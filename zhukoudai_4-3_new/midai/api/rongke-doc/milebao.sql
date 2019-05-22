/*
SQLyog v10.2
MySQL - 5.6.30 : Database - milebao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`milebao` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `milebao`;

/*Table structure for table `about_xed` */

DROP TABLE IF EXISTS `about_xed`;

CREATE TABLE `about_xed` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `about_us` mediumtext,
  `serve_phone` varchar(255) DEFAULT NULL,
  `wexin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='关于我们';

/*Data for the table `about_xed` */

insert  into `about_xed`(`id`,`about_us`,`serve_phone`,`wexin`) values (2,'123123123123123sdfdsf','','');

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `user_id` bigint(33) NOT NULL COMMENT '用户id',
  `province` varchar(10) DEFAULT NULL COMMENT '省',
  `city` varchar(10) DEFAULT NULL COMMENT '市',
  `area` varchar(10) DEFAULT NULL COMMENT '区',
  `area_code` varchar(6) DEFAULT NULL COMMENT '区域代码',
  `address_details` varchar(300) DEFAULT NULL COMMENT '地址详情',
  `address_code` int(3) DEFAULT NULL COMMENT '地址代号',
  `status` varchar(30) DEFAULT NULL COMMENT '状态',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `fk_address_user` (`user_id`),
  CONSTRAINT `fk_address_user` FOREIGN KEY (`user_id`) REFERENCES `user_basic_msg` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`id`,`user_id`,`province`,`city`,`area`,`area_code`,`address_details`,`address_code`,`status`,`gmt_datetime`,`upt_datetime`) values (1,1,'浙江','杭州','拱墅','3306','祥园路',220155,NULL,'2017-12-09 11:49:06','2017-11-28 11:49:08');

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(19) DEFAULT NULL COMMENT '真实姓名',
  `token` varchar(255) DEFAULT NULL,
  `gmt_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `upt_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `admin_role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `admin_role_id` (`admin_role_id`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`admin_role_id`) REFERENCES `admin_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='后台登录表';

/*Data for the table `admin` */

insert  into `admin`(`id`,`login_name`,`password`,`name`,`token`,`gmt_datetime`,`upt_datetime`,`admin_role_id`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e','admin','e7b1eb2c-aeb2-4d5b-9245-f09b0d17f5a6','2017-11-30 10:03:19','2017-12-13 20:25:36',1),(7,'rosi','e10adc3949ba59abbe56e057f20f883e','rosi',NULL,'2017-12-11 15:05:59','2017-12-11 15:06:00',3),(8,'lisa','e10adc3949ba59abbe56e057f20f883e','lisa',NULL,'2017-12-11 15:05:59','2017-12-11 15:06:00',3),(9,'marry','e10adc3949ba59abbe56e057f20f883e','marry',NULL,'2017-12-11 15:05:59','2017-12-11 15:06:00',3),(10,'rosi1','e10adc3949ba59abbe56e057f20f883e','rosi1',NULL,'2017-12-11 15:05:59','2017-12-11 15:06:00',2),(11,'lisa1','e10adc3949ba59abbe56e057f20f883e','lisa1',NULL,'2017-12-11 15:05:59','2017-12-11 15:06:00',2),(12,'marry1','e10adc3949ba59abbe56e057f20f883e','marry1',NULL,'2017-12-11 15:05:59','2017-12-11 15:06:00',2);

/*Table structure for table `admin_permission` */

DROP TABLE IF EXISTS `admin_permission`;

CREATE TABLE `admin_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `type` varchar(32) NOT NULL COMMENT '资源类型：menu,button,',
  `url` varchar(128) DEFAULT NULL COMMENT '访问url地址',
  `percode` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `parentid` int(20) DEFAULT NULL COMMENT '父结点id',
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Data for the table `admin_permission` */

insert  into `admin_permission`(`id`,`name`,`type`,`url`,`percode`,`parentid`,`available`) values (1,'系统设置','menu','#',NULL,NULL,'1'),(2,'短信协议模板','menu','msg_model_add.html','item:query',1,'1'),(3,'提交','button','msg_model_add.html','item:create',2,'1'),(4,'短信协议列表','menu','msg_model_list.html','item:query',1,'1'),(5,'修改','button','msg_model_list.html','item:update',4,'1'),(6,'删除','button','msg_model_list.html','item:delete',4,'1'),(7,'参数设置','menu','config.html','item:query',1,'1'),(8,'保存','button','config.html','item:create',7,'1'),(9,'权限管理','menu','#',NULL,NULL,'1'),(10,'角色列表','menu','role_list.html','item:query',9,'1'),(11,'修改','button','role_list.html','item:update',10,'1'),(12,'删除','button','role_list.html','item:delete',10,'1'),(13,'管理员列表','menu','admin_list.html','item:query',9,'1'),(14,'修改','button','admin_list.html','item:update',13,'1'),(15,'删除','button','admin_list.html','item:delete',13,'1'),(16,'会员管理','menu','#',NULL,NULL,'1'),(17,'会员列表','menu','user_list.html','item:query',16,'1'),(18,'认证信息','button','user_list.html','item:query',17,'1'),(19,'拉黑','button','user_list.html','item:update',17,'1'),(20,'禁用','button','user_list.html','item:update',17,'1'),(21,'催款详情','button','user_list.html','item:query',17,'1'),(22,'取消拉黑','button','user_list.html','item:update',17,'1'),(23,'题库管理','menu','#',NULL,NULL,'1'),(24,'题库配置','menu','topay_question.html','item:query',23,'1'),(25,'保存','button','topay_question.html','item:create',24,'1'),(26,'题库列表','menu','topay_question_list.html','item:query',23,'1'),(27,'修改','button','topay_question_list.html','item:update',26,'1'),(28,'删除','button','topay_question_list.html','item:delete',26,'1'),(29,'信审管理','menu','#',NULL,NULL,'1'),(30,'认证列表','menu','auth_list.html','item:query',29,'1'),(31,'去认证','button','auth_list.html','item:query',30,'1'),(32,'贷款管理','menu','#',NULL,NULL,'1'),(33,'订单列表','menu','to_passmoney_list.html','item:query',32,'1'),(34,'打款','button','to_passmoney_list.html','item:create',33,'1'),(35,'催款管理','menu','#',NULL,NULL,'1'),(36,'催款列表','menu','loan_list.html','item:query',35,'1'),(37,'延期','button','loan_list.html','item:update',36,'1'),(38,'转交','button','loan_list.html','item:create',36,'1'),(39,'详情','button','loan_list.html','item:query',36,'1'),(40,'记录','button','loan_list.html','item:create',36,'1'),(41,'数据统计','menu','#',NULL,NULL,'1'),(42,'统计报表','menu','index_v1.html','item:query',41,'1'),(43,'信息管理','menu','#',NULL,NULL,'1'),(44,'信息管理','menu','news_type_list.html','item:query',43,'1'),(45,'修改','button','news_type_list.html','item:update',44,'1'),(46,'删除','button','news_type_list.html','item:delete',44,'1'),(47,'优惠券管理','menu','#',NULL,NULL,'1'),(48,'优惠券设置','menu','coupon_list.html','item:update',47,'1'),(49,'添加','button','coupon_list.html','item:create',48,'1'),(50,'修改','button','coupon_list.html','item:update',48,'1'),(51,'禁用','button','coupon_list.html','item:update',48,'1'),(52,'优惠券人员管理','menu','coupon_user_list.html','item:update',47,'1'),(53,'详情','button','coupon_user_list.html','item:query',52,'1'),(54,'广告管理','menu','#',NULL,NULL,'1'),(55,'发布与管理','menu','each_picture_list.html','item:query',54,'1'),(56,'修改','button','each_picture_list.html','item:update',55,'1'),(57,'删除','button','each_picture_list.html','item:delete',55,'1'),(58,'基本信息','menu','#',NULL,NULL,'1'),(59,'极光推送','menu','punsh_msg_list.html','item:query',58,'1'),(60,'推送','button','punsh_msg_list.html','item:create',59,'1'),(61,'修改','button','punsh_msg_list.html','item:update',59,'1'),(62,'删除','button','punsh_msg_list.html','item:delete',59,'1'),(63,'用户反馈信息','menu','appfeedback_list.html','item:query',58,'1'),(64,'详情','button','appfeedback_list.html','item:query',63,'1'),(65,'删除','button','appfeedback_list.html','item:delete',63,'1'),(66,'关于我们','menu','about_us.html','item:query',58,'1'),(67,'提交','button','about_us.html','item:create',66,'1'),(68,'渠道商管理','menu','#',NULL,NULL,'1'),(69,'渠道商列表','menu','channel_list.html','item:query',68,'1'),(70,'修改','button','channel_list.html','item:update',69,'1'),(71,'删除','button','channel_list.html','item:delete',69,'1'),(72,'订单详情','button','channel_list.html','item:query',69,'1'),(73,'会员详情','button','channel_list.html','item:query',69,'1');

