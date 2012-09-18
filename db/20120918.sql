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

insert  into `company`(`id`,`email`,`firstName`,`lastName`,`logo`,`password`,`phone`,`version`) values (1,'rubenski@gmail.com','Ruben','van Loen','GIF89ad\0F\0Ä\0\0À¿Ò‚€¦Wïïô40n¡Ÿ¼±¯ÇĞÏİsp›c`$ bàßéSP„‘°D@y\0Lÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0!ù\0\0\0\0\0,\0\0\0\0d\0F\0\0ÿ $dihª®lë¾p,Ïtmßx®ï|ïÿÀ pH,\ZÈ¤rÉl:ŸĞ¨tJ­Z¯Ø¬vû,‚!VpT’\0;¾\0,W¹…ğB”ì]tr)w[c#‰_	#u’aˆ`\n‹`¢\0pb‰Ÿ\n‚=„\'y†²†u–Ÿ\0\"`³†\n\0­Ä“À³—<°&“m\r_ˆ_rÔÇÁ‡\"à\r‹wÇ\n‹{ÖÀÌ>Ù$Çj\"ş8=ˆ\'^€ƒ„`2–\0Î@fÂ sĞ×`<¬m‚à\0Á€€\0A	$à˜€4(Ñÿ‘œæ¨ˆ>Qˆ2¨yl‰ #`À»; €ğI”¡\0‰pFCeÒ½¸ \0°tÔ|úƒÑGP2Ï° A³=tÄĞ©c-\0k‚Jh@Î[]Æ¨Yµ‹)\0Kÿ|xq³iÀWd7¿°$g€50 	ì#\0ØI 4ÂJ²¥R7‘|-â@W¦ÿ”X\0@2@Ò\0¨~	\0öšÛ¸sëŞÍ»·o\'R¹RMhéß‰Ü<~â\0µ’ÿ6„Èä\n ‘·åÚõ€.‹423>@G> 0@—³ÒÛŒÉ¤ÊHŸ0T_jIÀñÔ Kê€	Tmer†ÿI`À†E§\r‘	ÆA\0”äõHàLuÈTÌ%cy=pÀääuÎ!&€0ÙÀ#’†\"Ğ…@vMsÎ\0\r8€™ÇR+\rDDˆb‰pÔH#È¸áŒI)ĞÆeR>pÆ½´äŒ&Õ$]I™8B7ß\\’ØÍXf3cMòQ#ÖR”KÙ„!Î˜GÍy(`€ŸŒ‘¦µA@×êõqsĞ3É–µõÖ5CF!b‘`&€XÙÁ!G˜gL%ÖWÎÀ™cF&\Z\0²Àêu¦Àšk)¤†Ü­¸æªë®¼öÚƒ—$©C\ZT0	—R¨Şü8Q@e(ÀbÀ$ÂŞĞlÿ÷Dk¤Óüs€5@bŞMÄB\0(2®HÛÈ##:0©Ÿè5Ğ†<™Å-+® à.1g·Ø	üÜ±À$´¡FË\"lîsp©4Êsµ@nı¡EMº´BíÂÕ\\nÁúko	´Gb6#±™Ùæãğ6\"i½–EÇŠ`,BbËl¢,ÈÀdğ¶*%¨§–Ô£”@ı2Rv	ĞÉ„,DÑAN­Ğ&dãœÖPÓI-ö€74Õ´ˆ“#Dm&Tiuf(-¢5ËowMô’mˆu€kÖ|\"ÙV\05ùPŠ}\rÕ°¡ˆö—ÜunÌw7ÉtO¢“Iô ŞÇ²}ğ×ZŒ„\\ÖY(o:­™q%GZ£ÿS¹ÏN¡”®=+ÌFÑğY¢b)òˆĞfbµîxœ ÿv¶Ó\\ËÂG±¢ÀšKJùEÑêÂõß·z×¾¾@~ùèû\Z\0;','test',NULL,NULL),(2,'rubenskicorp@gmail.com','Rubenskicorp','blabla','ÿØÿà\0JFIF\0\0\0d\0d\0\0ÿì\0Ducky\0\0\0\0\0d\0\0ÿî\0Adobe\0dÀ\0\0\0ÿÛ\0„\0ÿÀ\0\0F\0d\0ÿÄ\0—\0\0\0\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	!\n1\"#$2\0\0\0\0\0!1AQ\"a#q‘2ÁB3¡±bğá‚CÿÚ\0\0\0?\0÷ñÅ(â”qJ8¥R)G£ŠQÅ(â”qJ8¥R)H=¬ºG­º»²[¿ƒ î+¤CÎB¡Õ•äŠr‘¥Îå–/‡9Á¥çĞıüãgÂñÿ\0Ës8œQm£\'&(¯éÔuKùø^õmª[Ğ^¨OXôW}6WV5“~{mÛHfã_0ÚfUÆßÒË4Y¢!k´°Ï¨‡èÅ£G24Àß€„*€àZá9–3° !2W¥óÉÛ\\G3™Û2px2p8ÒK\r¹…£,_ª;šåÖöÛmº[Æö9›Îó¯Ãô©§|ö•nF¯Í‰ uId[–å¥ğhdÇm¦(î¨…ÇqFN#õåXÛ#MWÛÖj¨R&µ’crT‚Qš»Ì0Ÿã{7n3“ç9ÀNBG\\e14ÅÂ6Æy\n²£B†;‰ıÁv‹ÔÚRª.í¾:Ú´µ}Ò§¸¥ú•\\uÿ\0«òÂ°öƒZ7\rÊ>ûmÄõõ\r=¯­sÖªŞ%ÒöËk„ßŠ%±°´ñ¦ü¢L„kÀHÂw/¯Ûóv_sæ..`Å\"iÌ³•ê\0ª¥N™n:í?¶úU:Ûˆ‹’/é¥V‰wKh×ZO²M¢“\r—Û¯ÜİÙUpKúúŒR\ruÕYHI‘»ÈAnİ3dÓfªõª»f|JØÚÜR\'8.	©Qû†WûuİÉöÿ\0+›LwƒÂãñ\ZG†˜¼“)Ò‰6.AfbWh³7Zr_W,|M¼+}ÜŞŞ\'×n£uó±ºÀÉk@,¦¾ğh-EØıp¯,xÚéDÂGGj*²5ˆöG&ø4ö\'ixŒ¢@¨ãSµ¹&^ŒãÂHp ƒ€ìln;œå8®a¡—»vl˜\'xØ*«˜úy.ßpe*À^¤óEe½÷€Gô©ŞÙÜ\r¬ÚŸt+KCD$p­¾ÓæÊ’Z¢eØšÚI²+;­g‡´›¶\"DÁ_DâĞ˜ÆC‘ÊT-lÙCè€´ĞœQ<Ûö.?3’I8,ó*õŒ+Ç$BíYÙ¿õ€Şÿ\0=¢ÄÏ¬}À¯¼y_×ãH8Çö(ƒ;¹,-wj*o×Ô†ƒKb”&Ë@¶RŸ²ÙgE,’èÇDâqèÊ6æ×æõiäæÒgàDœDú)]¢/g7Ú¼Ÿå¸¼\\¦ú~Q&ei±Ş	cúqº@Ğ33Tƒ›ß{è¶c6³ªÛÀßÇãNZß¼!jvÚmÆÁPl*ßVcÙrİvÚZopj«y]€­Z²á%Âj°L˜< jZŞòÌ€”g¬p¤ßŒà—¯Ëûw\'óx<’Òåæ»®ÙñåÅ’ €–w÷İ6†`ÊÄ<Åä\'öa ô ÓsE;]U´»ù©÷eÁ®wÈh†m’…1Ãv:½Ù¸lº¯[#&$şdêÇK´!2ÉŠtš•A€$Ñ(%A©ÁƒGƒÜ”8n-y¾?%²øß©09xÖ@»”„rÛ£ukÜk¡\0éU]í±…š×ñ½\\78J½G£ŠV»0‰Fçñ)DdÎ’CšÇ^âR¶\0Œh#rFÅ,ÏŒë@XË’9µ¬4“09\0óéœrìÍ‹:dã±YãpÊÃÄ2›‚> €h@\"ÇÂ¨\0=ÜæÁ!š”»´[ñO[0„uú9©èêjõ†ØJ+G4ŠU.[~ÀğÛa8@#ïˆ‹ÊR²Ğ¤Y%– *QéßşÇŒ™9µá±‡vË+d™\\Ç¹”£H1XÀêwX›’5kãô\ZÛ7˜òÿ\0½=.î¯6´7¾‘îsF®¥Ş8T³°ÉšíNŠú}y¶i—_ÜˆĞyêxBƒYN(*4K\rÿ\0˜`\ZŞ;¼x³Åañ½ÃÇ¶cqÒ;ãºMÑÑÛ{G(é¾ôß¨#k\06/Uh›qdknñÒõ§&éyÛ^æ:…eõë²ßÆûZuyv•Jİí\Z¥ºøe·5õêt}¬â©Ş>T¢ºM’’Ùr]$)RsÂÜzå!$i‹J_ÄfAïôå ÎÄîŒO«ÅËÌj#Âbœ\'LY¶½ã1n\0^ä›‡Gi3b½t¨àƒúëæSkâ(¦ÍÂì-‚Ö«ãe­¸¥ƒ±:»µ*;.\'´Š›ŸWw5¾s–ùŠ´¦Tı½É¸ÄÎ4ôè“ŒÂ²›jßu>£7)§Ã’.//&H2\Z9cl{ìx¦	uäek­ccxı=€±÷|G¯¨©=éVW`j&ªkŞvÍŠ¼´õÛ{ëİóz¼êMH¦k2yÜ¾jÚÙ‡ÔuÂÈT6sZ9*›ŸÅ*\\Ql¤YKIÉd§Öc}ÁƒœÍå>…¥ÂÊã_C.L²G(K4²v¹V,‹Óó´©¹20’o¨kŞÔ—¹úµ¶q—elŠİôÛmo™ş¶É¦lúî—]`:®µ[Œ›’ëó…œø¦yº­\Zù$\0KÊHadŒ)MÜş?îV&&\'Ç´|4S©C=çgÉyVaØèÔÚŸ·p\'Ü6ÄÀZå›ŞHòÓO+S6©ébç®Òoô€ãÄí-øëjW§ÚwPkè-Q>kƒL¢6ÌÃ\Z©ß§Óh\\ºš’Cä¿‡q-N•ÁJTù4N¾AæŒLß¸~Sq‘9ŸŒyì%ÊwyDåX³H¨²«.åpJ‚m²À\n¨„ŞíZŞ^ŸÒ“ğOë›|Šo~ÁÚµk{®èÒĞja\\[Mµ¹‡W)zëöæzÉkF¬²kbfd’ëvLU±ÀóŒ5¸Œ§)J@ƒØ¤##;\'î®DsñÍÅÃ1N?!å\r•;dJıD1´}M¨U:lÊ¸ŞÍå­0³n#Ü<……Nş½ºÜ²õ.Ë•Ú÷¯­Ö+úúÚ?VÅuïF)-XjkFÌ¬¥VŠMûEŒı:\ZH2æ;ºà}¸ö 3%$ÊNoº;³›ÄL,2âˆLÒ1Ÿ2l‚IªÛcTOí%Yÿ\0ä.Û§e\rØƒ§­ßœ5^£ŠQÅ(â•D= ö…sj¾Õk~ÒšiJI¯\ZÚej.ØŞÀd–k^Ğ¦Œ¾“\Zn«ãƒ€9Fp²_*•¯(Ò%J\"qì4Õ>“ÙİÍp¹|÷\"¹ùãL‘ˆ0•\Zs¸n26ğÖŒ\r4RI¿€¬K)G»A#ÄøT¦×MèšÇô¾[³=˜Gê-I6°˜Iã²™ìVÆošPvt=æ²`÷5*üÍş@á´ôRvVÕ#ò•„ˆOœœ@¥å{s^àN#´Z|á4jÊI£bø¥RCÇb]‡°ã¡©+›¥°·éøŠRİ]Ùi».’í¶ÖkİÉjHõ–që+d>×­eÈìy;qÅÔlSÊêoŠÚQxÌò@bpè{Zt\nü†¨ wö÷“¸px^R†,É4‘Z9Æ§æ”‘£fE½Ô1 ØåTi“au7µA­lşÃPµÏZXl³õG%°7\Z+9°fsÚ\n¶ØXŒ—lHÙøÚæ¶\"8j±çV$öEf§ZÄ±É\nâš	Â*>Èz>[ínBÇÉæq:bà:\"$Ò@Ï)&òI½hˆ±ÙÂ°,o·Ä\Z‚ä¨k\\ú^®gZçÕ©ê-“„›İiÊÊ×W†VH-éÃ|´¨#ƒ\ZCãÅ/lP‚dym§˜æR\nVieŒxÉ€÷pÙıÊ“æc6$~>1&@ºü¤+¼1;¬n¾á´’@&ÚUŞ¬vú7…jv—mıxÓN“V	îÆ·&“×ÖÒš*IŒ×–í…5Í°ÜÉö7¸lv^×ò™LÕleŒe©w9•‚FrÏ#Í4ŒN{±û§=#—˜eƒ¬¬Ï\'L¡ÙİÕP1ÑC•-c´Ñ¯‰Öö®gÛ:çë­e¶O[Ol×«†Lñ¯¬•mSb›İ¥ñôOÎ/q‡&|Å¾ÍkIZ#’;¢@x2NûŒ(#¤}‘İRò³p‘áHyH;Æ\n\\+İµ”–*H×àiÕŒ({ûMi²êzÄ…W(Ú¦r-úÚpÅ^ÜF&g«ìa\"Äui©M]œE•É9ˆ”M…	ÄY¸ÿ\0Œar!û}Şa1‚W‰½ñk$wŞ‘ûşs-Ä[ìA5C4B×>#ş¿Î­\"´«Ò¦\\…Ju¨–§%Z5‰-BUiT“©L ‘£ÓPğ 9ÈDã8ÎqqÌ¬¬U\rˆ> ÕÚìr”£ŠQÅ(â•DıªV-`[•áuRİ hrÚÑåWLœª·‡l…;y©z÷§´¡kAõ¥*ˆ›ÄX”ÍƒJÆr\'Rê?Né—™ÄbàÊcår8~ä²üÆ‚Xmş7HÁ;ƒ]®à©\0\\š±(bhhíùŞª±éów¢\Z,»Z)š³Y^vËMö3®İfK6²Ægj¦ê!¨mq×iUØöäé­‹‡!;µKÕ6–cra+Q…‡‚»\\ÎûíÙûŒ}NKL²ğ’àÏÈ,2e—Q:Ä\0{%¶è{Ñ´Ú¸M¬÷ô½H»ËMw›zfİ–íúÍU{ÖwKŸªy_Ô¶Mlºm¡vÏ\\&6Iö­Š¾%6q¬¡f4¹1–Qº¾y6gæ3)“ûp=OÏöçncñæ®bcóK›4é¢8P §d=ÇÌ}©mÚš“#ÈYík¥€õ§<ÛW¶ş£Ø~ˆ¯ØÆµI®f½LÕ9¦¶lL>	=ªå”ÒÇ£«*ñÊó¥ófãôE‰ù¥~W®h\\àÉB`ü©p£˜à³¸®äã&ËLwÎÍIàgI\nÈ‘Í#6£2±l.¤z5ªUÃ#{ÒªÇèCyíâGb©£õ »²öéÑJ	årÖ4÷ÕÍzRÛ˜«xæ¥­‚—\r^²\Z%RTl¨1ÓÉ\n¬&,\nÚb}ËíØğøéesü_JRé!ù1C,‰ÙgİÔÚ2äm¶Û›F»[ö‹•üIŸL½~ö{FQ\ZQÖ—ì‚Ü¾÷iû5`Ó›¢„¨v>ÁìBÄÂ­ˆ‰ßS	N2Á[%f“)‰HJP¡1*%\')rN²NçìşG’ä>v:Å6.?ÙQM.?Bó7B«rä’Ñ‰RÀ‘¥ïytåU]¤–µ¯sñ¯½vê—xcšã×\r[ek€’;ë¯vOÛ]n³>\\µ%†‘¯_Xöö×g—*Y`«q°I×2”æ•öÕ*ˆç¶İ‰Ê÷¯nËÊòÙ˜™w+·†4DE*=€1íh£å\0l‹…PGƒßÇÊ¶Mßësn‡cx%²»Gö/\r¤šĞë¶ÑP:ı\n@Ebœ˜Õ»Nš#(”\rhbËcÎ1´«@‘übÉ“E”v»w»8à8¨r²119*I‰3ãÍ;òuVL~“*ï&ÁÖB.u&ÃÜxß{	V·õ¯S´d’”¡é:i\\ò†¤¨ëzÉ^äÆM^r’\rfŠ§Ë¼Èæ8ÉÒ×<’Õ!Ìm­â^o¸ü¦##øã<cr–F{[tóÉ!²ìw-¢]¶t]Í´iskÖZªĞSs˜5Z8¥Wfñv[Ohtç^+ÕM³7e•´?»_´uî¯Sçİ3—¿Ù6ˆ”‚}î‰¶>6?øÖ	+±á§WíF‘YÆüE‘‘gªíŞÑÏî\\l¬Ìiğññ0ú]WÈ—¤ƒªYSÜA]YHÔJrjÛÈ# I>šøVsJû×è_iD«TVµipR--öö¾ì5hûM^uérãUuÀ$^õ!f|$¡ü*8ğ€AÀNø„2ğ;]ÁÚœ§n,3å˜&ÀÈÅ<	a}º0W_1æëD‘^à\\ät5=ùÍUÊ8¥R¢¾“î\rO¾úÉYí•\\’­µşçõtó¶tL¢ş‹`JëW¿Ê´·;¾£Iï‘C•ˆb£~DÙ,yöˆY\0w]ÃÁföÏ17	È”9lİ°–_z,‚Ä€Oµ…ô\ZŞ¢$PëàjTsKR£ŠQÅ(â”qJF_Û%Lk^+/»fiáŒ³«2MÃcs³ª¹5f¼…†kBÌ…z¡¯{q±ò˜Ò¦$³PiDaØñœO!ÌLğqÑ™$•õ\0,q‹»HĞÌ›\0	 TY•EÛÖ|×T«ÍWq»Kjol=\rìÃNĞVtôÿ\0Pşß7t@öè…“íZÛXB˜>t1ÖÇ—“ÿ\0%(‘¢H…1Ñ¨À‡í úßañ\\‡7Ù=ËÆqq³åş?j;g‘ÛV hªN§ËÖ±¦eIcfĞ{¿Ú«ÿ\0h®KOv¯^ĞûêÜd†°¯:}hÓøµÕ„OaÛ·s†Ç ¸gïĞ£Édš²¡ÿ\0&€§$iLXoá™ŒGtÜ6oq¼?j÷—Es%çNSDÎÒ„@bA-‹*¬“mm¤Ø‹î\Z5­³f’+Ø%¯ñ¿—åJş²µ¶ªÚ»Ü uÚö×(å)tu¢ª±•°HwÀ#÷^´ŸÚ=Š²¥¶œ–º‡íÄjNMrlRôıZ`¨È˜V½ßËfğ¸°r|®6[òü¸h$È\\Tùj>l¬ndlgOÚÁ\nop6ªD¡‰U#i]m×ñ¦–X;é½Êo»ªCšGïşŸºê½ôz¥Œœ­k’¹ßhn‘K!4´•êIn2@Å`ÔJÓNå¿2ÇŸQ\08]Å‹Û]¶1¸øš6ã9ŞV¹ZÀãÃ#$gÑw;AğCzªíıÈ¤åINÒ·8íö‚>R×e·ÔQ×¦ú×0‰åØ×µê¹dp„²án‘sh	õis@®ÌàÒs£»y*\r,iPœµ0›‰Q°ûˆ]8.N>C%ğFLCG\\Xá„ºı&ÇêIÅí`ªÄ\rX)ŞE ıêA­¯ÏãùÒ‡PÜa?ù±ı{c[œìîÉÕŒ’kØq;P ÇY<~¤t¸’ß÷(õ™c^ãJQšMlªVjÃ§TiM*W–n\\½ÉŠ·?œLş·º&à[¼Ò<§ÑZQ†/¨0¿ÌÛk¶ÍME-ÓŒ?øµ¿êmzë_RôQı3·ÇOÌn”]\\±w{©-ZW)ŠHì¬É	©C\Zš™±mšç#9X§‹«iÁ&#–ÑšP\\~Å!7Ò¼d\r/?×Ç{Å»w$å«,{z»“ g[lÿ\0.ívÛu…¨ÆÈm~–ñoëjf[®4¤zß)}eÈVŸ×zm¨^ RsÕ>Ûºªe­F:â´”?-Yé(YUª\n‰8[Õn\\F®ÎÁ!Æ&\nrävÑîôºO\'(Pê¢SˆÛ¨ª´I¤{‡íıº^ªÖN—øöÖ·\'\rÖW}æ’Qnñ9c­U;ş¸m­eÃœ­kIkM©µ±ëâ´^vZu2›Çc/‡å¸Åx\ZvõÕ	Š(Ğû¹ËËÇÛ‰ÈÆè¹±÷YÄ„qƒ3 ‘¡ÛìF`7[V\ZEW¦»íåÓ¿çëQfO9•X5÷NGî¼š§‘i¢î­¤p÷­×²6\'¯O»fÃdK_†û.¦›ßŞ®xÅØÓˆ¡¡ÇÇ”FyâNY»˜q¡ÅÊç‡o¤éÏeCHàiÆ3F¥l²•&˜·P¯ÃpÛr I!7Ûfß;Úÿ\0—ªs³V³>\'ıf)M»%ÄBÄ°7-¹ÒhÈã{ÕÎ–&š¸1ú»ˆM~şÓZZ-Iá)ÒGÖåbÉŞØËÏÆ5)ù*9É2àÃŸ»ù31§Š,RˆdËÔÚì›‘ƒ¾î,IGñ³-…Ë\"V7Ÿ_\n‚WåHEõËe`’fE¨5«N?²tr·‹¶¸Ë\'…ĞZÃ>&\Z’Ñ`!x_r8´\0ü6 4àå£=éM%J³DIÆr\\ŒÜ®&L,/ŸÚM#©¾l„Şck[Üú¶€{‡ˆ!E­²¨R?´Iú\n÷}ùMeş~kì‘_á·ñ«òŸoûS—Ò?Œ¿µŞ_Ù>ïù?Ì}Wö³ş×å|ß\'Äÿ\0±ó{ÿ\0ßŸ7låÿ\0éìçş®Ûvÿ\0QÔ¶İ–¶î¦›mkékiYşİŸğ·úT•æ¢¥G¬sk;C0¡­¹¨Öæ¸\r¨S!Ç%X/\n\\…)EaBÕ(80Ñú˜<¹Ï¦97‘ä±‘‹,.oaè>\nVG¥c‘³´7*pZŞÖÜ…k¹Å¨uV\ndªœÔ\0£ÜP\rZq`p‘ÎqŒş¼›Hî³«à	ğü=)\\ëQ\"rH¥ŠD«Ğ¬$iÕ¢Zœ¥I\'49	„)Lx$òL}!Î?Î9fF„†hE*&mÖ•Tû›\r¦à¶S¼Ş,ÇFì%A²0ĞÖËãl‡Ÿ4¥­_bxÃüVRŒØZ“ˆR¥%\"±”à•Dzg9Şp}Á›Àdddâ,o&N,°7P1öJ\0b6²úhI#Ô\Zƒ pò7ı*X¢o@ÚHÓ·!FŞAŠ«“”‘ªXpÔ«R2È`…J\r†8÷bÈ…œç9Ï4ŒÌæîI6¶ºè<?Jw9\ZV9Ñ¡ñ.=5·<\"ÁÅ(ÂGD)œáA÷~«(â°q#ı@/Opsúã<šHñÑ±VõßíJÈò®šöô¨Ô7:!FäŞ¬¼”©\nôÄ¬F¤¬çÉJ¨„^sŒ¨ƒœ~œ’³#BC44®O/‹àøÉü/Äğşü_ãø|oÛñxÿ\0úû==¾ßÓÓÓ”ÜÛ·\\î½ïçzWc”¥R)G£ŠQÅ(â”qJ8¥R)G£ŠWÿÙ','lkflek',NULL,NULL);

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
