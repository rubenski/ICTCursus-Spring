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
  KEY `FK9BDE863FCA19FBB5` (`course_id`),
  KEY `FK9BDE863F1A263DE9` (`course_id`),
  CONSTRAINT `FK9BDE863F1A263DE9` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FK9BDE863FCA19FBB5` FOREIGN KEY (`course_id`) REFERENCES `articlepage` (`id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `company` */

insert  into `company`(`id`,`email`,`firstName`,`lastName`,`logo`,`password`,`phone`,`version`) values (1,'rubenski@gmail.com','Ruben','van Loen','GIF89ad\0F\0�\0\0��҂��W���40n���������sp�c`�$ b���SP����D@y\0L���\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0!�\0\0\0\0\0,\0\0\0\0d\0F\0\0� $�di�h��l�p,�tm�x��|����pH,\Z�Ȥr�l:�ШtJ�Z�جv�,�!VpT�\0;��\0,W����B��]tr)w[c#�_	#u�a�`\n��`�\0pb��\n�=�\'y����u��\0\"`��\n\0������<�&�m\r_�_rԐ���\"�\r�w�\n�{���>�$�j\"�8=�\'^���`2�\0�@f� s��`<�m��\0���\0A	$���4(����樏�>Q�2��yl��#`���; ��I��\0��pFCeҽ��\0�t�|���GP2ϰ�A�=t�Щc-\0k�Jh@ΐ[]ƨY��)\0K�|xq�i�Wd7��$g�50 	�#\0؁I�4��J��R7�|-�@W���X\0@2@�\0�~	\0��۸s��ͻ�o\'R�RMh�߉�<~�\0����6���\n� ������.�423>@G>�0@���یɤ�H�0T_jI��Ԑ K�	Tmer��I`��E�\r�	�A\0���H�Lu�T�%cy=p���u�!&�0��#��\"Ѕ@vMs�\0\r8���R+\rDD�b�p�H#ȸ�I)��eR>p����&�$]I�8B7�\\����Xf3cM�Q#�R�K��!ΘG�y(`������A@���q�s�3ɖ����5CF!b�`&�X��!G�gL%�W����cF&\Z\0����u���k)��ܭ��뮼�ڃ�$�C\ZT0	�R���8Q@e(�b�$���l��Dk���s�5�@b�M�B\0(2�H��##:0���5І<��-+� �.1g��	�ܱ�$��F�\"l�sp�4�s�@n��EM��B���\\n��ko	�Gb6#������6\"�i���EǊ`,Bb�l�,��d�*%���ԣ�@�2Rv	�Ʉ,D�AN���&d㜐�P�I-��74մ��#Dm&�Tiuf(-�5�owM��m�u�k�|\"�V\05�P�}\rհ�����un�w�7�tO��I� �ǲ}��Z��\\�Y(o:��q%GZ��S��N���=+�Fѐ�Y�b)��fb��x���v��\\��G�����KJ�E����߷z׾�@~���\Z\0;','test',NULL,NULL),(2,'rubenskicorp@gmail.com','Rubenskicorp','blabla','����\0JFIF\0\0\0d\0d\0\0��\0Ducky\0\0\0\0\0d\0\0��\0Adobe\0d�\0\0\0��\0�\0��\0\0F\0d\0��\0�\0\0\0\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	!\n1\"#$2\0\0\0\0\0!1AQ\"a#q��2�B3��b��C��\0\0\0?\0���(�qJ8�R�)G��Q�(�qJ8�R�)H=��G����[������+�C�B�Օ�r����/�9�������g���\0�s8�Qm�\'&(���uK��^�m�[�^�OX�W}6WV5�~{m�Hf�_0�fU����4Y�!k������ţG24�߀�*��Z�9�3��!2W����\\G3��2px2p8�K�\r���,�_�;�����m�[��9�������|��nF�͉�uId[���hd�m�(�q�FN#��X�#�MW��j�R&��crT�Q���0��{7n3��9�NBG\\e14��6�y\n��B�;���v���R�.�:ڴ�}ҧ����\\u�\0��°��Z7\r�>�m���\r=��s֪�%���k���%������L�k�H�w/���v_s�..`�\"i̳��\0��N�n:�?��U:ۈ��/�V��wKh�Z�O�M��\r�ۍ���ُUpK���R\ru�YHI���An�3d�f����f|J���R\'8.	�Q��W�u����\0+��Lw�����\ZG����)҉6.AfbWh�7�Z�r_W,|M�+}���\'�n�u���k@,���h-E��p�,x��D�GGj*�5��G&�4�\'ix��@��S��&^���Hp ���ln;��8�a���vl�\'x�*���y.��pe*�^��Ee���G�����\r�ڟt+KCD$p����ʒZ��eؚ�I�+;�g����\"D�_D�И�C��T-l�C���МQ<��.�?3�I8,�*��+�$B��Yٝ�����\0=��Ϭ}���y_��H8��(�;�,-wj*o�Ԇ��Kb�&�@�R���gE,���D�q��6����i���g�D�D�)]��/g7ڼ�帼\\��~Q&ei��	c�q�@�33T���{�c�6�������NZ߼!jv�m��Pl*�Vc��r�v�Zopj�y]��Z��%j�L�<�jZ��̀�g�p�ߌ�����w\'�x<���滮���Œ ��w���6�`�Đ<��\'�a�� �sE;]U�����e��w�h�m��1�v:�ٸl��[#&$��d��K�!2Ɋt���A�$�(%A���G�ܝ�8n-y�?%��ߩ09x�@���rۣuk�k�\0�U�]����\\78J�G��V�0�F��)DdΒC��^�R�\0�h#rF�,ό�@X��9��4�09\0��r�͋:d�Y�p���2��> �h@\"�¨\0=���!����[�O[0�u�9���j���J+G4�U.[~���a8@#�R����Y%��*Q����ǌ�9�᱇v�+d�\\ǹ��H1X��wX��5k��\Z�7���\0�=.�6�7���sF���8T��ɝ��N��}y�i�_܈�y�xB��YN(*4K\r�\0�`\Z�;�x��a��Ƕcq�;�M���{G(��ߨ#k\06�/Uh�qdkn����&�y�^�:�e�����Zuyv�J��\Z���e�5��t}���>T��M���r]$)Rs��z�!$i�J_�fA��� ���O����j#��b�\'LY���1n\0^䛇Gi3b�t������Sk�(����-�֫�e�����:��*;.\'�����Ww5�s�����T���ɸ��4�蓌²�j�u>�7)�Ò.//&H2\Z9cl{�x�	u�ek��ccx�=���|G���=�VW`j&�k�v͊����{���z��MH�k2y��j����u��T6sZ9*���*\\Ql�YKI�d��c}�����>�����_C.L�G(K4�v�V,��󴩹20��o�k�ԗ����q�el�����mo�����l�]`:��[�������y���\Z�$\0K�Had�)M��?�V&&\'Ǵ|4S�C=�g�yVa���ڟ�p\'�6��Z��H��O+S6��b��o����-���jW��wPk�-Q>k�L�6��\Z�ߧ�h\\���C俇q�-N��JT�4N�A��L߸~Sq�9��y�%�wyD�X�H����.�pJ�m��\n�����Z�^�ғ�O�|�o~�ڵk{����ja\\[M���W)z���z�kF��kbfd��vLU���5���)J@�ؤ##;\'�Ds����1N?!�\r�;dJ�D1�}M�U:l�����0�n#�<��N���ܲ�.˕�����+���?V�u�F)-XjkF̬��V�M�E��:�\ZH2�;��}���3%$�No�;���L,2�L�1�2l�I��cTO�%Y�\0�.ۧe\r؃���ߜ5^��Q�(�D=���sj��k~���iJI�\Z�ej.���d�k^Ц���\Zn�ダ9Fp�_�*��(�%J\"q�4�>��ݝ��p�|�\"���L��0�\Zs�n26�֌\r4RI���K)G�A#��T��M����[�=�G�-I6��I㲙�V�o�Pvt=�`�5*����@���RvV�#򕄈O��@��{s^�N#�Z|�4jʍI�b��RC�b]��㡩+��������R�]�i�.���kݞ�jH��q�+d>׭e��y;q��lS��o��Qx��@bp�{Zt\n��� �w�����px^R�,�4�Z9Ƨ攑�fE��1 ��Ti�au7�A�l��P��ZXl��G%�7\Z+9�fs�\n��X��lH����\"8j��V$�Ef�Zı�\n�	�*>�z>[�nB���q:b�:\"$�@�)&�I�h���°,o��\Z���k\\�^�gZ�թ�-����i���W�VH-��|��#�\ZC��/lP�dym���R\nVie�xɀ�p���ʓ�c6$�~>1&@���+�1;�n�ᴒ@&�Uެv�7�jv�m�x�N�V	�Ʒ&���Қ*I�ז�5Ͱ���7�lv^��L�le�e�w9��Fr�#�4��N{���=#��e����\'L�����P1�C�-c�ѯ����g��:��e�O[Ol׫�L����mSb�ݥ��O�/q�&|ž�kIZ#�;�@x2N���(#�}��R�p��HyH;�\n\\+�ݵ��*H��iՌ({�Mi���ząW(ڦr-��p�^�F&g��a\"�ui�M]�E��9��M�	ĞY��\0�ar!�}�a1�W���k$wޑ��s-��[�A5C4B�>#��έ\"��Ҧ\\�Ju���%Z5�-BUiT��L���ӞP� 9�D�8�q�q̬�U�\r�> ���r���Q�(�D��V-`[��uRݠhr���WL����l�;y�z����kA��*���X�̓J�r\'R�?N����b��c�r8~���ƂXm�7H�;�]��\0\\��(bhh��ު����w�\Z,�Z)��Y^v�M�3��fK6��gj��!�mq�iU�������!;�K�6�cra+Q����\\������}NKL�����,2e�Q:�\0{%��{�Ѵ��M����H��Mw�zfݖ���U{�wK��y_��Ml��m�v�\\&�6I����%6q��f4�1�Q��y6g�3)��p=O���nc��bc�K�4��8P ��d=��}�m���#�Y�k����<�W����~���ƵI�f�L�9��lL>	=����ǣ�*����f��E���~W�h\\��B`����p��೸���&�Lw��I�gI\nȑ�#�6�2�l.�z5�U�#{Ҫ���Cy��Gb���������J	�r�4���zR���x業��\r^�\Z%RTl��1��\n�&,\n�b}������es��_JR�!�1C,��g���2�m�ۛF�[����I�L�~�{FQ\ZQ�֗�ܾ�i�5`ӛ���v�>��B�­���S	N2�[%f�)�HJP�1*%\')rN�N���G��>v:�6.?�QM.?B�7B�r�щR����yt�U]����s�v�xc���\r[ek��;�vO�]n�>\\�%���_X���g�*Y`�q�I�2����*��݁����n���٘�w�+��4DE*=�1�h��\0l��PG���ʶM��sn�cx%��G�/\r�����P:�\n@Eb���ջN�#(�\rhb�c�1��@��b��E�v�w�8�8�r�119*I�3��;��uVL~�*�&��B.u&��x�{	V����S�d����:i\\򆤨�z�^��M^r�\rf��˼��8���<�Տ!�m��^o���##���<�cr�F{[t��!��w-�]��t]ʹisk�Z���Ss�5Z8�Wf�v[Oht�^+�M�7e��?�_�u�S��3���6���}>6?���	�+��W�F�Y��E��g������\\l��i���0�]Wȗ���YS�A]YHԍJ�rj��# I>��VsJ�׍�_iD�TV�ipR--����5h�M^u�r�Uu��$^�!f|$��*8��A�N��2�;]�ڜ�n,3�&���<	a}�0W_1��D�^�\\�t5=��U�8�R����\rO���Y��\\������t�tL���`J�W�ʴ�;��I�C���b�~D�,y��Y\0w]��f��17	Ȕ9�lݰ�_z,�ĀO���\Zޢ�$P��jTsKR��Q�(�qJF_�%Lk^+/�fiጳ�2M�cs���5�f���kB̅z��{q��Ҧ$�PiDa���O!�L�qљ$���\0,q��H�̛\0	 TY�E�֞|�T��Wq�Kjol=\r��N�Vt��\0P��7t@�腓�Z�XB�>t1�Ǘ��\0%(��H�1�Ѩ��� ��a�\\�7�=��qq���?j;g��V h�N��ֱ�eIcf�{�ګ�\0h�KOv�^����d���:}h�����Oa۷s�� �g����d����\0&��$iLX�o�ᙐ�Gt�6oq�?j��Es%�NSDΏ҄@bA-�*��mm�؋�\Z5��f�+�%���J�����ڻܠu���(�)tu������Hw�#�^���=����������jNMrlR���Z`�Ȑ�V���f�r|�6[���h$�\\T�j>l�ndlgO��\nop6�D��U#i]m�񦖐X;��o��C�G������z����k���hn�K!4���In2@�`�J�N�2ǟQ\08]ŋ�]�1���6�9�V�Z���#$g�w;�A�Cz���Ȥ�IN�ҷ8���>R�e��Q����0���׵�dp���n�sh	��is@����s��y*\r,iP��0��Q���]8.N>C%�FLCG\\X���&��I��`��\rX)�E ��A������҇P�a?���{c[����Ռ�k�q;P��Y<~�t����(���c^�JQ�Ml�Vj��TiM*W�n\\�Ɋ�?�L����&�[��<��ZQ�/�0���k���ME-ӌ?����mz�_R�Q�3��O�n�]\\�w{�-ZW)�H��	�C\Z���m��#9X���i�&#�њP\\~�!7Ҽd\r/?�ǎ{Żw$�,{z���g[l�\0.�v�u����m~��o�jf[�4�z�)}e�V��zm�^� Rs�>ۺ�e�F:ⴔ?-Y�(YU�\n�8[�n\\F���!�&\nr�v����O\'(P�S�ۨ��I�{����^��N����ַ\'\r�W}�Qn�9c�U;��m�eÜ�kIkM����ⴏ^vZu2��c/���x\Zv���	�(��������ۉ��蹱�Yč�q�3 �����F`7[V\ZEW����ӿ��QfO9�X5�NG��i�p��ײ6\'�O�f�dK_��.��ߝޮx��ӈ���ǁ�Fy�NY��q����o��ώeCH�i�3F�l��&��P��p�r I!7�f�;��\0���s�V�>\'�f)M��%�Bİ7-��h��{�Ζ�&��1���M~��ZZ�-I�)�G��b������5)�*9�2�ß��31��,R�d��������,IG�-��\"V7�_\n�W�HE��e`�fE�5�N?�tr�����\'��Z�>&\Z��`!x_r8�\0�6 4���=�M%J�DI�r\\�ܮ&L,/��M#��l��ck[����{��!E���R?�I�\n�}�Me�~k�_���o�S��?����_�>��?�}W�����|�\'��\0��{�\0ߟ7l��\0�������v��\0QԶݖ�mk�kiY�ݟ��T�梥G�sk;C0�����֞�\r�S!�%X/\n\\�)EaB�(80���<�Ϧ97�䱑�,.oa�>\nVG��c���7*pZ��܅k�ŨuV�\nd���\0��P\rZq`p��q����H����	��=)\\�Q\"rH��D�Ь$iբZ��I\'49	�)Lx$�L}!�?�9fF��hE*&m֕T��\r��S��,�F�%A�0����l��4��_bx��VR��Z��R�%\"����Dzg9�p}���ddd�,o&N,�7P1�J\0b6���hI#�\Z��p�7�*X�o@�Hӷ!F�A������XpԫR2�`�J\r�8�bȅ��9�4����I6���<?J�w9\ZV9ѝ��.=5�<\"��(�GD)��A��~�(�q#�@/Ops��<�H�ѱV���J��������7:!F�ެ���\n�ĬF����J��^s����~���#BC44�O/�����/����_��|o��x�\0��==����Ӕ�۷\\���zWc��R�)G��Q�(�qJ8�R�)G��W��','lkflek',NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `course` */

insert  into `course`(`id`,`duration`,`lastUpdated`,`name`,`pricingInfo`,`shortDescription`,`version`,`category_id`,`company_id`) values (1,'lkjlkj','0000-00-00 00:00:00','Office algemeen',NULL,NULL,NULL,16,1),(2,'kjlk','0000-00-00 00:00:00','Office algemeen 2',NULL,NULL,NULL,16,2);

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

/*Table structure for table `experience` */

DROP TABLE IF EXISTS `experience`;

CREATE TABLE `experience` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK71B8358A1A263DE9` (`course_id`),
  CONSTRAINT `FK71B8358A1A263DE9` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `experience` */

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