/*Table structure for table `admin_role` */

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `id` bigint(36) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(128) NOT NULL,
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，2不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `admin_role` */

insert  into `admin_role`(`id`,`name`,`available`) values (1,'总管理员','1'),(2,'审核员','1'),(3,'放款员','1'),(4,'一催催款员','1'),(5,'二催催款员','1');

/*Table structure for table `admin_role_permission` */

DROP TABLE IF EXISTS `admin_role_permission`;

CREATE TABLE `admin_role_permission` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `admin_role_id` int(32) NOT NULL COMMENT '角色id',
  `admin_permission_id` int(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `admin_role_permission` */

insert  into `admin_role_permission`(`id`,`admin_role_id`,`admin_permission_id`) values (2,1,8),(3,1,11),(4,1,12),(5,1,14),(6,1,15),(7,1,18),(8,1,19),(9,1,20),(10,1,21),(11,1,22),(12,1,25),(13,1,27),(14,1,28),(15,1,31),(16,1,34),(17,1,37),(18,1,38),(19,1,39),(20,1,40),(21,1,45),(22,1,46),(23,1,49),(24,1,50),(25,1,51),(26,1,53),(27,1,56),(28,1,57),(29,1,60),(30,1,61),(31,1,62),(32,1,64),(33,1,65),(34,1,67),(35,1,70),(36,1,71);

/*Table structure for table `admin_user_role` */

DROP TABLE IF EXISTS `admin_user_role`;

CREATE TABLE `admin_user_role` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `admin_id` int(32) NOT NULL,
  `admin_role_id` int(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `admin_user_role` */

/*Table structure for table `agreement` */

DROP TABLE IF EXISTS `agreement`;

CREATE TABLE `agreement` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `content` mediumtext COMMENT '内容',
  `type` int(255) DEFAULT NULL COMMENT '类型(1,借款协议 2,借款服务协议,3用户注册协议,4关于我们)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='合同模板';

/*Data for the table `agreement` */

/*Table structure for table `app_feedback` */

DROP TABLE IF EXISTS `app_feedback`;

CREATE TABLE `app_feedback` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `type` int(33) DEFAULT NULL COMMENT '反馈类型1认证2借款3还款4体验与界面5其他',
  `content` mediumtext COMMENT '内容',
  `img_url` varchar(2550) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_app_feedback_user1` (`user_id`),
  CONSTRAINT `fk_app_feedback_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='用户反馈';

/*Data for the table `app_feedback` */

insert  into `app_feedback`(`id`,`user_id`,`type`,`content`,`img_url`,`phone`,`gmt_datetime`,`upt_datetime`) values (49,1,1,'借款',NULL,NULL,'2017-12-07 16:44:38','2017-12-07 16:44:40');

/*Table structure for table `app_version` */

DROP TABLE IF EXISTS `app_version`;

CREATE TABLE `app_version` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `app_version` varchar(255) DEFAULT NULL COMMENT 'app版本',
  `upload_url` varchar(255) DEFAULT NULL COMMENT '下载地址',
  `details` mediumtext NOT NULL COMMENT '更新内容',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `app_version` */

/*Table structure for table `audit_record` */

DROP TABLE IF EXISTS `audit_record`;

CREATE TABLE `audit_record` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '审核记录表id',
  `admin_id` bigint(20) NOT NULL COMMENT '审核人id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `question_id` bigint(20) NOT NULL COMMENT '审核选项id',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  `answer` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '选项答案',
  `type` enum('一催','二催','总管催收') CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1 COMMENT='审核记录表';

/*Data for the table `audit_record` */

insert  into `audit_record`(`id`,`admin_id`,`user_id`,`question_id`,`gmt_datetime`,`upt_datetime`,`answer`,`type`) values (77,1,1,49,'2017-12-12 14:19:40',NULL,'还钱','一催'),(78,1,1,46,'2017-12-12 14:19:40',NULL,'2017-12-12~2017-12-12','一催'),(79,1,1,44,'2017-12-12 14:19:40',NULL,'2017-12-12~2017-12-12','一催'),(80,1,1,55,'2017-12-12 14:19:40',NULL,NULL,'一催'),(81,1,1,49,'2017-12-12 15:21:24',NULL,'不知道','二催'),(82,1,1,46,'2017-12-12 15:21:24',NULL,'2017-12-12~2017-12-12','二催'),(83,1,1,44,'2017-12-12 15:21:24',NULL,'2017-12-12~2017-12-12','二催'),(84,1,1,54,'2017-12-12 15:21:24',NULL,NULL,'二催'),(85,1,1,49,'2017-12-12 15:40:25',NULL,'肯定换','总管催收'),(86,1,1,46,'2017-12-12 15:40:25',NULL,'2017-12-12~2017-12-12','总管催收'),(87,1,1,44,'2017-12-12 15:40:25',NULL,'2017-12-12~2017-12-12','总管催收'),(88,1,1,57,'2017-12-12 15:40:25',NULL,NULL,'总管催收'),(89,1,1,38,'2017-12-12 17:33:15',NULL,NULL,NULL),(90,1,1,38,'2017-12-12 17:36:53',NULL,NULL,NULL),(91,1,1,38,'2017-12-12 19:14:00',NULL,NULL,NULL);

/*Table structure for table `call_records` */

DROP TABLE IF EXISTS `call_records`;

CREATE TABLE `call_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `phone` varchar(255) DEFAULT NULL COMMENT '对方手机号',
  `call_time_z` varchar(255) DEFAULT NULL COMMENT '主叫时长',
  `call_count_z` int(11) DEFAULT NULL COMMENT '主叫次数',
  `call_time_b` varchar(255) DEFAULT NULL COMMENT '被叫时长',
  `call_count_b` int(11) DEFAULT NULL COMMENT '被叫次数',
  `status` int(11) DEFAULT NULL COMMENT '状态：1,正常 2,删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `call_records_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户通话记录表';

/*Data for the table `call_records` */

/*Table structure for table `channel` */

DROP TABLE IF EXISTS `channel`;

CREATE TABLE `channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录账号',
  `name` varchar(255) DEFAULT NULL COMMENT '渠道商名称',
  `link_url` varchar(255) DEFAULT NULL COMMENT '推广链接',
  `proportion` varchar(255) DEFAULT NULL COMMENT '每单分成利润比例',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `token` varchar(255) DEFAULT NULL COMMENT '登录token',
  `member_count` int(11) DEFAULT '0' COMMENT '总注册会员数',
  `apply_money` varchar(255) DEFAULT '0' COMMENT '总申请金额',
  `out_money` varchar(255) DEFAULT '0' COMMENT '总放款金额',
  `profit` varchar(255) DEFAULT '0' COMMENT '总分成利润',
  `status` int(11) DEFAULT '1' COMMENT '状态: 1,正常 2,删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `channel` */

insert  into `channel`(`id`,`login_name`,`name`,`link_url`,`proportion`,`password`,`token`,`member_count`,`apply_money`,`out_money`,`profit`,`status`) values (10,'xl','xl123','http://120.55.44.117/share/index.html?channelId=xl','0.15','e10adc3949ba59abbe56e057f20f883e','c5b3b7a7-0881-4c2d-94fa-1a8dfa18e2d6',0,'0','0','0',1),(11,'xl1','xl1234444','http://120.55.44.117/share/index.html?channelId=xl','0.15','e10adc3949ba59abbe56e057f20f883e','8752e7ed-c1c4-4ce0-9dba-81fdc75986f5',0,'0','0','0',1);

/*Table structure for table `coupon` */

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `coupon_status` int(11) DEFAULT '2' COMMENT '优惠券状态，默认1表示正常，2表示暂停使用,3表示过期优惠券',
  `valid_time` int(11) DEFAULT NULL COMMENT '有效时长(天)',
  `save_money` int(11) DEFAULT NULL COMMENT '节约金额',
  `type` int(10) DEFAULT NULL COMMENT '1新用户订单2邀请好友奖励3自由发放',
  `coupou_name` varchar(255) DEFAULT NULL COMMENT '优惠券名称',
  `upt_datetime` datetime DEFAULT NULL,
  `gmt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='优惠券,红包表';

/*Data for the table `coupon` */

insert  into `coupon`(`id`,`coupon_status`,`valid_time`,`save_money`,`type`,`coupou_name`,`upt_datetime`,`gmt_datetime`) values (11,1,7,5,2,'邀请好友奖励','2017-12-06 14:01:38','2017-12-06 10:38:13'),(12,1,7,5,1,'新客奖励','2017-12-06 14:02:08','2017-12-06 14:02:10');

/*Table structure for table `delay` */

DROP TABLE IF EXISTS `delay`;

CREATE TABLE `delay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '延期id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `admin_id` bigint(20) NOT NULL COMMENT '催款员id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `delay_time` datetime DEFAULT NULL COMMENT '延期时间',
  `gmt_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `upt_datetime` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '催收类型：1,一催 2,二催',
  PRIMARY KEY (`id`),
  KEY `fk_delay_user` (`user_id`),
  KEY `fk_dealy_admin` (`admin_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `delay_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `loan_order` (`id`),
  CONSTRAINT `fk_dealy_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_delay_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COMMENT='延期时间表';

/*Data for the table `delay` */

insert  into `delay`(`id`,`user_id`,`admin_id`,`order_id`,`delay_time`,`gmt_datetime`,`upt_datetime`,`type`) values (1,1,1,2,'2017-12-20 00:00:00','2017-12-08 17:52:29',NULL,1),(12,1,1,2,'2017-12-28 00:00:00','2017-12-08 17:52:29',NULL,1),(13,1,1,2,'2017-12-28 00:00:00','2017-12-08 17:52:29',NULL,2);

/*Table structure for table `each_picture` */

DROP TABLE IF EXISTS `each_picture`;

CREATE TABLE `each_picture` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `img_url` varchar(2550) DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='广告';

/*Data for the table `each_picture` */

insert  into `each_picture`(`id`,`img_url`,`link_url`,`gmt_datetime`,`upt_datetime`) values (29,'','132','2017-12-08 10:47:02',NULL);

/*Table structure for table `first_catalogue` */

DROP TABLE IF EXISTS `first_catalogue`;

CREATE TABLE `first_catalogue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(55) DEFAULT NULL COMMENT '一级目录名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='一级目录表';

/*Data for the table `first_catalogue` */

/*Table structure for table `help_center` */

DROP TABLE IF EXISTS `help_center`;

CREATE TABLE `help_center` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `gmt_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `upt_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `content` text COMMENT '帮助内容',
  `title` varchar(255) DEFAULT NULL COMMENT '帮助标题',
  `type` int(11) DEFAULT NULL COMMENT '类型 1,借款问题 2,还款问题 3,其他问题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `help_center` */

insert  into `help_center`(`id`,`gmt_datetime`,`upt_datetime`,`content`,`title`,`type`) values (1,'2017-12-05 15:23:32','2017-12-05 15:23:34','123123123213333333333333333333333333333333333','wer',1),(2,'2017-12-05 15:23:32','2017-12-05 15:23:34','123123123213333333333333333333333333333333333','wer',1);

/*Table structure for table `loan_black_list` */

DROP TABLE IF EXISTS `loan_black_list`;

CREATE TABLE `loan_black_list` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '法人名称',
  `card_num` varchar(255) DEFAULT NULL COMMENT '身份证号码/组织机构代码',
  `telephone_num` varchar(45) DEFAULT NULL COMMENT '固定电话',
  `mobile_num` varchar(45) DEFAULT NULL COMMENT '移动电话',
  `issue_money` decimal(40,2) DEFAULT '0.00' COMMENT '贷款金额',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `repay_money` decimal(40,2) DEFAULT '0.00' COMMENT '已还金额',
  `bad_balance` decimal(40,2) DEFAULT '0.00' COMMENT '未还金额',
  `published_money` decimal(40,2) DEFAULT '0.00' COMMENT '逾期总罚息',
  `start_date` datetime DEFAULT NULL COMMENT '借款时间',
  `period` int(10) DEFAULT '0' COMMENT '借款期数',
  `address` varchar(255) DEFAULT NULL COMMENT '住所',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `position` varchar(255) DEFAULT NULL COMMENT '职位',
  `company_address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `overdue_times` datetime DEFAULT NULL COMMENT '过期时间',
  `web_repay` int(10) DEFAULT '0' COMMENT '网站代换次数',
  `max_overdue` int(10) DEFAULT '0' COMMENT '最长逾期天数',
  `creditor` varchar(255) DEFAULT NULL COMMENT '出借方',
  `qq` varchar(255) DEFAULT '0' COMMENT 'qq',
  `end_date` datetime DEFAULT NULL COMMENT '应还款日期',
  `cmoney` decimal(40,0) DEFAULT '0' COMMENT '悬赏金额',
  `overdue_money` decimal(40,0) DEFAULT '0' COMMENT '逾期金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `loan_black_list` */

/*Table structure for table `loan_order` */

DROP TABLE IF EXISTS `loan_order`;

CREATE TABLE `loan_order` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `user_coupon_id` bigint(33) DEFAULT NULL COMMENT '优惠卷id',
  `param_setting_id` bigint(33) NOT NULL COMMENT '利率表id',
  `admin_id` bigint(33) DEFAULT NULL COMMENT '放款员id',
  `limit_days` int(3) DEFAULT NULL COMMENT '借款期限（天）',
  `give_status` varchar(33) DEFAULT '0' COMMENT '打款状态0未打款1打款还未成功2打款成功3打款失败4退款状态',
  `pay_status` varchar(10) DEFAULT NULL COMMENT '还款状态 1正常还款2逾期还款',
  `order_status` varchar(33) DEFAULT NULL COMMENT '订单状态 默认0未申请    1审核中2待打款3待还款5逾期6已还款7审核失败8坏账',
  `lian_pay_num` varchar(30) DEFAULT NULL COMMENT '连连支付商户打款订单号',
  `lian_repay_num` varchar(30) DEFAULT NULL COMMENT '连连支付用户还款单号',
  `bank_name` varchar(30) DEFAULT NULL COMMENT '银行名称',
  `bank_card_num` varchar(30) DEFAULT NULL COMMENT '银行卡号',
  `order_number` varchar(20) DEFAULT NULL COMMENT '订单编码',
  `no_agree` varchar(21) DEFAULT NULL COMMENT '借款协议号',
  `repayment_no` varchar(21) DEFAULT NULL COMMENT '还款计划编号',
  `refuse_reason` varchar(200) DEFAULT NULL COMMENT '拒绝原因',
  `auditor` varchar(11) DEFAULT NULL COMMENT '审核人',
  `interest_precent` double(255,0) DEFAULT NULL,
  `wate_money` decimal(33,2) DEFAULT '0.00' COMMENT '综合费用',
  `save_money` decimal(33,2) DEFAULT '0.00' COMMENT '优惠卷节省金额',
  `need_pay_money` decimal(33,2) DEFAULT '0.00' COMMENT '应还金额',
  `borrow_money` decimal(33,2) DEFAULT '0.00' COMMENT '借款金额',
  `real_money` decimal(33,2) DEFAULT '0.00' COMMENT '到账金额',
  `channel_profit` decimal(10,2) DEFAULT '0.00' COMMENT '渠道商分成利润',
  `agreement_two_url` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '借款服务协议',
  `agreement_url` text COMMENT '借款协议',
  `real_pay_time` datetime DEFAULT NULL COMMENT '还款时间',
  `give_time` datetime DEFAULT NULL COMMENT '打款时间',
  `pass_time` datetime DEFAULT NULL COMMENT '放款时间',
  `limit_pay_time` datetime DEFAULT NULL COMMENT '应还款时间',
  `gmt_datetime` datetime DEFAULT NULL COMMENT '借款时间',
  `upt_dateime` datetime DEFAULT NULL,
  `delay` enum('延期','正常') DEFAULT NULL COMMENT '是否延期',
  `auditor_id` bigint(33) DEFAULT NULL COMMENT '审核人id',
  `out_money_user_id` bigint(33) DEFAULT NULL COMMENT '放款员id',
  `back_money_user_id` bigint(33) DEFAULT NULL COMMENT '催款员id',
  `extend_num` int(11) DEFAULT NULL COMMENT '续期次数',
  `collection_status` enum('一催','二催') DEFAULT NULL COMMENT '催收订单状态',
  PRIMARY KEY (`id`),
  KEY `fk_order_coupon1` (`user_coupon_id`),
  KEY `fk_order_admin1` (`user_id`),
  KEY `fk_order_param1` (`param_setting_id`),
  KEY `fk_order_admin` (`admin_id`),
  CONSTRAINT `fk_order_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_coupon1` FOREIGN KEY (`user_coupon_id`) REFERENCES `user_coupon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_param1` FOREIGN KEY (`param_setting_id`) REFERENCES `param_setting` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='借款订单';

/*Data for the table `loan_order` */

insert  into `loan_order`(`id`,`user_id`,`user_coupon_id`,`param_setting_id`,`admin_id`,`limit_days`,`give_status`,`pay_status`,`order_status`,`lian_pay_num`,`lian_repay_num`,`bank_name`,`bank_card_num`,`order_number`,`no_agree`,`repayment_no`,`refuse_reason`,`auditor`,`interest_precent`,`wate_money`,`save_money`,`need_pay_money`,`borrow_money`,`real_money`,`channel_profit`,`agreement_two_url`,`agreement_url`,`real_pay_time`,`give_time`,`pass_time`,`limit_pay_time`,`gmt_datetime`,`upt_dateime`,`delay`,`auditor_id`,`out_money_user_id`,`back_money_user_id`,`extend_num`,`collection_status`) values (2,1,NULL,40,1,7,'0',NULL,'2','123456789','123456789','123456789','123456789','123456789',NULL,NULL,NULL,NULL,NULL,'0.00','0.00','1000.00','2.00','800.00','0.00',NULL,NULL,'2017-12-11 11:27:17','2017-12-11 11:27:15','2017-12-11 11:27:12',NULL,'2017-12-01 09:50:35',NULL,'正常',NULL,NULL,NULL,NULL,NULL),(3,4,NULL,40,NULL,7,'0',NULL,'2','123456789','123456789','123456789','123456789','123456789',NULL,NULL,NULL,NULL,NULL,'0.00','0.00','1100.00','2.00','900.00','0.00',NULL,NULL,'2017-12-11 11:27:25','2017-12-11 11:27:23','2017-12-11 11:27:21',NULL,'2017-12-01 09:50:35',NULL,'正常',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `login_record` */

DROP TABLE IF EXISTS `login_record`;

CREATE TABLE `login_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(55) DEFAULT NULL,
  `ip` varchar(55) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `phone` varchar(55) DEFAULT NULL,
  `app_version` varchar(55) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型：1(后台登录) 2(app登录)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1251 DEFAULT CHARSET=utf8;

/*Data for the table `login_record` */

/*Table structure for table `msg_model` */

DROP TABLE IF EXISTS `msg_model`;

CREATE TABLE `msg_model` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `type` int(33) DEFAULT NULL COMMENT '类型 1借款成功 2还款前一天 3还款日 4还款成功 5逾期',
  `content` mediumtext COMMENT '内容',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `msg_model` */

insert  into `msg_model`(`id`,`type`,`content`,`title`,`gmt_datetime`,`upt_datetime`) values (8,2,'<p>dsf afasd as&nbsp;</p>','dasfd ',NULL,NULL);

/*Table structure for table `order_extend` */

DROP TABLE IF EXISTS `order_extend`;

CREATE TABLE `order_extend` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(33) DEFAULT NULL,
  `extend_days` int(33) DEFAULT NULL COMMENT '续期天数',
  `extend_money` decimal(33,2) DEFAULT NULL,
  `extend_lianlian_num` varchar(255) DEFAULT NULL COMMENT '续期连连支付单号',
  `repayment_no` varchar(255) DEFAULT NULL COMMENT '连连还款计划单号',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  `status` int(33) DEFAULT '0' COMMENT '0续期未成功 1续期成功',
  PRIMARY KEY (`id`),
  KEY `fk_extend_order1` (`order_id`),
  CONSTRAINT `fk_extend_order1` FOREIGN KEY (`order_id`) REFERENCES `loan_order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8;

/*Data for the table `order_extend` */

/*Table structure for table `param_setting` */

DROP TABLE IF EXISTS `param_setting`;

CREATE TABLE `param_setting` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `limit_days` int(33) DEFAULT NULL COMMENT '贷款期限',
  `allow_days` int(33) DEFAULT '0' COMMENT '容限期',
  `status` enum('使用','禁用','删除') DEFAULT NULL COMMENT '数据状态，1表示可用，2表示禁用，3表示删除',
  `money` decimal(10,2) DEFAULT NULL COMMENT '额度',
  `place_serve_percent` decimal(10,2) DEFAULT '0.00' COMMENT '平台服务费',
  `msg_auth_percent` decimal(10,2) DEFAULT '0.00' COMMENT '信息认证费',
  `risk_serve_percent` decimal(10,2) DEFAULT '0.00' COMMENT '风控服务费',
  `risk_plan_percent` decimal(10,2) DEFAULT '0.00' COMMENT '风险准备金',
  `interest_percent` double(10,2) DEFAULT '0.00' COMMENT '利息',
  `overdue_percent` double(10,2) DEFAULT '0.00' COMMENT '逾期日利率',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_dateime` datetime DEFAULT NULL,
  `first_reminder_time` int(11) DEFAULT NULL COMMENT '一催持续时间(天)',
  `second_reminder_time` int(11) DEFAULT NULL COMMENT '二催持续时间(天)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `param_setting` */

insert  into `param_setting`(`id`,`limit_days`,`allow_days`,`status`,`money`,`place_serve_percent`,`msg_auth_percent`,`risk_serve_percent`,`risk_plan_percent`,`interest_percent`,`overdue_percent`,`gmt_datetime`,`upt_dateime`,`first_reminder_time`,`second_reminder_time`) values (39,NULL,0,NULL,'0.00','0.00','0.00','0.00','0.00',0.00,0.00,NULL,NULL,NULL,NULL),(40,7,0,NULL,'500.00','0.01','0.01','0.00','0.00',0.02,0.01,NULL,NULL,3,4);

/*Table structure for table `pay_feedback` */

DROP TABLE IF EXISTS `pay_feedback`;

CREATE TABLE `pay_feedback` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) DEFAULT NULL,
  `reason` varchar(2550) DEFAULT NULL COMMENT '还款反馈原因',
  `img_url` varchar(2550) DEFAULT NULL COMMENT '图片地址',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pay_feedbak_user1` (`user_id`),
  CONSTRAINT `fk_pay_feedbak_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `pay_feedback` */

/*Table structure for table `pay_order_confirm` */

DROP TABLE IF EXISTS `pay_order_confirm`;

CREATE TABLE `pay_order_confirm` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `loan_order_id` bigint(11) DEFAULT NULL,
  `no_order` varchar(255) DEFAULT NULL COMMENT '疑似订单号',
  `code` varchar(255) DEFAULT NULL COMMENT '验证码',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  `recive_name` varchar(255) DEFAULT NULL COMMENT '收款人',
  `recive_bank_num` varchar(255) DEFAULT NULL COMMENT '收款账户',
  `money` varchar(255) DEFAULT NULL COMMENT '打款金额',
  `status` int(255) DEFAULT '0' COMMENT '状态0未确认1确认再次打款2不再打款',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `pay_order_confirm` */

/*Table structure for table `person_record` */

DROP TABLE IF EXISTS `person_record`;

CREATE TABLE `person_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `all_count` int(11) DEFAULT NULL COMMENT '用户总数',
  `out_order_count` int(11) DEFAULT NULL COMMENT '放款订单总数',
  `bad_order_count` int(11) DEFAULT NULL COMMENT '坏账数',
  `black_count` int(11) DEFAULT NULL COMMENT '黑名单数',
  `out_money` decimal(10,2) DEFAULT NULL COMMENT '总放款金额',
  `member_count` int(11) DEFAULT NULL COMMENT '会员总数',
  `over_order_count` int(11) DEFAULT NULL COMMENT '总结清订单数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='人员统计';

/*Data for the table `person_record` */

insert  into `person_record`(`id`,`all_count`,`out_order_count`,`bad_order_count`,`black_count`,`out_money`,`member_count`,`over_order_count`) values (1,0,0,0,0,'0.00',0,0);

/*Table structure for table `push_msg` */

DROP TABLE IF EXISTS `push_msg`;

CREATE TABLE `push_msg` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(2550) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `link_url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  `status` int(33) DEFAULT '0' COMMENT '0未推送1已推送2已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='推送消息表';

/*Data for the table `push_msg` */

insert  into `push_msg`(`id`,`title`,`content`,`img_url`,`link_url`,`type`,`gmt_datetime`,`upt_datetime`,`status`) values (35,'推送消息','<p>推送消息推送消息推送消息推送消息推送消息<br></p>',NULL,NULL,NULL,'2017-12-07 10:20:38',NULL,0);

/*Table structure for table `push_msg_record` */

DROP TABLE IF EXISTS `push_msg_record`;

CREATE TABLE `push_msg_record` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) DEFAULT NULL,
  `push_msg_id` bigint(33) DEFAULT NULL COMMENT '推送消息id',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2227 DEFAULT CHARSET=utf8;

/*Data for the table `push_msg_record` */

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '题库id',
  `title` varchar(100) DEFAULT NULL,
  `que_option` varchar(100) DEFAULT NULL COMMENT '选项',
  `type` int(1) DEFAULT NULL COMMENT '1审核题库2催收题库',
  `kinds` varchar(100) DEFAULT NULL COMMENT '认证种类',
  `answer` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='催收题库表';

/*Data for the table `question` */

insert  into `question`(`id`,`title`,`que_option`,`type`,`kinds`,`answer`) values (29,'电话','姓名错误',1,'身份证认证','失败'),(30,'电话','电话不通',1,'身份证认证','失败'),(31,'电话','信息完善',1,'身份证认证','通过'),(32,'身份信息','身份信息不完善',1,'身份证认证','失败'),(33,'身份信息','身份信息造假',1,'身份证认证','失败'),(34,'身份信息','身份信息合格',1,'身份证认证','通过'),(35,'银行卡上传情况','银行卡造假',1,'银行卡认证','失败'),(36,'银行卡上传情况','银行卡未上传',1,'银行卡认证','失败'),(37,'银行卡上传情况','银行卡无误',1,'银行卡认证','通过'),(38,'通话录情况','通话记录不正确',1,'运营商认证','失败'),(39,'通话录情况','通话信息残缺',1,'运营商认证','失败'),(40,'通话录情况','通话信息正确',1,'运营商认证','通过'),(41,'通讯录','通讯录和通话记录不匹配',1,'运营商认证','失败'),(42,'通讯录','通讯录信息造假',1,'运营商认证','失败'),(43,'通讯录','通讯录完善',1,'运营商认证','通过'),(44,'还款时间2',NULL,2,'时间填空',NULL),(46,'还款时间',NULL,2,'时间填空',NULL),(49,'还借不借钱',NULL,2,'文字填空',NULL),(51,'还款',NULL,NULL,NULL,NULL),(54,'还钱意愿','不乐意',2,'单选',NULL),(55,'还钱意愿','肯定换',2,'单选',NULL),(56,'还钱意愿','麼用选项',2,'单选',NULL),(57,'还钱意愿','麼用选项',2,'单选',NULL),(58,'有没有钱还款','有的',2,'单选',''),(59,'有没有钱还款','没有一分钱',2,'单选','');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel_id` bigint(30) DEFAULT NULL COMMENT '注册来源(渠道商id)',
  `user_type` int(11) DEFAULT '1' COMMENT '用户类型1普通会员',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户名',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `head_img` varchar(256) DEFAULT NULL COMMENT '头像',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机',
  `money` decimal(33,2) DEFAULT '1000.00' COMMENT '额度',
  `status` varchar(11) DEFAULT '正常' COMMENT '默认1表示正常，2表示黑名单，3表示禁用，4被拒绝(拒绝后，一月之后可借款),5无贷款资格',
  `token` varchar(256) DEFAULT NULL,
  `pay_pwd` varchar(45) DEFAULT NULL COMMENT '支付密码',
  `phone_sign` varchar(255) DEFAULT NULL COMMENT '设备标识',
  `defriend_reason` varchar(300) DEFAULT NULL COMMENT '拉黑原因',
  `refuse_remove_time` date DEFAULT NULL COMMENT '拒绝状态释放时间',
  `auth_status` enum('未认证','认证成功') DEFAULT NULL COMMENT '认证状态，默认0表示未认证，1表示已认证可借款',
  `auth_score` int(33) DEFAULT '0' COMMENT '认证分数',
  `is_pay` int(33) DEFAULT '0' COMMENT '还款状态 0已还款1还款中或者申请中',
  `coupon_all_count` int(11) DEFAULT '0' COMMENT '优惠券总数',
  `coupon_use_count` int(11) DEFAULT '0' COMMENT '使用总数',
  `coupon_past_count` int(11) DEFAULT '0' COMMENT '过期数',
  `bank_auth_num` int(11) DEFAULT '0' COMMENT '银行卡认证次数',
  `taobao_auth_num` int(11) DEFAULT '0' COMMENT '淘宝认证次数',
  `uuid` varchar(256) DEFAULT NULL,
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  `channel_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

/*Data for the table `user` */

insert  into `user`(`id`,`channel_id`,`user_type`,`user_name`,`password`,`head_img`,`phone`,`money`,`status`,`token`,`pay_pwd`,`phone_sign`,`defriend_reason`,`refuse_remove_time`,`auth_status`,`auth_score`,`is_pay`,`coupon_all_count`,`coupon_use_count`,`coupon_past_count`,`bank_auth_num`,`taobao_auth_num`,`uuid`,`gmt_datetime`,`upt_datetime`,`channel_name`) values (1,10,1,'芭乐','e10adc3949ba59abbe56e057f20f883e',NULL,'15512345678','1000.00','正常','30c129d0-05da-4e67-b2bf-e3c6edb68f14',NULL,'','想拉就拉黑',NULL,'认证成功',0,0,0,0,0,0,0,NULL,'2017-11-27 17:15:12','2017-11-27 13:30:49',NULL),(2,10,1,'芭乐','e10adc3949ba59abbe56e057f20f883e',NULL,'15512345679','1000.00','正常','99ae1f50-d11d-4355-883d-02700f2d0629',NULL,'','想拉就拉黑',NULL,'认证成功',0,0,0,0,0,0,0,NULL,'2017-11-27 17:15:12','2017-11-27 13:30:49',NULL),(4,11,1,'芭乐','e10adc3949ba59abbe56e057f20f883e',NULL,'15512345677','1000.00','正常','99ae1f50-d11d-4355-883d-02700f2d0629',NULL,'','想拉就拉黑',NULL,'认证成功',0,0,0,0,0,0,0,NULL,'2017-11-27 17:15:12','2017-11-27 13:30:49',NULL),(5,NULL,1,NULL,'e10adc3949ba59abbe56e057f20f883e',NULL,'18767702333','1000.00','正常','1f2eaa64c967aa72bd5992bd786558b6',NULL,'',NULL,NULL,NULL,0,0,0,0,0,0,0,'519d6d1084e94bedbe468949c6ca4ae8',NULL,'2017-12-13 20:53:10',NULL),(6,NULL,1,NULL,NULL,NULL,NULL,'1000.00','正常',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,NULL,NULL,NULL,NULL),(7,NULL,1,NULL,'b5ea0a99f448e31b57a69cad090ce8dc',NULL,'13884494992','1000.00','正常','ebae778d-b1bf-4336-bf52-86b62bb2caaa',NULL,'',NULL,NULL,NULL,0,0,0,0,0,0,0,'2a5fb107f1d349b5862daa5955cac261',NULL,NULL,NULL);

/*Table structure for table `user_auth` */

DROP TABLE IF EXISTS `user_auth`;

CREATE TABLE `user_auth` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) DEFAULT NULL,
  `baisc_auth` char(1) DEFAULT '0' COMMENT '基本信息是否已验证 0未审核1审核通过2审核失败',
  `bank_auth` char(1) DEFAULT '0' COMMENT '银行是否验证0否1是',
  `phone_auth` char(1) DEFAULT '0' COMMENT '手机是否认证 0否1是',
  `identity_auth` char(1) DEFAULT '0' COMMENT '身份是否验证',
  `zhima_auth` char(1) DEFAULT '0' COMMENT '芝麻是否验证',
  `shebao_auth` char(1) DEFAULT '0' COMMENT '社保是否验证',
  `gongjijin_auth` char(1) DEFAULT '0' COMMENT '公积金是否验证',
  `zhifubao_auth` char(1) DEFAULT '0' COMMENT '支付宝是否验证',
  `jindong_auth` char(1) DEFAULT '0' COMMENT '京东是否验证',
  `taobao_auth` char(1) DEFAULT '0' COMMENT '淘宝是否认证0未审核1审核通过2审核失败3用户未认证',
  `school_info` char(1) DEFAULT '0' COMMENT '学信网是否认证0未审核1审核通过2审核失败3用户未认证',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '审核人id',
  PRIMARY KEY (`id`),
  KEY `fk_auth_user1` (`user_id`),
  CONSTRAINT `fk_auth_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户认证表';

/*Data for the table `user_auth` */

insert  into `user_auth`(`id`,`user_id`,`baisc_auth`,`bank_auth`,`phone_auth`,`identity_auth`,`zhima_auth`,`shebao_auth`,`gongjijin_auth`,`zhifubao_auth`,`jindong_auth`,`taobao_auth`,`school_info`,`auditor_id`) values (3,1,'1','0','1','1','0','0','0','0','0','0','0',NULL),(6,5,'1','0','1','1','0','0','0','0','0','0','0',NULL);

/*Table structure for table `user_bank` */

DROP TABLE IF EXISTS `user_bank`;

CREATE TABLE `user_bank` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `cardname` varchar(255) DEFAULT NULL COMMENT '卡名称',
  `cardtype` varchar(255) DEFAULT NULL COMMENT '卡类型',
  `status` enum('未认证','认证通过','认证失败') DEFAULT '未认证' COMMENT '认证情况',
  `bankcardno` varchar(255) DEFAULT NULL COMMENT '银行卡号码',
  `idcardno` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `birthday` varchar(255) DEFAULT NULL COMMENT '用户生日',
  `address` varchar(255) DEFAULT NULL COMMENT '身份证地址',
  `bank_phone` varchar(255) DEFAULT NULL COMMENT '预留手机号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名开户行姓名',
  `mobile_city` varchar(255) DEFAULT NULL COMMENT '号码归属城市',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bank_user1` (`user_id`),
  CONSTRAINT `fk_bank_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1918 DEFAULT CHARSET=utf8 COMMENT='用户绑定银行卡信息表';

/*Data for the table `user_bank` */

insert  into `user_bank`(`id`,`user_id`,`bank_name`,`cardname`,`cardtype`,`status`,`bankcardno`,`idcardno`,`birthday`,`address`,`bank_phone`,`name`,`mobile_city`,`gmt_datetime`,`upt_datetime`) values (1917,1,'中国银行','中国银行','中国银行',NULL,'5265656565','45454245454',NULL,NULL,'235656232','26232',NULL,NULL,NULL);

/*Table structure for table `user_basic_msg` */

DROP TABLE IF EXISTS `user_basic_msg`;

CREATE TABLE `user_basic_msg` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL COMMENT '地址id',
  `status` enum('未认证','认证通过','认证失败') DEFAULT '未认证' COMMENT '状态',
  `marry` enum('未婚','已婚','未知') DEFAULT NULL COMMENT '婚姻状况',
  `study` enum('小学','初中','高中','专科','本科','硕士','博士') DEFAULT NULL COMMENT '学历',
  `first_contact_name` varchar(11) DEFAULT NULL COMMENT '第一联系人姓名',
  `second_contact_name` varchar(11) DEFAULT NULL COMMENT '第二联系人姓名',
  `first_contact_phone` varchar(11) DEFAULT NULL COMMENT '第一联系人电话',
  `first_contact_relation` varchar(11) DEFAULT NULL COMMENT '第一联系人关系',
  `second_contact_phone` varchar(11) DEFAULT NULL COMMENT '第二联系人电话',
  `second_contact_relation` varchar(11) DEFAULT NULL COMMENT '第二联系人关系',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_basic_user1` (`user_id`),
  KEY `fk_basic_address` (`address_id`),
  CONSTRAINT `fk_basic_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_basic_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户基本信息认证表';

/*Data for the table `user_basic_msg` */

insert  into `user_basic_msg`(`id`,`user_id`,`address_id`,`status`,`marry`,`study`,`first_contact_name`,`second_contact_name`,`first_contact_phone`,`first_contact_relation`,`second_contact_phone`,`second_contact_relation`,`gmt_datetime`,`upt_datetime`) values (1,1,1,'未认证','未婚','本科','姓名1','姓名2','1223121','关系1','5654545','关系2','2017-11-28 11:30:04',NULL),(2,5,NULL,'未认证','未婚','小学','Asddd','Asddd','1 234-567',NULL,'1 234-567',NULL,'2017-12-13 21:11:53',NULL);

/*Table structure for table `user_coupon` */

DROP TABLE IF EXISTS `user_coupon`;

CREATE TABLE `user_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `coupon_id` bigint(20) NOT NULL COMMENT '优惠券id',
  `status` int(11) DEFAULT '1' COMMENT '默认1表示正常，2表示已使用，3表示过期，4表示不能使用',
  `save_money` decimal(10,2) DEFAULT NULL COMMENT '节约金额',
  `invitee_phone` varchar(255) DEFAULT NULL COMMENT '被邀请人手机号',
  `past_datetime` date DEFAULT NULL COMMENT '过期时间',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_coupon_coupon1_idx` (`coupon_id`),
  KEY `fk_user_coupon_user1_idx` (`user_id`),
  CONSTRAINT `user_coupon_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_coupon_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户优惠券列表';

