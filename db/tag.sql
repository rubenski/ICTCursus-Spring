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
/*Table structure for table `px_tag` */

DROP TABLE IF EXISTS `px_tag`;

CREATE TABLE `px_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `px_tag` */

insert  into `px_tag`(`id`,`name`) values (1,'design'),(2,'access'),(3,'debit card'),(4,'death threat'),(5,'debiel'),(6,'dekens'),(7,'fsgfgdsg'),(8,'gfddfhdf'),(9,'gfsdgdf'),(10,'kljhkdjh'),(11,'kdkdkd'),(12,'abcabc'),(13,'frger'),(14,'htyhy5hty'),(15,'Java'),(16,'Blabla'),(17,'fdewf'),(18,'wefwefwef');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
