-- company
INSERT INTO `px_company` (`id`, `description`, `email`, `logo`, `name`, `phone`) VALUES('1',NULL,NULL,NULL,'Produx default corp',NULL);
INSERT INTO `px_company` (`id`, `description`, `email`, `logo`, `name`, `phone`) VALUES('2',NULL,NULL,NULL,'Rubenski corp',NULL);

-- role
INSERT INTO `px_role` (`id`, `name`) VALUES('1','COMPANY');
INSERT INTO `px_role` (`id`, `name`) VALUES('2','ANONYMOUS');
INSERT INTO `px_role` (`id`, `name`) VALUES('3','ADMIN');

-- right
INSERT INTO `px_right` (`id`, `name`) VALUES('1','access_management_screens');
INSERT INTO `px_right` (`id`, `name`) VALUES('2','access_admin_screens');

-- role2right
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('1','1');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('3','1');
INSERT INTO `px_role2right` (`role_id`, `right_id`) VALUES('3','2');

-- userprofile
INSERT INTO `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `company_id`) VALUES('1','rubenski@gmail.com','Ruben','van Loen','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,'1');


-- userprofile2role
INSERT INTO `px_userprofile2role` (`userprofile_id`, `role_id`) VALUES('1','1');

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
INSERT INTO `px_course` (`id`, `duration`, `last_updated`, `name`, `price`, `short_description`, `long_description`, `category_id`, `company_id`) VALUES
(1, 'Deze cursus duurt drie dagen', '2012-09-27 14:22:33', 'Cursus Excel', 72500, 'korte omschrijving', 'lange omschrijving', 2, 1),
(2, 'Deze cursus duurt vier dagen', '2012-09-27 14:22:33', 'Cursus Office algemeen', 72500, 'korte omschrijving', 'lange omschrijving', 1, 1),
(3, 'Deze cursus duurt vijf dagen', '2012-09-27 14:22:33', 'Cursus Word', 82000, 'korte omschrijving', 'lange omschrijving', 3, 1);

-- region
INSERT INTO `px_region` (`id`, `name`) VALUES
(1, 'Amsterdam'),
(2, 'Rotterdam'),
(3, 'Nijmegen'),
(4, 'Utrecht'),
(5, 'Groningen');