/*Data for the table `user_coupon` */

insert  into `user_coupon`(`id`,`user_id`,`coupon_id`,`status`,`save_money`,`invitee_phone`,`past_datetime`,`gmt_datetime`,`upt_datetime`) values (1,1,11,1,'5.00',NULL,'2017-12-11','2017-12-06 14:00:54','2017-12-06 14:00:56'),(4,1,12,1,'5.00',NULL,'2017-12-06','2017-12-06 00:00:00','2017-12-06 00:00:00');

/*Table structure for table `user_identity` */

DROP TABLE IF EXISTS `user_identity`;

CREATE TABLE `user_identity` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `identity_front` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `identity_back` varchar(255) DEFAULT NULL COMMENT '反面',
  `face_url` varchar(255) DEFAULT NULL COMMENT '人脸照片',
  `identity_num` varchar(30) DEFAULT NULL COMMENT '身份证号',
  `user_name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `status` enum('未认证','认证通过','认证失败') DEFAULT NULL COMMENT '0未认证1已认证',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_identity_user1` (`user_id`),
  CONSTRAINT `fk_identity_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3615 DEFAULT CHARSET=utf8 COMMENT='身份认证表';

/*Data for the table `user_identity` */

insert  into `user_identity`(`id`,`user_id`,`identity_front`,`identity_back`,`face_url`,`identity_num`,`user_name`,`status`,`gmt_datetime`,`upt_datetime`) values (3613,1,NULL,NULL,NULL,'1545242','balabala','未认证','2017-11-28 14:12:42',NULL),(3614,5,'http://47.100.18.109/ATTACHMENT/IdentityPicture/bb150092-6d35-467d-9b0f-77b6d414af40.jpg','http://47.100.18.109/ATTACHMENT/IdentityPicture/af5d43bf-d542-4cf3-a139-194d39c022f4.jpg','http://47.100.18.109/ATTACHMENT/IdentityPicture/ec8c828a-3383-449b-9a70-c524e728b669.jpg','341021198312283992','汪琦','未认证',NULL,NULL);

/*Table structure for table `user_jindong_address` */

DROP TABLE IF EXISTS `user_jindong_address`;

CREATE TABLE `user_jindong_address` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `order_address` varchar(2550) DEFAULT NULL COMMENT '收货地址',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jindong_user1` (`user_id`),
  CONSTRAINT `fk_jindong_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_jindong_address` */

