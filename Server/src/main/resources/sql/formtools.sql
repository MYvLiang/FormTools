/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 8.0.18 : Database - formtools
*********************************************************************
*/


/*!40101 SET NAMES utf8mb4 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`formtools` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `formtools`;

/*Table structure for table `all_built_form` */

DROP TABLE IF EXISTS `all_built_form`;

CREATE TABLE `all_built_form` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `form_id`  BIGINT COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表单的id',
  `user_id`  BIGINT COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表单创建者的userId',
  `form_title` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表单标题',
  `form_info` TEXT COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表单的信息',
  `built_time` DATETIME NOT NULL COMMENT '建表时间',
  `begin_time` DATETIME NOT NULL COMMENT '开始填表时间（默认为建表时间）',
  `end_time` DATETIME DEFAULT NULL COMMENT '截止时间（可无）',
  `max_count` INT DEFAULT NULL COMMENT '最多可填表的人数（可无）',
  `form_type` CHAR(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表单类型 （问卷：W；登记：D，报名：B）',
  UNIQUE (`form_id`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `all_built_form` */

/*Table structure for table `email_verify` */

DROP TABLE IF EXISTS `email_verify`;

CREATE TABLE `email_verify` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` VARCHAR(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE (`user_email`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `email_verify` */


/*Table structure for table `fill_questionnaire` */

DROP TABLE IF EXISTS `fill_questionnaire`;

CREATE TABLE `fill_questionnaire` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `form_id` BIGINT NOT NULL COMMENT '表单id',
  `fill_content` TEXT COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '填写的内容',
  `fill_time` DATETIME NOT NULL COMMENT '填表时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `fill_questionnaire` */

/*Table structure for table `fill_registry_form` */

DROP TABLE IF EXISTS `fill_registry_form`;

CREATE TABLE `fill_registry_form` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL COMMENT '填表用户id',
  `form_id` BIGINT NOT NULL COMMENT '表单id',
  `fill_content` TEXT COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '填写的内容',
  `fill_time` DATETIME NOT NULL COMMENT '填表时间',
  `alter_time` DATETIME NOT NULL COMMENT '修改时间',
  `file_list` TEXT COLLATE utf8mb4_unicode_ci COMMENT '所有附件的路径集合',
  `check_state` CHAR(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核情况',
  CONSTRAINT DATA_ID UNIQUE (`user_id`,`form_id`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `fill_registry_form` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` BIGINT NOT NULL COMMENT '用户唯一id',
  `user_nickname` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `user_profile` TEXT COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户头像的url',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `user_verify` */

DROP TABLE IF EXISTS `user_verify`;

CREATE TABLE `user_verify` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `openid` VARCHAR(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `verify_type` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE (`openid`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
