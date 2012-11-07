-- company
insert into `px_company` (`id`, `description`, `email`, `logo`, `name`, `phone`, `version`) values('1',NULL,NULL,NULL,'Produx default corp',NULL,NULL);
insert into `px_company` (`id`, `description`, `email`, `logo`, `name`, `phone`, `version`) values('2',NULL,NULL,NULL,'Rubenski corp',NULL,NULL);

-- role
insert into `px_role` (`id`, `name`, `version`) values('1','ROLE_COMPANY',NULL);
insert into `px_role` (`id`, `name`, `version`) values('2','ROLE_ANONYMOUS',NULL);
insert into `px_role` (`id`, `name`, `version`) values('3','ROLE_ADMIN',NULL);

-- right
insert into `px_right` (`id`, `name`, `version`) values('1','access_management_screens',NULL);
insert into `px_right` (`id`, `name`, `version`) values('2','access_admin_screens',NULL);

-- role2right
insert into `px_role2right` (`role_id`, `right_id`) values('1','1');
insert into `px_role2right` (`role_id`, `right_id`) values('3','1');
insert into `px_role2right` (`role_id`, `right_id`) values('3','2');

-- userprofile
insert into `px_userprofile` (`id`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `version`, `company_id`) values('1','rubenski@gmail.com','Ruben','van Loen','3fc393d1c2afa6bf41a3dc44b29aace238d885cc',NULL,NULL,'1');


-- userprofile2role
insert into `px_userprofile2role` (`userprofile_id`, `role_id`) values('1','1');