/*Table structure for table `user_login_log` */

DROP TABLE IF EXISTS `user_login_log`;

CREATE TABLE `user_login_log` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `login_time` varchar(255) DEFAULT NULL COMMENT '登录时间',
  `lng` varchar(255) DEFAULT NULL COMMENT '经度',
  `lat` varchar(255) DEFAULT NULL COMMENT '纬度',
  `address_details` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `app_version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_login_log_user1` (`user_id`),
  CONSTRAINT `fk_login_log_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_login_log` */

/*Table structure for table `user_phone` */

DROP TABLE IF EXISTS `user_phone`;

CREATE TABLE `user_phone` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `status` enum('未认证','认证通过','认证失败') DEFAULT NULL COMMENT '状态0未通过验证1通过',
  `use_time` varchar(10) DEFAULT NULL COMMENT '网龄',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phone_user1` (`user_id`),
  CONSTRAINT `fk_phone_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user_phone` */

insert  into `user_phone`(`id`,`user_id`,`phone`,`status`,`use_time`,`gmt_datetime`,`upt_datetime`) values (1,1,'232323','未认证','1','2017-11-28 14:48:22',NULL),(2,1,'95989','未认证','1','2017-11-28 14:48:32',NULL),(3,5,'18258185673',NULL,NULL,NULL,NULL);

/*Table structure for table `user_phone_list` */

DROP TABLE IF EXISTS `user_phone_list`;

CREATE TABLE `user_phone_list` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `name` varchar(11) DEFAULT NULL COMMENT '联系人姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `link` varchar(11) DEFAULT NULL COMMENT '关系',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phone_list_user1` (`user_id`),
  CONSTRAINT `fk_phone_list_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户通讯录';

/*Data for the table `user_phone_list` */

insert  into `user_phone_list`(`id`,`user_id`,`name`,`phone`,`link`,`gmt_datetime`,`upt_datetime`) values (1,1,'联系人1','656523','同事','2017-11-28 15:08:05',NULL),(2,1,'联系人2','2656232','同事','2017-11-28 15:09:19',NULL);

/*Table structure for table `user_phone_record` */

DROP TABLE IF EXISTS `user_phone_record`;

CREATE TABLE `user_phone_record` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) DEFAULT NULL,
  `belong_area` varchar(30) DEFAULT NULL COMMENT '号码归属地',
  `called_times` varchar(30) DEFAULT NULL COMMENT '被呼叫次数',
  `conn_times` varchar(30) DEFAULT NULL COMMENT '通话时长(黑格)',
  `call_times` varchar(30) DEFAULT NULL COMMENT '主叫次数',
  `identify_info` varchar(30) DEFAULT NULL,
  `phone_no` varchar(11) DEFAULT NULL COMMENT '通话对方手机号',
  `another_nm` varchar(11) DEFAULT NULL COMMENT '联通号码',
  `comm_fee` varchar(10) DEFAULT NULL COMMENT '费用',
  `comm_mode` varchar(30) DEFAULT NULL COMMENT '主被叫',
  `comm_plac` varchar(11) DEFAULT NULL COMMENT '号码归属地',
  `comm_time` varchar(20) DEFAULT NULL COMMENT '通话时间',
  `start_time` varchar(20) DEFAULT NULL COMMENT '开始时间',
  `comm_phone_no` varchar(11) DEFAULT NULL COMMENT '电信电话号码',
  `comm_total_time` varchar(11) DEFAULT NULL COMMENT '共计通话时间',
  `comm_date` varchar(11) DEFAULT NULL COMMENT '通话时期',
  `comm_area_code` varchar(11) DEFAULT NULL COMMENT '归属地',
  `call_type` varchar(11) DEFAULT NULL COMMENT '主被叫',
  `type` int(1) DEFAULT NULL COMMENT '1移动2联通3电信',
  PRIMARY KEY (`id`),
  KEY `fk_user_phone_record1` (`user_id`),
  CONSTRAINT `fk_user_phone_record1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户通话记录';

