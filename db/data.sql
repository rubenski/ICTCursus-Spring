-- company
INSERT INTO `px_company` (`id`, `address`, chamber_of_commerce_number, `city`, `country`, `email`, `name`, `phone`, `vat_number`, `zip_code`) VALUES
(1, 'Bedrijfsstraat 22', '12345', 'Leiden', 'NL', 'email@example.com', 'Produx default corp', '0612345678', '328729837', '1056PH'),
(2, 'Breestraat 22', '12345', 'Leiden', 'NL', 'bree@example.com', 'Produx Bree corp', '0612345678', '328729837', '1056PH');



-- role
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`) VALUES('1','COMPANY_PROFILE_ADMIN', 'Toegang tot bedrijfsprofiel', 1);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`) VALUES('2','COMPANY_ARTICLES_ADMIN', 'Toegang tot artikelen', 1);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`) VALUES('3','COMPANY_COURSE_ADMIN', 'Toegang tot cursussen', 1);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`) VALUES('4','COMPANY_REQUESTS_ADMIN', 'Toegang tot aanvragen', 1);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`) VALUES('5','SYS_ADMIN', 'Systeem admin', 0);
INSERT INTO `px_role` (`id`, `system_name`, `display_name`, `company_admin_role`) VALUES('6','COMPANY_USERS_ADMIN', 'Toegang tot gebruikersbeheer', 1);

-- right
INSERT INTO `px_right` (`id`, `name`) VALUES('1','access_admin_screens');
INSERT INTO `px_right` (`id`, `name`) VALUES('2','access_sysadmin_screens');
INSERT INTO `px_right` (`id`, `name`) VALUES('3','edit_company_profile');
INSERT INTO `px_right` (`id`, `name`) VALUES('4','edit_company_courses');
INSERT INTO `px_right` (`id`, `name`) VALUES('5','edit_company_articles');
INSERT INTO `px_right` (`id`, `name`) VALUES('6','edit_company_requests');
INSERT INTO `px_right` (`id`, `name`) VALUES('7','edit_everything');
INSERT INTO `px_right` (`id`, `name`) VALUES('8','edit_company_users');

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

-- userprofile
INSERT INTO `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `company_id`) VALUES(1,'rubenski@gmail.com','Ruben','van Loen','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,1);
INSERT INTO `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `company_id`) VALUES(2,'admin@example.com','Jan','Jansen','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,2);

-- userprofile2role
INSERT INTO `px_userprofile2role` (`userprofile_id`, `role_id`) VALUES(1,1);
INSERT INTO `px_userprofile2role` (`userprofile_id`, `role_id`) VALUES(1,2);
INSERT INTO `px_userprofile2role` (`userprofile_id`, `role_id`) VALUES(1,3);
INSERT INTO `px_userprofile2role` (`userprofile_id`, `role_id`) VALUES(1,4);
INSERT INTO `px_userprofile2role` (`userprofile_id`, `role_id`) VALUES(1,6);
INSERT INTO `px_userprofile2role` (`userprofile_id`, `role_id`) VALUES(2,5);

-- category
INSERT INTO `px_category` (`id`, `name`, `url_title`, `parent_id`) VALUES
(1, 'Microsoft Office', 'microsoft_office', NULL),
(2, 'Excel', 'excel', 1),
(3, 'Word', 'word', 1),
(4, 'Access', 'access', 1),
(5, 'Programmeren', 'programmeren', NULL),
(6, 'PHP', 'php', 5),
(7, 'Java', 'java', 5),
(8, 'C#', 'csharp', 5),
(9, 'C++', 'cplus', 5),
(10, 'Business Intelligence', 'business_intelligence', NULL);

-- course
INSERT INTO `px_course` (`id`, `duration`, `last_updated`, `name`, `price`, `short_description`, `long_description`, `category_id`, `company_id`, `certificate`) VALUES
(1, 'Deze cursus duurt drie dagen', '2012-09-27 14:22:33', 'Cursus Excel', 72500, 'korte omschrijving', 'lange omschrijving', 2, 1, 1),
(2, 'Deze cursus duurt vier dagen', '2012-09-27 14:22:33', 'Cursus Office algemeen', 72500, 'korte omschrijving', 'lange omschrijving', 1, 1, 0),
(3, 'Deze cursus duurt vijf dagen', '2012-09-27 14:22:33', 'Cursus Word', 82000, 'korte omschrijving', 'lange omschrijving', 3, 1, 0);

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