// 文件位置
def filePath = "version.txt"
//执行命令
def command = "git name-rev --name-only HEAD"
def process = command.execute()
println "process=" + process
process.waitFor()
def tags = process.text.tokenize()
def tagName = tags[0]
println "tagName=" + tagName
def dateTime = new Date().format("yyMMddHHmm")
def version = "$tagName$dateTime".replace("/","")
println "version=" + version
File file = new File(filePath)
println file.getAbsolutePath()
// 写入文件
file.write(version)