/*Data for the table `user_phone_record` */

insert  into `user_phone_record`(`id`,`user_id`,`belong_area`,`called_times`,`conn_times`,`call_times`,`identify_info`,`phone_no`,`another_nm`,`comm_fee`,`comm_mode`,`comm_plac`,`comm_time`,`start_time`,`comm_phone_no`,`comm_total_time`,`comm_date`,`comm_area_code`,`call_type`,`type`) values (1,1,'杭州','1','3','25',NULL,'5454353',NULL,'22','262',NULL,'232','22','121','122','1212','12','1212',121);

/*Table structure for table `user_school_info` */

DROP TABLE IF EXISTS `user_school_info`;

CREATE TABLE `user_school_info` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT COMMENT '学信网认证id',
  `user_id` bigint(21) DEFAULT NULL COMMENT '用户id',
  `shool_info_detail` text COMMENT '学籍信息（base64）',
  `education_info_detail` text COMMENT '学历信息（base64）',
  `head_img` text COMMENT '头像（base64）',
  PRIMARY KEY (`id`),
  KEY `fk_school_user` (`user_id`),
  CONSTRAINT `fk_school_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='用户学信网认证(学籍、学历信息)';

/*Data for the table `user_school_info` */

insert  into `user_school_info`(`id`,`user_id`,`shool_info_detail`,`education_info_detail`,`head_img`) values (3,1,'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');

/*Table structure for table `user_taobao_address` */

DROP TABLE IF EXISTS `user_taobao_address`;

CREATE TABLE `user_taobao_address` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) DEFAULT NULL,
  `address` varchar(2550) DEFAULT NULL COMMENT '淘宝地址',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  `status` enum('未认证','认证通过','认证失败') DEFAULT '未认证',
  PRIMARY KEY (`id`),
  KEY `fk_tb_user` (`user_id`),
  CONSTRAINT `fk_tb_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_taobao_address` */

insert  into `user_taobao_address`(`id`,`user_id`,`address`,`gmt_datetime`,`upt_datetime`,`status`) values (1,1,'杭州拱墅','2017-12-04 14:59:46',NULL,'未认证');

/*Table structure for table `user_taobao_zhifubao` */

DROP TABLE IF EXISTS `user_taobao_zhifubao`;

CREATE TABLE `user_taobao_zhifubao` (
  `id` bigint(33) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(33) NOT NULL,
  `huabei_can_use_money` varchar(255) DEFAULT NULL COMMENT '花呗余额',
  `huabei_total_amount` varchar(255) DEFAULT NULL COMMENT '花呗额度',
  `alipay_remaining_amount` varchar(255) DEFAULT NULL COMMENT '支付宝余额',
  `gmt_datetime` datetime DEFAULT NULL,
  `upt_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_taobao_zhifubao` */

/*Table structure for table `user_zhima` */

DROP TABLE IF EXISTS `user_zhima`;

CREATE TABLE `user_zhima` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `open_id` varchar(50) NOT NULL COMMENT '芝麻用户openid',
  `score` int(20) NOT NULL COMMENT '芝麻信用分',
  `score1` int(11) DEFAULT NULL COMMENT '芝麻申请欺诈评分',
  `risk_code` varchar(255) DEFAULT NULL COMMENT '欺诈关注清单的RiskCode列表',
  `transaction_id` varchar(200) DEFAULT NULL COMMENT '流水凭证',
  `addtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='芝麻信用';

