# blog
bootstrap+springboot博客最简单实现

## 1 集成了mybatis+thymeleaf
## 2 集成了swagger2（访问路径http://localhost/swagger-ui.html#/）
![图片丢失了](https://github.com/jzyGithub/blog/blob/master/src/screenshots/swagger2shot.png?raw=true)
## 3 资源访问：
### 1 前台访问路径：http://localhost/
### 2 后台访问路径：http://localhost/back/
## 4 异常处理：
自定义异常处理类、自定义异常类
```
gr.blog.exception.CustomExceptionHandler
gr.blog.exception.FontException
```
## 5 阿里云服务器部署（资源在app文件夹下）：
参考教程[点击打开](https://blog.csdn.net/m0_37063257/article/details/78300877)
```
 ps -ef | grep java
 kill 进程号
 nohup java -jar blog.jar &
 tail -500f nohub.out
```
记录一下部署到云服务器遇到了乱码问题，原因是linux首次安装mysql字符设置问题，需要修改相关配置（csdn中有记录）。
## 6 缓存问题
### mybatis+spring 默认一级缓存失效问题，加上事务才能生效