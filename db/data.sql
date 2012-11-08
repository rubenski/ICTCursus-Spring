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