/*Data for the table `user_zhima` */

/*Table structure for table `you_dun_log` */

DROP TABLE IF EXISTS `you_dun_log`;

CREATE TABLE `you_dun_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `identity_order_no` varchar(255) DEFAULT NULL COMMENT '有盾实名认证商户唯一订单号',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `you_dun_log` */

insert  into `you_dun_log`(`id`,`user_id`,`identity_order_no`,`created_time`) values (1,5,'skd_1512991405376_5','2017-12-11 19:29:58'),(2,5,'skd_1512991504071_5','2017-12-11 19:31:37'),(3,5,'skd_1512991552819_5','2017-12-11 19:33:10'),(4,5,'skd_1512992087963_5','2017-12-11 19:41:24'),(5,5,'skd_1512992159156_5','2017-12-11 19:42:34'),(6,5,'skd_1512992552192_5','2017-12-11 19:49:05'),(7,5,'skd_1512993091886_5','2017-12-11 19:58:05'),(8,5,'skd_1512993684910_5','2017-12-11 20:07:58'),(9,5,'skd_1512995877687_5','2017-12-11 20:38:01'),(10,5,'skd_1512995911618_5','2017-12-11 20:38:33'),(11,5,'skd_1512996129786_5','2017-12-11 20:42:11'),(12,5,'skd_1512996228083_5','2017-12-11 20:43:49'),(13,5,'skd_1512996450289_5','2017-12-11 20:47:31'),(14,5,'skd_1513043422332_5','2017-12-12 09:50:23'),(15,5,'skd_1513043659710_5','2017-12-12 09:54:21'),(16,5,'skd_1513044217225_5','2017-12-12 10:03:38'),(17,5,'skd_1513068345443_5','2017-12-12 16:45:46');

/*Table structure for table `yys_log` */

DROP TABLE IF EXISTS `yys_log`;

CREATE TABLE `yys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `task_id` varchar(100) DEFAULT NULL COMMENT '任务id',
  `created_time` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `code` varchar(100) DEFAULT NULL COMMENT '创建 结果code',
  `message` varchar(100) DEFAULT NULL COMMENT '创建结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `yys_log` */

