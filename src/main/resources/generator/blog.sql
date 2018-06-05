CREATE TABLE `blog_article` (
  `id` int(11) NOT NULL,
  `title` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '文章标题',
  `description` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '文章描述',
  `content` text COLLATE utf8_bin COMMENT '文章内容)',
  `ckick_count` int(11) DEFAULT NULL COMMENT '点击次数',
  `is_recommend` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '是否推荐',
  `date_publish` date DEFAULT NULL COMMENT '发布时间',
  `author_id` int(11) DEFAULT NULL COMMENT '作者',
  `category_id` int(11) DEFAULT NULL COMMENT '分类',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签',
  `photo` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '文章图片简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文章表'