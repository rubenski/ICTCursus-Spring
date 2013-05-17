-- company
INSERT INTO `px_company` (`id`, `address`, trade_number, `city`, `country`, `email`, `name`, `phone`, `vat_number`, `zip_code`) VALUES
(1, 'Bedrijfsstraat 22', '2123453', 'Leiden', 'NL', 'email@example.com', 'Produx default corp', '0612345674', '328729834', '1056PH'),
(2, 'Breestraat 22', '1232452', 'Leiden', 'NL', 'bree@example.com', 'Produx Bree corp', '0612445678', '328429837', '1056PH'),
(3, 'Blablastraat 11', '12324534', 'Breda', 'NL', 'bleodijs@example.com', 'Breda corp', '0692837428', '192837464', '1111AA'),
(4, 'Amsterdamstraat 122', '92837224', 'Amsterdam', 'NL', 'dkjhsks@example.com', 'Some corp', '0612445674', '827364728', '9999AA');



-- role
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`, `list_rank`, `user_assignable`) VALUES('1','COMPANY_PROFILE_ADMIN', 'Toegang tot bedrijfsprofiel', 1, 1, 0);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`, `list_rank`, `user_assignable`) VALUES('2','COMPANY_ARTICLES_ADMIN', 'Toegang tot artikelen', 1, 4, 1);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`, `list_rank`, `user_assignable`) VALUES('3','COMPANY_COURSE_ADMIN', 'Toegang tot cursussen', 1, 2, 1);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`, `list_rank`, `user_assignable`) VALUES('4','COMPANY_REQUESTS_ADMIN', 'Toegang tot aanvragen', 1, 3, 1);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`, `list_rank`, `user_assignable`) VALUES('5','SYS_ADMIN', 'Systeem admin', 0, NULL, 0);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`, `list_rank`, `user_assignable`) VALUES('6','COMPANY_USERS_ADMIN', 'Toegang tot gebruikersbeheer', 1, 5, 0);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`, `list_rank`, `user_assignable`) VALUES('7','COMPANY_SETTINGS_ADMIN', 'Toegang tot bedrijfsinstellingen', 1, 6, 0);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`, `list_rank`, `user_assignable`) VALUES('8','COMPANY_ADVERTISING_ADMIN', 'Toegang tot advertentiemogelijkheden', 1, 7, 0);

-- right
INSERT INTO `px_right` (`id`, `name`) VALUES('1','access_admin_screens');
INSERT INTO `px_right` (`id`, `name`) VALUES('2','access_sysadmin_screens');
INSERT INTO `px_right` (`id`, `name`) VALUES('3','edit_company_profile');
INSERT INTO `px_right` (`id`, `name`) VALUES('4','edit_company_courses');
INSERT INTO `px_right` (`id`, `name`) VALUES('5','edit_company_articles');
INSERT INTO `px_right` (`id`, `name`) VALUES('6','edit_company_requests');
INSERT INTO `px_right` (`id`, `name`) VALUES('7','edit_everything');
INSERT INTO `px_right` (`id`, `name`) VALUES('8','edit_company_users');
INSERT INTO `px_right` (`id`, `name`) VALUES('9','edit_company_settings');
INSERT INTO `px_right` (`id`, `name`) VALUES('10','edit_company_advertisements');

-- role2right
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('1','1');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('1','3');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('2','1');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('2','5');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('3','1');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('3','4');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('4','1');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('4','6');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('5','2');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('5','7');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('6','8');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('6','1');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('7','9');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('7','1');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('8','10');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('5','10');

