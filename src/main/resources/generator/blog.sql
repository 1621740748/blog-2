CREATE TABLE `blog`.`blog_article` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL COMMENT '文章标题',
  `desc` VARCHAR(45) NULL COMMENT '文章描述',
  `content` TEXT(3000) NULL COMMENT '文章内容)',
  `ckick_count` INT NULL COMMENT '点击次数',
  `is_recommend` VARCHAR(2) NULL COMMENT '是否推荐',
  `date_publish` DATE NULL COMMENT '发布时间',
  `author_id` INT NULL COMMENT '作者',
  `category_id` INT NULL COMMENT '分类',
  `tag_id` INT NULL COMMENT '标签',
  PRIMARY KEY (`id`))
COMMENT = '文章表';