# blog
bootstrap+springboot博客最简单实现
项目地址

(前台):[前台连接](http://47.52.56.163/)
        
(后台):[后台连接](http://47.52.56.163/back/)

## 1 集成了mybatis+thymeleaf
## 2 集成了swagger2（访问路径http://localhost/swagger-ui.html#/）
![图片丢失了](http://i1.bvimg.com/652887/e1072e3dfa343f5f.png)
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
### 文章"新建"过程中，图片上传功能
默认没有图片上传功能，js初始化配置：
```
<script>
        CKEDITOR.replace("content",//textarea的id
                        {
                            filebrowserUploadUrl : '/back/image/fileUpload',
                            filebrowserImageUploadUrl : '/back/image/fileUpload'
                        });
</script>
```
config.js配置文件修改设置,修改图片显示区提示英文字母：
```
config.image_previewText = '图片最大宽度最好设置为760px';
```
使用时调用回调函数显示图片预览失败，发现后台查找"CKEditorFuncNum"失败，最后替换了CKeditor版本解决掉这个问题，具体原因尚不清楚，修改后的版本为3.0
## 8 分页显示
使用插件PageHelper
前台是用DataTable显示表格，开启后台分页
```
<script type="text/javascript" th:inline="javascript">
      /*<![CDATA[*/
        var path = [[${path}]];
        $('#sampleTable').DataTable({
            "sDom" : '<"row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6"f>>rt<"row"<"col-sm-12 col-md-6 crudBtn"><"col-sm-12 col-md-6"p>><"clear">',
            bServerSide : true,//开启后台分页功能
            fnServerData: retrieveData, //执行函数
            bPaginate : true,//显示分页按钮
            sAjaxSource : path + "/back/articlePage",//请求url
            aoColumns : [{"mDataProp": "id", "sDefaultContent": ""},
                         {"mDataProp": "title", "sDefaultContent": ""},
                         {"mDataProp": "categoryId", "sDefaultContent": ""},
                         {"mDataProp": "authorId", "sDefaultContent": ""},
                         {"mDataProp": "datePublish", "mRender": function(data, type, full){
                            var date = new Date(data);
                            var y = date.getFullYear();
                            var m = "0"+(date.getMonth()+1);
                            var d = "0"+date.getDate();
                            return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
                         }, "sDefaultContent": ""},
                         {"mDataProp": "isRecommend", "sDefaultContent": ""},
                         {"mDataProp": "clickCount", "sDefaultContent": ""}
                        ],
            "language": {
              "lengthMenu": "每页 _MENU_ 条记录",
              "zeroRecords": "没有找到记录",
              "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )  显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
              "infoEmpty": "无记录",
              "infoFiltered": "(从 _MAX_ 条记录过滤)",
              "search": "搜索",
              "oPaginate": {
                  "sFirst": "首页",
                  "sPrevious": "上页",
                  "sNext": "下页",
                  "sLast": "末页"
              },
              "oAria": {
                  "sSortAscending": ": 以升序排列此列",
                  "sSortDescending": ": 以降序排列此列"
              }
            },
        });
      /*]]>*/

      function retrieveData(sSource, aoData, fnCallback) {
          console.log(JSON.stringify(aoData));
          $.ajax({
              url: sSource,
              data: {"aoData" : JSON.stringify(aoData)},
              type: "post",
              dataType: "json",
              async: false,//异步与否，待测试
              success: function(result){
                    fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
              },
              error: function(msg){

              }
          });
      }
    </script>
```
# 小总结展示
## 后台文章管理
![图片丢失了](http://i4.bvimg.com/652887/eb6cbf783485c2ce.png)
## 前台首页展示
![图片丢失了](http://i2.bvimg.com/652887/97851bf5ae840b15s.png)
## 关于我
![图片丢失了](http://i2.bvimg.com/652887/12f58cb29a237014s.png)