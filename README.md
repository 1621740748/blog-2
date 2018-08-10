# blog
bootstrap+springboot博客最简单实现
项目地址

[中英个人博客](http://www.jzyblog.top/)
        
[中英个人博客/后台](http://www.jzyblog.top/back/)

## 1 集成了mybatis+thymeleaf
## 2 集成了swagger2（访问路径
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
 tail -500f nohup.out
```
记录一下部署到云服务器遇到了乱码问题，原因是linux首次安装mysql字符设置问题，需要修改相关配置（csdn中有记录）。
## 6 缓存问题
### mybatis+spring 默认一级缓存失效，加上事务才能生效
## 7 ckeditor插件使用
config.js配置文件修改设置,修改图片显示区提示英文字母：
```
config.image_previewText = '图片最大宽度最好设置为760px';
```
使用时调用回调函数显示图片预览失败，发现后台查找"CKEditorFuncNum"失败，最后替换了CKeditor版本解决掉这个问题，具体原因尚不清楚，修改后的版本为3.0，版本再次修改为4.1.1
## 8 分页显示
DataTable显示表格，开启后台分页
# 小总结展示
## 后台文章管理
## 前台首页展示
## 关于我
## 9 集成畅言评论留言系统
## 10 前台页面公共代码抽取
1 首页轮播+右边两个博客：按照id倒序查找，并查询前五名喜欢数最多的文章，轮播图分三个 ，右边分两个

2 特别推荐：就是推荐文章中点击量最大的三个

3 推荐文章：就是正常推荐文章按id倒叙前五

4 点击排行：按点击量排序

5 标签云：  标签分组并查询每组个数最多的前八个标签

## 11 前台点赞
新增有关点赞的后台方法:

1 进入详细页，查询一次喜欢数方法 getLikeCount

2 点击喜欢 ，首先到数据库中查找喜欢数，如果不是0，说明喜欢过，事实是不是0就是1两种可能，方法是isLiked

3 最后返回根据第二步中的结果，如果没喜欢过，就先曾加一次喜欢addLike， 然后再查询一次喜欢数，将喜欢数返回给前端，如果喜欢过，则返会前端-1.前台进行判断进行相应的修改或提示用户。

## 12  增加ip归属地，后台首页统计展示
## 13  前后台cdn，BootCDN、小贱贱图床。
## 14 shiro记住我