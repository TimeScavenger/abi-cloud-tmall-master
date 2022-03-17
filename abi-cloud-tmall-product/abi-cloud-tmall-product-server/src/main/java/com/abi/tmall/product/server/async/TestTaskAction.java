package com.abi.tmall.product.server.async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestTaskAction {

    public static void main(String[] args) {
        System.out.println("主线程id：" + Thread.currentThread().getId() + " 开始执行调用任务...");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AsyncConfig.class);
        TaskService taskService = context.getBean(TaskService.class);

        for (int i = 0; i < 10; i++) {
            // 多线程执行任务
            taskService.executeTask();
            taskService.executeTask(i);
            Future<String> result = taskService.executeTaskHasReturn(i);

            // 调用下方的代码，整个逻辑不再是异步
            try {
                System.out.println("异步程序执行结束，返回内容：" + result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        context.close();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程id：" + Thread.currentThread().getId() + " 程序结束...");
    }
}