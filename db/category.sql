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
/*Table structure for table `px_category` */

DROP TABLE IF EXISTS `px_category`;

CREATE TABLE `px_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url_title` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `url_title` (`url_title`),
  KEY `FKAEE32955C2E4AC1D` (`parent_id`),
  CONSTRAINT `FKAEE32955C2E4AC1D` FOREIGN KEY (`parent_id`) REFERENCES `px_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `px_category` */

insert  into `px_category`(`id`,`name`,`url_title`,`parent_id`) values (1,'Microsoft Office','microsoft_office',NULL),(2,'Excel','excel',1),(3,'Word','word',1),(4,'Access','access',1),(5,'Programmeren','programmeren',NULL),(6,'PHP','php',5),(7,'Java','java',5),(8,'C#','csharp',5),(9,'C++','cplus',5),(10,'Business Intelligence','business_intelligence',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
