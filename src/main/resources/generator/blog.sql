-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog_article`
--

DROP TABLE IF EXISTS `blog_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '文章标题',
  `description` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '文章描述',
  `content` text COLLATE utf8_bin COMMENT '文章内容)',
  `click_count` int(11) DEFAULT '0' COMMENT '点击次数',
  `is_recommend` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '是否推荐',
  `date_publish` date DEFAULT NULL COMMENT '发布时间',
  `author_id` int(11) DEFAULT NULL COMMENT '作者',
  `category_id` int(11) DEFAULT '0' COMMENT '分类',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签',
  `photo` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '文章图片简介',
  PRIMARY KEY (`id`),
  KEY `fk_article_category_idx` (`category_id`),
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `blog_category` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3705 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_article`
--

LOCK TABLES `blog_article` WRITE;
/*!40000 ALTER TABLE `blog_article` DISABLE KEYS */;
INSERT INTO `blog_article` VALUES (3674,'2018年7月5日  日记','如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if 来判断，输出。思路已打开，样式调用就可以多样化啦！...','<p>回家时间两周零两天，接到父亲的电话，匆忙从哈尔滨回到龙江，奶奶开始可以攒一段力气，说上几句话，无非就是对母亲如何的不满，到现在已经有一个礼拜连一句话都说不清楚，父亲从头看到现在，最近很是辛苦，病情的严重，使奶奶更加难以维持一个状态太长时间，需要父亲不断的扶起和放平躺下，很是折磨人，晚上也睡不好觉，父亲是一位很孝顺的人，回家已有两个多星期，也是最近才看是疲惫与有些烦躁，俗话&ldquo;久病无孝子&rdquo;，想来也不无道理，但是终究还是耐心并尽力的伺候着奶奶，我说我来吧，奶奶还在推攘不许，从小心疼我的奶奶，见到她被这恶病折磨成这个样子，心里着实难受的紧，晚上去接母亲放羊回家，琢磨自己平淡而无为的小半生，除了有些怨气，不会排解，大概没什么值得骄傲的事情了。自认为是一个朝气不足的人，无论生理还是心理，也是，没在有些不舒服的情况下，自己才会去清理脑袋里的垃圾，或许这就是病吧。一场生下来就携带的病，无药可救的病，在想什么时候才能不那么怂，怂，简单的一个字，短暂的人生的完美总结。</p>\r\n\r\n<p>有些感慨感触感悟，会把人，搞迷糊。 ---李剑青</p>',1,'0','2018-07-08',1,11,NULL,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532279262930&di=5e9a7e21b2d931e466f27f6851876517&imgtype=0&src=http%3A%2F%2Fimg4q.duitang.com%2Fuploads%2Fitem%2F201304%2F28%2F20130428233454_XfC3B.jpeg'),(3683,'thymeleaf 标签使用记录','记录网站创建过程中使用过的thymeleaf的使用记录，主要是标签的使用。','<ol>\r\n	<li><strong>th:each :</strong></li>\r\n	<li><strong>th:text:</strong></li>\r\n	<li><strong>th:if</strong></li>\r\n	<li><strong>th:utext</strong></li>\r\n	<li><strong>th:with</strong></li>\r\n	<li><strong>th:inclue/th:replace/th:insert：</strong></li>\r\n</ol>\r\n\r\n<p><strong>​</strong><strong><code>th:insert</code>&nbsp; &nbsp;</strong>：保留自己的主标签，保留th:fragment的主标签。</p>\r\n\r\n<p><code>th:replace</code>&nbsp;：不要自己的主标签，保留th:fragment的主标签。</p>\r\n\r\n<p><code>th:include</code>&nbsp;：保留自己的主标签，不要th:fragment的主标签。（官方3.0后不推荐）</p>\r\n\r\n<p>&nbsp;</p>',2,'1','2018-07-15',NULL,10,NULL,'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1532762941&di=082f3c892b6d6ef45eeb48da3f231744&src=http://img2.3lian.com/2014/f6/152/d/136.jpg'),(3689,'springmvc 使用list、数组处理参数问题','springmvc 接收list、数组方法 参数取值时候注意的问题，有的情况下不需要添加注解，有的情况下需要添加@RequestBody@RequestParam等标签配合方法使用','<p>因为本站要为博客添加标签功能，博客与标签的关系模型为一对多，所以增删改查博客同时标签需要有相应操作，更新删除策略也是级联级别。</p>\r\n\r\n<p>在传递参数的时候发生了问题，页面使用的标签插件，传递给后台的是字符串并以逗号分隔开来的数组，springmvc可以处理这种请求，String数组处理的话，不需要添加注解，而使用List的情况下需要使用@RequestParam注解标注使用 的是哪个属性，本来的想法是在Article实体中添加Tag的list的，但controller如何接收页面传过来的这个参数，目前不太会，这里标记一下，提醒自己！！</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">@RequestMapping(\"/hello\")\r\n    public String test(String name,@RequestBody List&lt;ForListReceive&gt; list) {\r\n        /**\r\n         * 接收List的条件\r\n         * 1、使用JSON格式数据，如[{\"a\":\"a\",\"b\":\"b\"}] 放在RequestBody中传递\r\n         * 2、RequestHeader中需要有 Content-Type: application/json;charset=utf8\r\n         * 3、需要在参数前加上@RequestBody\r\n         */\r\n\r\n        System.out.println(list.get(0).getA());\r\n        return list.size()+\":\"+name;\r\n    }\r\n\r\n    @RequestMapping(\"/hi\")\r\n    public String hi(@RequestParam(\"list\") List&lt;String&gt; list) {\r\n        /**\r\n         * 接收List&lt;String&gt;\r\n         * 1、Request Parameters中list=a,b,c \r\n         * 2、必须写上@RequestParam(\"list\")\r\n         */\r\n        System.out.println(list.get(0));\r\n        return list.size()+\"\";\r\n    }\r\n\r\n    @RequestMapping(\"/hey\")\r\n    public String hey(String[] list) {\r\n        /**\r\n         * 接收数组\r\n         * 1、Request Parameters中list=a,b,c 即可成功接收\r\n         */\r\n        System.out.println(list[0].toString());\r\n        System.out.println(list[1].toString());\r\n        return list.length+\"\";\r\n    }</code></pre>\r\n\r\n<p>&nbsp;</p>',1,'1','2018-07-21',NULL,10,NULL,'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1532762676&di=78f59e95cf56637d7f769a9288b44285&src=http://img.zcool.cn/community/038c14d56fb2f506ac7257948b61828.jpg'),(3698,'bootstrap 插件bootstrap-tagsinput使用过程','bootstrap-tagsinput 为了为博客标签在网上找到的插件，但是使用的方法还有待研究','<p>官方API：<a href=\"http://bootstrap-tagsinput.github.io/bootstrap-tagsinput/examples/\" target=\"_blank\">http://bootstrap-tagsinput.github.io/bootstrap-tagsinput/examples/</a></p>\r\n\r\n<p>目前网站后台使用的只是最简单的标签初始化过程方法，并没有使用插件的方法和事件，两者需要以后有需求时候再研究学习。</p>',1,'1','2018-07-21',NULL,8,NULL,'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1532762676&di=d5fe94125fe0df6162bd65a2648717a0&src=http://img5q.duitang.com/uploads/blog/201406/01/20140601170820_n4fii.jpeg;https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532772654213&di=aa0d632ca548db2755350409449dcaba&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170326%2F278492a299e24985a5f3c0fe77d71c9c_th.jpg;https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1532762676&di=5ad550e512fb757c6ee9857fbb059e16&src=http://img3.duitang.com/uploads/item/201208/27/20120827162528_Wc2Sn.jpeg'),(3700,'thymeleaf 获取list指定位置元素','如题','<p>1. 想要获取列表元素值，通常是使用迭代标签th:each，但是目前需求是：只想获取列表中非常常少的几个元素，再使用迭代的话，未免会降低效率：</p>\r\n\r\n<pre>\r\n<code class=\"language-html\">th:each=\"article,status: ${articleList}\"</code></pre>\r\n\r\n<p>但是现在想获取指定位置元素位置的元素，比如想获得博客列表20当中前三个：</p>\r\n\r\n<pre>\r\n<code>${articleList[2].photo}</code></pre>\r\n\r\n<p>直接按照数组下标访问方式是成功的，其他方法仍不知道</p>\r\n\r\n<p>2.获取list的长度：</p>\r\n\r\n<pre>\r\n<code>​${articleList[2]size()}\r\n​而不是​\r\n${articleList[2].size}\r\n</code></pre>\r\n\r\n<p>&nbsp;</p>',1,'1','2018-07-22',NULL,10,NULL,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532285384237&di=b506f1819829ceb7f82a3320e57dbc56&imgtype=0&src=http%3A%2F%2Fs11.sinaimg.cn%2Fmw690%2F0032W324gy6JIeHfrGWca%26690'),(3703,'springboot 集成shiro 遇到的坑------ 身份认证','springboot 集成shiro方式，包括通过配置类的方式，为类加上@Configuration，并为每个方法加上@Bean，以及自定义过滤器的使用，如何理解过滤器，和spring是如何处理过滤器注册过程的。最后加深了理解shiro登录身份认证过程。','<p>在使用springboot集成shiro，我通过学习&nbsp;<u><a href=\"https://www.cnblogs.com/ll409546297/p/7815409.html\" target=\"_blank\"><span style=\"color:#FF0000\">springboot整合shiro应用</span></a></u>，然后通过代码配置方式进行配置的：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">/**\r\n * 描述：Shiro权限配置\r\n */\r\n@Configuration\r\npublic class ShiroConfiguration {\r\n    // 配置核心安全事务管理器\r\n    @Bean(name = \"securityManager\")\r\n    public SecurityManager securityManager(@Qualifier(\"shiroRealm\") ShiroRealm shiroRealm) {\r\n        ......\r\n    }\r\n    /**\r\n     * ShiroFilterFactoryBean 处理拦截资源文件问题。\r\n     */\r\n    @Bean\r\n    public ShiroFilterFactoryBean shirFilter(@Qualifier(\"securityManager\") SecurityManager manager){\r\n        ......\r\n    }\r\n    // 配置自定义的权限登录器\r\n    @Bean(name = \"shiroRealm\")\r\n    public ShiroRealm shiroRealm() {\r\n        ......\r\n    }\r\n}</code></pre>\r\n\r\n<p>通过&nbsp;<u><a href=\"https://www.cnblogs.com/ll409546297/p/7815409.html\" target=\"_blank\"><span style=\"color:#FF0000\">springboot整合shiro应用</span></a></u>&nbsp;知道<strong>Shiro</strong><strong>不会去维护用户、维护权限；这些需要我们自己去设计/</strong><strong>提供；然后通过相应的接口注入给Shiro</strong><strong>即可。</strong>所以需要&ldquo;配置自定义的权限登录器&rdquo;并将登录器配置给&ldquo;安全事务管理器securityManager&rdquo;，将管理器配置给拦截器工厂ShiroFilterFactoryFactoryBean中，这样，最简单的shiro权限框架就集成完成了，我的拦截器工厂是这样配置的（这样是有问题的，后文进行修改）：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">    @Bean\r\n    public ShiroFilterFactoryBean shirFilter(@Qualifier(\"securityManager\") SecurityManager manager){\r\n        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();\r\n        bean.setSecurityManager(manager);\r\n        // 配置登录的url和登录成功的url\r\n        bean.setLoginUrl(\"/login\");\r\n        bean.setSuccessUrl(\"/index\");\r\n        // 配置访问权限\r\n        LinkedHashMap&lt;String, String&gt; filterChainDefinitionMap = new LinkedHashMap&lt;&gt;();\r\n        // 配置不同的URL采用的验证方式\r\n        filterChainDefinitionMap.put(\"/login\", \"anno\");\r\n        filterChainDefinitionMap.put(\"/back**\", \"authc\");// 表示需要认证才可以访问\r\n        filterChainDefinitionMap.put(\"/back/**\", \"authc\");// 表示需要认证才可以访问\r\n        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);\r\n        return bean;\r\n    }</code></pre>\r\n\r\n<p>因为我的博客系统前后台都在一个项目管理之下，前台访问需要放行，所以我只拦截后台访问，我的后台访问URL全部由/back开头，所以我拦截了所有/back开头的请求，我们的controller层&quot;/login&quot;处理方法：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">    @ApiOperation(\"进入登录界面\")\r\n    @RequestMapping(value = \"/login\",method = RequestMethod.GET)\r\n    public String login(){\r\n        return \"backstage/login\";\r\n    }</code></pre>\r\n\r\n<p>这时候，我们猜测访问以/back访问的请求都会被shiro拦截下来，可是如果直接请求/login是不会被拦截的，可以由springboot处理请求，开始了我的测试（这里先不要考虑自定义权限管理器，因为还没有用到）：</p>\r\n\r\n<p>测试一:访问后台首页:&nbsp;<a href=\"http://localhost/back\" target=\"_blank\">http://localhost/back</a>&nbsp;发现确实重定向转向到了配置中LoginUrl也就是&quot;/login&quot;，然后&quot;/login&quot;会被shiro放行，进入到login处理方法中，进入到&quot;backstage/login&quot;指定的界面。</p>\r\n\r\n<p>OK，测试一通过！！说明shiro生效了。这里贴一张登录界面照片，方便下边理解，如果各位觉得我的登录模板丑，完全可以忽略：</p>\r\n\r\n<p><img alt=\"\" src=\"http://img2.ph.126.net/2sOEd5jyoBDe0EzdzRBRbA==/5717519838063628634.jpg\" style=\"height:553px; width:494px\" /></p>\r\n\r\n<p>下一步，我们需要登录过程，还是参考&nbsp;<u><a href=\"https://www.cnblogs.com/ll409546297/p/7815409.html\" target=\"_blank\"><span style=\"color:#FF0000\">springboot整合shiro应用</span></a></u>&nbsp;中表单请求路径依然&quot;/login&quot;,不过这时候请求方式改为post，springboot的处理方法为</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">    //post登录\r\n    @RequestMapping(value = \"/login\",method = RequestMethod.POST)\r\n    public String login(@RequestBody Map map){\r\n        //添加用户认证信息\r\n        Subject subject = SecurityUtils.getSubject();\r\n        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(\r\n                map.get(\"username\").toString(),\r\n                map.get(\"password\").toString());\r\n        //进行验证，这里可以捕获异常，然后返回对应信息\r\n        subject.login(usernamePasswordToken);\r\n        return \"login\";\r\n    }</code></pre>\r\n\r\n<p>还是刚才那句话，&quot;/login&quot;会被shiro放行，当我们填写好表单，并请求后台，依然会直接进入到login方法中，方法的步骤中需要解释一下，先获得shiro的subject对象，相信在座的个位对shiro都有一定了解，如果不了解，请参考&nbsp;<u><a href=\"http://jinnianshilongnian.iteye.com/blog/2018398\" target=\"_blank\"><span style=\"color:#FF0000\">跟我学Shiro目录贴</span></a></u>&nbsp;，通过subject.login()方法进行登录，相信各位在各种集成教程都是这样教，其实这样是不必的，这个后边再说为什么是不要的，目前我们还是按照这个步骤进行测试，login方法就需要上边我说过的自定义权限管理器了，我的自定义权限管理器是这样的：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">/**\r\n * shiro 用户认证 授权\r\n */\r\npublic class ShiroRealm extends AuthorizingRealm {\r\n\r\n    @Autowired\r\n    private LoginService loginService;\r\n\r\n    @Override\r\n    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {\r\n        ......\r\n    }\r\n\r\n    @Override\r\n    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {\r\n        Object principal = authenticationToken.getPrincipal();\r\n        if (principal == null) {\r\n            //System.out.println(\"ahah\");\r\n            return null;//这里返回null，shiro控制器根据返回的null判断，用户名有误，产生UnknownAccountException用户名出错异常，一般不会出现这个错误，因为前台和后台校验不允许出现空用户名\r\n        }\r\n        String userName = principal.toString();\r\n        SysUser user = loginService.getUserByName(userName);\r\n        if (user == null){\r\n            return null;//这里返回null，表示该用户名不存在，和上一个null都是触发shiro产生UnknownAccountException异常，也就是账户异常\r\n        }\r\n        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());\r\n        return simpleAuthenticationInfo;\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<p>这里主要看doGetAuthenticationInfo方法用户认证方法，login方法最终会调用到它。</p>\r\n\r\n<p>测试二:当我们填写完&nbsp;表单账户密码信息，点击登录后，发现成功进入到post的login方法，debug发现确实进入到身份认证方法中。账户密码如果都正确的话会进入到login指定的页面。</p>\r\n\r\n<p>OK，测试二正确！！</p>\r\n\r\n<p>下面是我为什么要写这篇博客的原因了，之前也有用过struts2+spring+hibernate集成shiro的项目练习，不过理解程度太低了，通过配置文件的方式，按照网络上的教程，配置好之后也没有深入研究，借着这次springboot集成shiro过程加深对shiro的学习。</p>\r\n\r\n<p>阅读上边的配置拦截器工厂中我发现有一个步骤是：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">bean.setSuccessUrl(\"/index\");</code></pre>\r\n\r\n<p>通过字面就很好理解，登录成功跳转的界面嘛，也就是测试二中login方法登录成功跳转的界面呗，可是没有起作用啊！！这就很恼火了，通过度娘，我了解到，可以通过自定义过滤器才能让这个URL发生作用（事实证明大自然的搬运工实在是太可怕了），按照教程自定义了一个继承自FormAuthenticationFilter过滤器：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public class ShiroLoginFilter extends FormAuthenticationFilter {\r\n\r\n    //重写各种方法尝试登陆成功跳转\r\n    ......\r\n}</code></pre>\r\n\r\n<p>然后需要将该过器注册到拦截器链中，在shiro配置类中添加：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">    @Bean\r\n    public ShiroLoginFilter shiroLoginFilter(){\r\n        ShiroLoginFilter shiroLoginFilter = new ShiroLoginFilter();\r\n        //shiroLoginFilter.setPasswordParam(\"password\");\r\n        return shiroLoginFilter;\r\n    }</code></pre>\r\n\r\n<p>按照网络上的教程，需要在过滤器方法中添加这段代码：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">    @Bean\r\n    public ShiroFilterFactoryBean shirFilter(@Qualifier(\"securityManager\") SecurityManager manager){\r\n\r\n        ......\r\n        bean.setSuccessUrl(\"/back\");\r\n\r\n        // 定义过滤器\r\n        Map&lt;String, Filter&gt; filterMap = bean.getFilters();\r\n        filterMap.put(\"authc\", shiroLoginFilter());\r\n        //filterMap.put(\"perms\", new ShiroPermissionsFilter());\r\n\r\n        ......\r\n    }</code></pre>\r\n\r\n<p>这样过滤器就会被注册为身份认证过滤器（authc），这时候，糟糕的情况发生了，添加过滤器的情况下，就会报：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">org.apache.shiro.UnavailableSecurityManagerException: No SecurityManager accessible to the calling code,either bound to the org.apache.shiro.util.ThreadContext or as a vm static singleton.  This is an invalid application configuration.</code></pre>\r\n\r\n<p>这个错误大概是跟securityManager找不到问题，但是没加过滤器的时候，securityManager明明是好用的啊，针对这个问题我找到了两个解决方法（依然说一句搬运工实在是太可怕了，最后这两个方法都被放弃了）：</p>\r\n\r\n<p>方法一：参照&nbsp;<u><a href=\"https://www.cnblogs.com/ginponson/p/6217057.html\" target=\"_blank\"><span style=\"color:#FF0000\">https://www.cnblogs.com/ginponson/p/6217057.html</span></a></u>&nbsp;中添加方法：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">@Bean\r\npublic FilterRegistrationBean delegatingFilterProxy(){\r\n    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();\r\n    DelegatingFilterProxy proxy = new DelegatingFilterProxy();\r\n    proxy.setTargetFilterLifecycle(true);\r\n    proxy.setTargetBeanName(\"shiroFilter\");\r\n    filterRegistrationBean.setFilter(proxy);\r\n    return filterRegistrationBean;\r\n}</code></pre>\r\n\r\n<p>方法一不做过多评判，毕竟博主是根据他个人的业务需求需要才这样写，将shiroFilter注册到spring中。</p>\r\n\r\n<p>方法二：将过滤器注册方法在配置类中放到后边。</p>\r\n\r\n<p>方法三：new一个新的过滤器注册到过滤器链中。</p>\r\n\r\n<p>这两种方法虽然解决了报错，但是debug登录的过程中，发现先执行的是过滤器的onLoginSuccess/onLoginFailure,可是这样不对啊，应该是先验证账户密码成功或失败才执行这两个方法嘛，为什么会先执行这两个方法啊？</p>\r\n\r\n<p>幸运的是，我找到了原因以及解决方案：&nbsp;<u><a href=\"https://www.cnblogs.com/yuananyun/p/8033822.html\" target=\"_blank\"><span style=\"color:#FF0000\">解决Shiro+SpringBoot自定义Filter不生效问题</span></a><span style=\"color:#FF0000\">&nbsp;</span></u>， 发现上边的方法一已经很靠近了，只不过我们不是注册shiroFilter，而是注册过滤器，将方法一的代码替换为:</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">    @Bean\r\n    public FilterRegistrationBean shiroLoginFilteRegistration(ShiroLoginFilter filter) {\r\n        FilterRegistrationBean registration = new FilterRegistrationBean(filter);\r\n        registration.setEnabled(false);\r\n        return registration;\r\n    }</code></pre>\r\n\r\n<p>替换之后，再debug发现过程是，先执行login在执行过滤器的处理方法。发现这个处理方法生效了。</p>\r\n\r\n<p>跑题跑题了！！开始还在讨论登录成功跳转到successUrl呢，在上一步中虽然方法一虽然是不合格的，但是onLoginSuccess/onLoginFailure方法确实执行了，而经过修改后正确的程序无论login成功与否都不会执行其中任何一个方法，查阅度娘，有人说是注册失败，但我肯定我的自定义过滤器肯定没失效，因为重写了onAccessDenied方法，这里先不管这个方法是干什么的，因为当我们初次访问/back路径的时候，这个方法确实是执行了的，说明我的自定义过滤器是没问题的。那是什么地方出错了呢？</p>\r\n\r\n<p>终极解决方案：<u><a href=\"http://lib.csdn.net/article/java/66210#focustext\" target=\"_blank\"><span style=\"color:#FF0000\">http://lib.csdn.net/article/java/66210#focustext</span></a></u><span style=\"color:#FF0000\">&nbsp;</span>这是一篇非常棒的shiro分析的文章，通过这篇文章，我们了解到根本不需要post类型的/login方法来处理表单，因为shiro的FormAuthenticationFilter已经为我们提供这个功能，这是有一个地方需要特别注意：<strong><span style=\"color:#FF0000\">配置URL验证方式过程需要将/login，设置为authc</span></strong>,这样在表单发出登录请求才会由shiro来处理。</p>\r\n\r\n<p>测试三：删除controller中请求类型为post 的login方法，修改URL验证规则：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">    @Bean\r\n    public ShiroFilterFactoryBean shirFilter(@Qualifier(\"securityManager\") SecurityManager manager){\r\n        ......\r\n        // 配置不同的URL采用的验证方式\r\n        filterChainDefinitionMap.put(\"/login\", \"authc\");\r\n        ......\r\n    }</code></pre>\r\n\r\n<p>表单请求之后，在填写正确的账户密码之后，经过shiro认证过程，成功进入到了onLoginSuccess方法中，最后自动转向上一次访问路径，根本与其他人说的需要自定义过滤器才能实现访问上一次路径，因为在登录成功后，shiro会执行onLoginSuccess，而onLoginSuccess方法会执行issueSuccessRedirect，网络上说要重写这个方法，其实根本不用，因为FormAuthenticationFilter提供issueSuccessRedirect方法就带有：<span style=\"color:#FF0000\">如果上次请求是登录页，则进入到successUrl中，不是登录页，登录成功后跳转到刚开始用户请求的路径</span>，根本不需要自己再实现一遍，shiro默认使用的还是FormAuthenticationFilter过滤器。所以最后自定义的filter这步完全可以省略，还要把springboot配置类中相关信息删除，也证明了为什么上边自己在controller中通过subject.login方法无论成功与否都不会执行onLoginSuccess/onLoginFailure方法了，折腾一圈，发现根本不要这么麻烦，累的同时，确实也收获到不少东西。</p>\r\n\r\n<p>上边的步骤都做好了之后，我又发现了一个问题，上边最后一步中说到，是填写正确的用户密码信息，那如果填写的是不正确的密码或用户名呢？这点在以前的项目中我是这么处理的：在controller中检查login会发生的异常，可是现在我们没有采用这种办法，所有有了下面的测试四。</p>\r\n\r\n<p>测试四：登录表单填写不正确的用户名/密码信息，点击登录，前台返回Whitelabel Error Page页面，后台报：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">Request method \'POST\' not supported</code></pre>\r\n\r\n<p>说明请求类型为post的的一个方法被请求了，从&nbsp;<u><a href=\"https://blog.csdn.net/hotdust/article/details/53783332\" target=\"_blank\"><span style=\"color:#FF0000\">https://blog.csdn.net/hotdust/article/details/53783332</span></a></u><span style=\"color:#FF0000\">&nbsp;</span>中知道，我们需要一个请求路径为LoginUrl（本项目中是/login,各位看官自行更改）并且请求类是post方法，它的作用只用来处理错误出现的情况就好，所以我按照参考博客内容，在我的controller中也添加这样一个方法：</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">    @ApiOperation(\"实际登录过程，只需要处理错误就好，登录交给shiro完成\")\r\n    @RequestMapping(value = \"/login\",method = RequestMethod.POST)\r\n    public String login(ModelMap modelMap, HttpServletRequest request){\r\n        String errorClassName = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME).toString();\r\n        if(UnknownAccountException.class.getName().equals(errorClassName)) {\r\n            modelMap.put(\"error\", \"用户名/密码错误\");\r\n        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {\r\n            modelMap.put(\"error\", \"用户名/密码错误\");\r\n        } else if(errorClassName != null) {\r\n            modelMap.put(\"error\", \"未知错误：\" + errorClassName);\r\n        }\r\n        return \"backstage/login\";\r\n    }</code></pre>\r\n\r\n<p>输入错误账号或密码，发现确实重新进入到登录界面。shiro身份认证告一段落，下面会继续写授权相关内容的。</p>\r\n\r\n<p><span style=\"font-size:22px\">本文参考资料：</span></p>\r\n\r\n<p><span style=\"font-size:22px\">1.&nbsp;<u><a href=\"https://www.cnblogs.com/ll409546297/p/7815409.html\" target=\"_blank\"><span style=\"color:#0000FF\">springboot整合shiro应用</span></a></u></span></p>\r\n\r\n<p><span style=\"font-size:22px\">2.<span style=\"color:#0000FF\">&nbsp;</span><u><a href=\"https://www.cnblogs.com/ginponson/p/6217057.html\" target=\"_blank\"><span style=\"color:#0000FF\">https://www.cnblogs.com/ginponson/p/6217057.html</span></a></u><span style=\"color:#0000FF\">&nbsp;</span></span></p>\r\n\r\n<p><span style=\"font-size:22px\">3.&nbsp;<u><a href=\"https://www.cnblogs.com/yuananyun/p/8033822.html\" target=\"_blank\"><span style=\"color:#0000FF\">解决Shiro+SpringBoot自定义Filter不生效问题</span></a></u></span></p>\r\n\r\n<p><span style=\"font-size:22px\">4.&nbsp;<u><a href=\"http://lib.csdn.net/article/java/66210#focustext\" target=\"_blank\"><span style=\"color:#0000FF\">http://lib.csdn.net/article/java/66210#focustext</span></a></u></span></p>\r\n\r\n<p><span style=\"font-size:22px\">5.&nbsp;<u><a href=\"https://blog.csdn.net/hotdust/article/details/53783332\" target=\"_blank\"><span style=\"color:#0000FF\">https://blog.csdn.net/hotdust/article/details/53783332</span></a></u></span></p>',1,'1','2018-07-28',NULL,10,NULL,'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1532762668&di=6b3cffd663a928976ea8d16168aac899&src=http://pic1.win4000.com/wallpaper/3/5574f07700d9c.jpg'),(3704,'shiro标签的使用过程','以往的项目中，更多的是使jsp配合shiro实现页面中的权限控制，但是在springboot中我使用的thymeleaf，为了在thymeleaf中使用shiro，就需要进行一些配置了。','<p><span style=\"font-size:20px\">以往的项目中，更多的是使jsp配合shiro实现页面中的权限控制，但是在springboot中我使用的thymeleaf，为了在thymeleaf中使用shiro，就需要进行一些配置了。</span></p>\r\n\r\n<p><span style=\"font-size:20px\">首先，引入thymeleaf的shiro插件，pom.xml中添加：</span></p>\r\n\r\n<pre>\r\n<code class=\"language-xml\">&lt;dependency&gt;\r\n    &lt;groupId&gt;com.github.theborakompanioni&lt;/groupId&gt;\r\n    &lt;artifactId&gt;thymeleaf-extras-shiro&lt;/artifactId&gt;\r\n    &lt;version&gt;2.0.0&lt;/version&gt;\r\n&lt;/dependency&gt;</code></pre>\r\n\r\n<p><span style=\"font-size:22px\">然后，在shiro配置文件中添加shiro标签配置：</span></p>\r\n\r\n<pre>\r\n<code class=\"language-java\">/**\r\n * 描述：Shiro权限配置\r\n */\r\n@Configuration\r\npublic class ShiroConfiguration {\r\n\r\n    ......\r\n\r\n    /**\r\n     * 作用：在thymeleaf中使用shiro标签\r\n     */\r\n    @Bean\r\n    public ShiroDialect shiroDialect(){\r\n        return new ShiroDialect();\r\n    }\r\n\r\n    ......\r\n}</code></pre>\r\n\r\n<p><span style=\"font-size:22px\">然后在html中就可以使用shiro标签了。</span></p>\r\n\r\n<p><span style=\"font-size:22px\">1. &lt;shiro:principal/&gt;标签：获取当前登录用户的登录名，在我的后台系统中，需要在用户头像旁显示用户名，项目初期版本就直接是用登录名作为账户名进行显示，后期如果添加新字段--登录名字段，那个时候再另做处理。目前系统作用就是这样，</span></p>\r\n\r\n<p><span style=\"font-size:22px\">2. 下一步是开发权限模块，权限模块完成后，再有需求需要在页面进行权限控制，再来更新。</span></p>',1,'1','2018-07-31',NULL,10,NULL,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533010568&di=53962abfb09ac3a7a873f608dabb9ad9&imgtype=jpg&er=1&src=http%3A%2F%2Fpic36.photophoto.cn%2F20150704%2F0005018771298196_b.jpg');
/*!40000 ALTER TABLE `blog_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_category`
--

DROP TABLE IF EXISTS `blog_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `pid` int(11) DEFAULT '0' COMMENT '父级标签id',
  `sort` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '顺序',
  `description` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_category`
--

LOCK TABLES `blog_category` WRITE;
/*!40000 ALTER TABLE `blog_category` DISABLE KEYS */;
INSERT INTO `blog_category` VALUES (2,'开发',0,'2','不要轻易放弃。学习成长的路上，我们长路漫漫，只因学无止境。'),(3,'生活',0,'3','慢生活，不是懒惰，放慢速度不是拖延时间，而是让我们在生活中寻找到平衡。'),(7,'C/C++',2,'1',NULL),(8,'JS',2,'2',''),(9,'Python',2,'3',NULL),(10,'Java',2,'4',NULL),(11,'日记',3,'1',''),(12,'美图',3,'2',''),(13,'程序',3,'3',''),(14,'语录',3,'4','');
/*!40000 ALTER TABLE `blog_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_tag`
--

DROP TABLE IF EXISTS `blog_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tag_article_idx` (`article_id`),
  CONSTRAINT `fk_tag_article` FOREIGN KEY (`article_id`) REFERENCES `blog_article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_tag`
--

LOCK TABLES `blog_tag` WRITE;
/*!40000 ALTER TABLE `blog_tag` DISABLE KEYS */;
INSERT INTO `blog_tag` VALUES (81,'thymeleaf',3700),(124,'日记',3674),(128,'bootstrap',3698),(129,'tag',3698),(132,'springmvc',3689),(133,'thymeleaf',3683),(135,'shiro',3704),(138,'shiro',3703),(139,'springboot',3703);
/*!40000 ALTER TABLE `blog_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_visitor_record`
--

DROP TABLE IF EXISTS `blog_visitor_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_visitor_record` (
  `ip` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '游客ip',
  `first_time` date DEFAULT NULL COMMENT '首次浏览时间',
  `last_time` date DEFAULT NULL COMMENT '最后浏览时间',
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `love` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '是否喜欢',
  `belong_place` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ip`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博客系统游客浏览记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_visitor_record`
--

LOCK TABLES `blog_visitor_record` WRITE;
/*!40000 ALTER TABLE `blog_visitor_record` DISABLE KEYS */;
INSERT INTO `blog_visitor_record` VALUES ('0:0:0:0:0:0:0:1',NULL,NULL,3674,NULL,NULL),('0:0:0:0:0:0:0:1',NULL,NULL,3683,NULL,NULL),('0:0:0:0:0:0:0:1',NULL,NULL,3689,'1',NULL),('0:0:0:0:0:0:0:1',NULL,NULL,3698,'1',NULL),('0:0:0:0:0:0:0:1',NULL,NULL,3700,'1',NULL),('0:0:0:0:0:0:0:1',NULL,NULL,3703,NULL,NULL),('0:0:0:0:0:0:0:1',NULL,NULL,3704,NULL,NULL),('1245',NULL,NULL,456,NULL,NULL),('192.168.1.103',NULL,NULL,3683,'1',NULL),('2',NULL,NULL,123,NULL,NULL);
/*!40000 ALTER TABLE `blog_visitor_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='菜单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,0,'系统管理','null','null',0,'app-menu__icon fa fa-laptop',0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'Administrator','系统管理员',3,'2018-08-01 00:00:00');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `email` varchar(100) DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT '' COMMENT '手机号',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','admin','88888888@qq.com','18888888888',0,1,'2018-01-01 00:00:00');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-01  0:30:09
