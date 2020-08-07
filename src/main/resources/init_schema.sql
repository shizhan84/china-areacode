CREATE TABLE `area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) NOT NULL COMMENT '区域编码',
  `parent_code` varchar(16) NOT NULL COMMENT '父区域编码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
);