insert  into `yys_log`(`id`,`user_id`,`task_id`,`created_time`,`mobile`,`code`,`message`) values (1,5,'TASKYYS100000201712141016020681100396','2017-12-14 10:16:02','18767702333','0','任务创建成功'),(2,5,'TASKYYS100000201712141017320661520440','2017-12-14 10:17:32','18767702333','0','任务创建成功'),(3,5,'TASKYYS100000201712141017480310470516','2017-12-14 10:17:48','18767702333','0','任务创建成功'),(4,5,'TASKYYS100000201712141018200711030499','2017-12-14 10:18:20','18767702333','0','任务创建成功'),(5,5,'TASKYYS100000201712141018410681100459','2017-12-14 10:18:41','18767702333','0','任务创建成功'),(6,5,'TASKYYS100000201712141019030681100468','2017-12-14 10:19:03','18767702333','0','任务创建成功'),(7,5,'TASKYYS100000201712141021300681100537','2017-12-14 10:21:30','18258185673','0','任务创建成功'),(8,5,'TASKYYS100000201712141025170720980640','2017-12-14 10:25:17','18258185673','0','任务创建成功'),(9,5,'TASKYYS100000201712141025310661520640','2017-12-14 10:25:31','18258185673','0','任务创建成功'),(10,5,'TASKYYS100000201712141025470711030710','2017-12-14 10:25:47','18258185673','0','任务创建成功');

/*Table structure for table `zhima_details_negative` */

DROP TABLE IF EXISTS `zhima_details_negative`;

CREATE TABLE `zhima_details_negative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `biz_no` varchar(255) DEFAULT NULL COMMENT '芝麻信用对于每一次请求返回的业务号',
  `biz_code` varchar(255) DEFAULT NULL COMMENT '风险信息行业编码',
  `level` int(11) DEFAULT NULL COMMENT '风险等级',
  `settlement` varchar(255) DEFAULT NULL COMMENT '结清状态',
  `type` int(11) DEFAULT NULL COMMENT '行业名单风险类型',
  `description` text COMMENT '负面详情',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `zhima_details_negative_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='芝麻负面记录';

/*Data for the table `zhima_details_negative` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
