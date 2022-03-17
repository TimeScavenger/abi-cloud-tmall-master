package com.abi.tmall.product.server.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * 1、异步方法使用注解@Async的返回值只能为void或者Future。
 * 2、注解的方法必须是public方法。
 * 3、注意：在同一个类中，一个方法调用另外一个有注解（比如@Async，@Transational）的方法，注解会失效
 */
@Service
public class TaskService {

    /**
     * 最简单的异步调用，返回值为void
     */
    @Async
    public void executeTask() {
        System.out.println("线程ID：" + Thread.currentThread().getId() + " 执行异步无参数的任务");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带参数的异步调用 异步方法可以传入参数
     * 对于返回值是void，异常会被AsyncUncaughtExceptionHandler处理掉
     *
     * @param i
     */
    @Async
    public void executeTask(Integer i) {
        System.out.println("线程ID：" + Thread.currentThread().getId() + " 执行异步有参数的任务:" + i);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常调用返回Future
     * 对于返回值是Future，不会被AsyncUncaughtExceptionHandler处理，需要我们在方法中捕获异常并处理
     * 或者在调用方在调用Futrue.get时捕获异常进行处理
     *
     * @param i
     * @return
     */
    @Async
    public Future<String> executeTaskHasReturn(Integer i) {
        System.out.println("线程ID：" + Thread.currentThread().getId() + " 执行异步有参数有返回的任务:" + i);
        Future<String> future;
        try {
            Thread.sleep(2000);
            future = new AsyncResult<String>("success:" + i);
            throw new IllegalArgumentException("error");
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error-InterruptedException");
        } catch (IllegalArgumentException e) {
            future = new AsyncResult<String>("error-IllegalArgumentException");
        }
        return future;
    }

}