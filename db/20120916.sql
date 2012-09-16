/*
SQLyog Community v9.20 
MySQL - 5.5.19 : Database - ic
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ic` /*!40100 DEFAULT CHARACTER SET latin1 */;

/*Table structure for table `articlepage` */

DROP TABLE IF EXISTS `articlepage`;

CREATE TABLE `articlepage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `body` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `publicationDate` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK942CE085EE21D4C6` (`parent_id`),
  CONSTRAINT `FK942CE085EE21D4C6` FOREIGN KEY (`parent_id`) REFERENCES `articlepage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `articlepage` */

/*Table structure for table `articlepage_articlepage` */

DROP TABLE IF EXISTS `articlepage_articlepage`;

CREATE TABLE `articlepage_articlepage` (
  `ArticlePage_id` bigint(20) NOT NULL,
  `pages_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ArticlePage_id`,`pages_id`),
  UNIQUE KEY `pages_id` (`pages_id`),
  KEY `FKB1087FCB9DA0C70B` (`ArticlePage_id`),
  KEY `FKB1087FCBA6035F6C` (`pages_id`),
  CONSTRAINT `FKB1087FCBA6035F6C` FOREIGN KEY (`pages_id`) REFERENCES `articlepage` (`id`),
  CONSTRAINT `FKB1087FCB9DA0C70B` FOREIGN KEY (`ArticlePage_id`) REFERENCES `articlepage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `articlepage_articlepage` */

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `numberOfVisits` bigint(20) DEFAULT NULL,
  `urlTitle` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `urlTitle` (`urlTitle`),
  KEY `FK6DD211EC2E4AC1D` (`parent_id`),
  CONSTRAINT `FK6DD211EC2E4AC1D` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`id`,`description`,`name`,`numberOfVisits`,`urlTitle`,`version`,`parent_id`) values (1,'Programmeren','Programmeren',NULL,'programmeren',NULL,NULL),(2,'PHP','PHP',NULL,'php',NULL,1),(3,'.NET C#','.NET C#',NULL,'c-sharp',NULL,1),(4,'.NET Visual Basic','.NET Visual Basic',NULL,'visual-basic',NULL,1),(5,'Java','Java',NULL,'java',NULL,1),(6,'Python','Python',NULL,'python',NULL,1),(7,'Objective C','Objective C',NULL,'objective-c',NULL,1),(8,'Databases','Databases',NULL,'databases',NULL,1),(9,'Mysql','Mysql',NULL,'mysql',NULL,8),(10,'Oracle','Oracle',NULL,'oracle',NULL,8),(11,'Microsoft Sql Server','Microsoft Sql Server',NULL,'sql-server',NULL,8),(12,'Informatiebeveiliging','Informatiebeveiliging',NULL,'informatiebeveiliging',NULL,NULL),(13,'Business Intelligence','Business Intelligence',NULL,'business-intelligence',NULL,NULL),(14,'Informatica (HBO/WO)','Informatica (HBO/WO)',NULL,'informatica',NULL,NULL),(15,'Computervaardigheden','Computervaardigheden',NULL,'computervaardigheden',NULL,NULL),(16,'Microsoft Office','Microsoft Office',NULL,'ms-office',NULL,NULL),(17,'Excel','Excel',NULL,'excel',NULL,16),(18,'Word','Word',NULL,'word',NULL,16),(19,'Outlook','Outlook',NULL,'outlook',NULL,16),(20,'Powerpoint','Powerpoint',NULL,'powerpoint',NULL,16),(21,'Access','Access',NULL,'access',NULL,16),(22,'Datawarehousing','Datawarehousing',NULL,'datawarehousing',NULL,NULL),(23,'Systeembeheer','Systeembeheer',NULL,'systeembeheer',NULL,NULL),(24,'Linux / Unix','Linux / Unix',NULL,'linux-unix',NULL,23),(25,'Microsoft','Microsoft',NULL,'microsoft',NULL,23),(26,'Netwerken','Netwerken',NULL,'netwerken',NULL,NULL),(27,'Informatie management','Informatie management',NULL,'informatie-management',NULL,NULL),(28,'Project management','Project management',NULL,'project-management',NULL,NULL),(29,'Software architectuur','Software architectuur',NULL,'software-architectuur',NULL,NULL),(30,'Interactie en grafisch ontwerp','Interactie en grafisch ontwerp',NULL,'interactie-grafisch-ontwerp',NULL,NULL);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commentText` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BDE863F1A263DE9` (`course_id`),
  CONSTRAINT `FK9BDE863F1A263DE9` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `comment` */

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `logo` longblob,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `company` */

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `duration` varchar(255) DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pricingInfo` varchar(255) DEFAULT NULL,
  `shortDescription` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK78A7CC3B632D006B` (`company_id`),
  KEY `FK78A7CC3BA326A749` (`category_id`),
  CONSTRAINT `FK78A7CC3BA326A749` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK78A7CC3B632D006B` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `course` */

/*Table structure for table `course_region` */

DROP TABLE IF EXISTS `course_region`;

CREATE TABLE `course_region` (
  `Course_id` bigint(20) NOT NULL,
  `regions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Course_id`,`regions_id`),
  KEY `FK613D1B986535F25E` (`regions_id`),
  KEY `FK613D1B981A263DE9` (`Course_id`),
  CONSTRAINT `FK613D1B981A263DE9` FOREIGN KEY (`Course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FK613D1B986535F25E` FOREIGN KEY (`regions_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `course_region` */

/*Table structure for table `course_tag` */

DROP TABLE IF EXISTS `course_tag`;

CREATE TABLE `course_tag` (
  `courses_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  PRIMARY KEY (`courses_id`,`tags_id`),
  KEY `FKB4DAD76F98A6EC` (`tags_id`),
  KEY `FKB4DAD76574C792C` (`courses_id`),
  CONSTRAINT `FKB4DAD76574C792C` FOREIGN KEY (`courses_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKB4DAD76F98A6EC` FOREIGN KEY (`tags_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `course_tag` */

/*Table structure for table `page` */

DROP TABLE IF EXISTS `page`;

CREATE TABLE `page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `body` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `page` */

/*Table structure for table `region` */

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `region` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role` */

/*Table structure for table `role_userprofile` */

DROP TABLE IF EXISTS `role_userprofile`;

CREATE TABLE `role_userprofile` (
  `Role_id` bigint(20) NOT NULL,
  `userProfiles_id` bigint(20) NOT NULL,
  KEY `FKB40535156203D3C9` (`Role_id`),
  KEY `FKB405351536E3F394` (`userProfiles_id`),
  CONSTRAINT `FKB405351536E3F394` FOREIGN KEY (`userProfiles_id`) REFERENCES `userprofile` (`id`),
  CONSTRAINT `FKB40535156203D3C9` FOREIGN KEY (`Role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role_userprofile` */

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tag` */

/*Table structure for table `userprofile` */

DROP TABLE IF EXISTS `userprofile`;

CREATE TABLE `userprofile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3EFA133E632D006B` (`company_id`),
  CONSTRAINT `FK3EFA133E632D006B` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userprofile` */

/*Table structure for table `userprofile_role` */

DROP TABLE IF EXISTS `userprofile_role`;

CREATE TABLE `userprofile_role` (
  `UserProfile_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`UserProfile_id`,`roles_id`),
  KEY `FKE887F0F791F65C2B` (`UserProfile_id`),
  KEY `FKE887F0F71C4C22` (`roles_id`),
  CONSTRAINT `FKE887F0F71C4C22` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKE887F0F791F65C2B` FOREIGN KEY (`UserProfile_id`) REFERENCES `userprofile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userprofile_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
