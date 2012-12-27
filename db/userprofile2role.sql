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
/*Table structure for table `px_userprofile2role` */

DROP TABLE IF EXISTS `px_userprofile2role`;

CREATE TABLE `px_userprofile2role` (
  `userprofile_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`userprofile_id`,`role_id`),
  KEY `FK1C82E84191F65C2B` (`userprofile_id`),
  KEY `FK1C82E8416203D3C9` (`role_id`),
  CONSTRAINT `FK1C82E8416203D3C9` FOREIGN KEY (`role_id`) REFERENCES `px_role` (`id`),
  CONSTRAINT `FK1C82E84191F65C2B` FOREIGN KEY (`userprofile_id`) REFERENCES `px_userprofile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `px_userprofile2role` */

insert  into `px_userprofile2role`(`userprofile_id`,`role_id`) values (1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
