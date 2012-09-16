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

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `numberOfVisits` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK6DD211EC2E4AC1D` (`parent_id`),
  CONSTRAINT `FK6DD211EC2E4AC1D` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`id`,`description`,`name`,`numberOfVisits`,`version`,`parent_id`) values (1,'Programmeren','Programmeren',NULL,NULL,NULL),(2,'PHP','PHP',NULL,NULL,1),(3,'.NET C#','.NET C#',NULL,NULL,1),(4,'.NET Visual Basic','.NET Visual Basic',NULL,NULL,1),(5,'Java','Java',NULL,NULL,1),(6,'Python','Python',NULL,NULL,1),(7,'Objective C','Objective C',NULL,NULL,1),(8,'Databases','Databases',NULL,NULL,1),(9,'Mysql','Mysql',NULL,NULL,8),(10,'Oracle','Oracle',NULL,NULL,8),(11,'Microsoft Sql Server','Microsoft Sql Server',NULL,NULL,8),(12,'Informatiebeveiliging','Informatiebeveiliging',NULL,NULL,NULL),(13,'Business Intelligence','Business Intelligence',NULL,NULL,NULL),(14,'Informatica (HBO/WO)','Informatica (HBO/WO)',NULL,NULL,NULL),(15,'Computervaardigheden','Computervaardigheden',NULL,NULL,NULL),(16,'Microsoft Office','Microsoft Office',NULL,NULL,NULL),(17,'Excel','Excel',NULL,NULL,16),(18,'Word','Word',NULL,NULL,16),(19,'Outlook','Outlook',NULL,NULL,16),(20,'Powerpoint','Powerpoint',NULL,NULL,16),(21,'Access','Access',NULL,NULL,16),(22,'Datawarehousing','Datawarehousing',NULL,NULL,NULL),(23,'Systeembeheer','Systeembeheer',NULL,NULL,NULL),(24,'Linux / Unix','Linux / Unix',NULL,NULL,23),(25,'Microsoft','Microsoft',NULL,NULL,23),(26,'Netwerken','Netwerken',NULL,NULL,NULL),(27,'Informatie management','Informatie management',NULL,NULL,NULL),(28,'Project management','Project management',NULL,NULL,NULL),(29,'Software architectuur','Software architectuur',NULL,NULL,NULL),(30,'Interactie en grafisch ontwerp','Interactie en grafisch ontwerp',NULL,NULL,NULL);

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