-- userprofile
INSERT INTO `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `company_id`) VALUES(1,'rubenski@gmail.com','Ruben','van Loen','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,1);
INSERT INTO `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `company_id`) VALUES(2,'admin@example.com','Jan','Jansen','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,2);
INSERT INTO `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `company_id`) VALUES(3,'jan@example.com','Jan','Jansen','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,3);
INSERT INTO `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `company_id`) VALUES(4,'charlotte@example.com','Charlotte','Lubongan','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,4);
INSERT INTO `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `company_id`) VALUES(5,'ruben@example.com','Jan','Jansen','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,4);


-- userprofile2role
insert  into `px_userprofile2role`(`userprofile_id`,`role_id`) values (1,1),(1,2),(1,3),(1,4),(1,6),(1,7),(1,8),(2,5),(3,1),(3,2),(3,3),(3,4),(3,6),(3,7),(3,8),(4,1),(4,2),(4,3),(4,4),(4,6),(4,7),(4,8),(5,1),(5,2),(5,3),(5,4),(5,6),(5,7),(5,8);


-- category
INSERT INTO `px_category` (`id`, `name`, `url_title`, `parent_id`) VALUES
(1, 'Microsoft Office', 'microsoft-office', NULL),
(2, 'Excel', 'excel', 1),
(3, 'Word', 'word', 1),
(4, 'Access', 'access', 1),
(5, 'Programmeren', 'programmeren', NULL),
(6, 'PHP', 'php', 5),
(7, 'Java', 'java', 5),
(8, 'C#', 'csharp', 5),
(9, 'C++', 'cplus', 5),
(10, 'Business Intelligence', 'business-intelligence', NULL);

-- course
insert  into `px_course`(`id`,`certificate`,`certificate_name`,`duration`,`first_published`,`last_indexed`,`last_updated`,`list_description`,`long_description`,`name`,`price`,`published`,`category_id`,`company_id`) values
(1,1,'','Deze cursus duurt drie dagen',NULL,NULL,'2013-05-17 13:22:59','korte omschrijving','<p>lange omschrijving</p>','Cursus Excel',72500,0,1,1),
(2,0,'','Deze cursus duurt vier dagen',NULL,NULL,'2013-05-17 13:23:15','korte omschrijving','<p>lange omschrijving</p>','Cursus Office algemeen',72500,0,1,1),
(3,0,NULL,'Deze cursus duurt vijf dagen',NULL,NULL,'2012-09-27 14:22:33','korte omschrijving','lange omschrijving','Cursus Word',82000,0,3,1),
(4,0,'','2 dagen','2013-05-17 13:06:45',NULL,'2013-05-17 13:06:45','Dit is een Office cursus die gegeven wordt door Charlotte. Hij is heel leuk.','<p>Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus.</p>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;</p>\r\n<p>Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus. Hallo, dit is de lange omschrijving van een cursus.</p>','Charlotte\'s Office cursus',45300,1,1,4),
(5,1,'b labla ','2 dagen','2013-05-17 13:10:35',NULL,'2013-05-17 13:12:28','Charlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office Cursus','<p>Charlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office Cursus</p>\r\n<p>&nbsp;</p>\r\n<p>Charlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office Cursusv</p>\r\n<p>&nbsp;</p>\r\n<p>Charlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office CursusCharlotte\'s Advanced Office Cursus</p>','Charlotte\'s Advanced Office Cursus',34200,1,1,4),
(6,1,'wergwer','3 dagen',NULL,NULL,'2013-05-17 13:22:12','Jan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursus','Jan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursus\r\n\r\nJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursus\r\n\r\nJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursusJan\'s MS Office cursus','Jan\'s MS Office cursus',24400,0,1,3),
(7,0,'','7 weken',NULL,NULL,'2013-05-17 13:26:05','Nog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus dan','<p>Nog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus dan</p>\r\n<p>&nbsp;</p>\r\n<p>Nog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus dan</p>\r\n<p>&nbsp;</p>\r\n<p>Nog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus danNog maar een MS Office cursus dan</p>','Nog maar een MS Office cursus dan',22200,0,1,3),
(8,0,'','5 dagen',NULL,NULL,'2013-05-17 13:27:17','MS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveel','<p>MS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelv</p>\r\n<p>MS cursus zoveel</p>\r\n<p>&nbsp;</p>\r\n<p>MS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveel</p>\r\n<p>&nbsp;</p>\r\n<p>MS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelv</p>\r\n<p>&nbsp;</p>\r\n<p>MS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveelMS cursus zoveel</p>','MS cursus zoveel',34200,0,1,3);

-- region
INSERT INTO `px_region` (`id`, `name`) VALUES
(1, 'Amsterdam'),
(2, 'Rotterdam'),
(3, 'Nijmegen'),
(4, 'Utrecht'),
(5, 'Groningen');

INSERT INTO `px_tag` (`id`, `name`) VALUES
(1, 'Database design'),
(2, 'Calculatie'),
(3, 'Office');

INSERT INTO `px_systemproperty` (`_key`, `value`) VALUES ('solr.url', 'http://local.tomcat:8983/solr');

INSERT INTO `px_time` (`name`, `display_rank`) VALUES('Ochtend', 1), ('Middag', 2), ('Avond', 3), ('Weekend', 4);

insert  into `px_course2time`(`course_id`,`time_id`) values (5,1),(8,1),(5,2),(7,2),(8,2),(5,3),(7,3),(8,3),(5,4);

INSERT INTO `px_optioncategory` (`id`, `name`, `display_rank`) VALUES (1, 'Cursusvormen', 1);
INSERT INTO `px_optioncategory` (`id`, `name`, `display_rank`) VALUES (2, 'Inschrijving', 3);
INSERT INTO `px_optioncategory` (`id`, `name`, `display_rank`) VALUES (3, 'Inhoud', 2);
INSERT INTO `px_optioncategory` (`id`, `name`, `display_rank`) VALUES (4, 'Certificering', 4);

INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Docent aan huis', 1, 4);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Studeren via internet', 1, 3);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('In company', 1, 1);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Op een cursuslokatie', 1, 2);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Exclusieve groepen', 2, 2);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Open inschrijving', 2, 1);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Vast programma', 3, 2);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Inhoud in overleg', 3, 1);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Erkend certificaat / diploma', 4, 1);
INSERT INTO `px_courseoption` ( `display_name`, `optioncategory_id`, `display_rank`) VALUES('Certificaat van deelname', 4, 2);


insert  into `px_course2courseoption`(`course_id`,`courseoption_id`) values (1,1),(2,1),(5,1),(7,1),(6,2),(8,2),(1,3),(2,4),(5,4),(6,4),(7,4),(8,4),(1,5),(2,5),(5,5),(6,5),(8,5),(2,7),(5,7),(6,7),(8,7),(2,9),(5,9),(6,10),(8,10);

insert  into `px_course2region`(`course_id`,`region_id`) values (1,1),(2,1),(4,1),(5,1),(6,1),(7,1),(8,1),(1,2),(2,2),(4,2),(5,2),(6,2),(7,2),(1,3),(2,3),(4,3),(5,3),(6,3),(7,3),(8,3),(1,4),(2,4),(4,4),(5,4),(6,4),(7,4),(1,5),(2,5),(4,5),(5,5),(6,5),(7,5);
