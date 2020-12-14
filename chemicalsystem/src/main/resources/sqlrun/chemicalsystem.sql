-- DROP TABLE IF EXISTS `user`;
--
-- CREATE TABLE `user` (
--   `id` int(11) NOT NULL auto_increment,
--   `username` varchar(32) NOT NULL COMMENT '用户名称',
--   `birthday` datetime default NULL COMMENT '生日',
--   `sex` char(1) default NULL COMMENT '性别',
--   `address` varchar(256) default NULL COMMENT '地址',
--   PRIMARY KEY  (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
--
--
-- insert  into `user`(`id`,`username`,`birthday`,`sex`,`address`) values (41,'老王','2018-02-27 17:47:08','男','北京'),(42,'小二王','2018-03-02 15:09:37','女','北京金燕龙'),(43,'小二王','2018-03-04 11:34:34','女','北京金燕龙'),(45,'传智播客','2018-03-04 12:04:06','男','北京金燕龙'),(46,'老王','2018-03-07 17:37:26','男','北京'),(48,'小马宝莉','2018-03-08 11:44:00','女','北京修正');
--
--
--
--
--
-- DROP TABLE IF EXISTS `account`;
--
-- CREATE TABLE `account` (
--   `ID` int(11) NOT NULL COMMENT '编号',
--   `UID` int(11) default NULL COMMENT '用户编号',
--   `MONEY` double default NULL COMMENT '金额',
--   PRIMARY KEY  (`ID`),
--   KEY `FK_Reference_8` (`UID`),
--   CONSTRAINT `FK_Reference_8` FOREIGN KEY (`UID`) REFERENCES `user` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
--
--
-- insert  into `account`(`ID`,`UID`,`MONEY`) values (1,41,1000),(2,45,1000),(3,41,2000);
--
--
--
-- DROP TABLE IF EXISTS `role`;
--
-- CREATE TABLE `role` (
--   `ID` int(11) NOT NULL COMMENT '编号',
--   `ROLE_NAME` varchar(30) default NULL COMMENT '角色名称',
--   `ROLE_DESC` varchar(60) default NULL COMMENT '角色描述',
--   PRIMARY KEY  (`ID`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
--
--
-- insert  into `role`(`ID`,`ROLE_NAME`,`ROLE_DESC`) values (1,'院长','管理整个学院'),(2,'总裁','管理整个公司'),(3,'校长','管理整个学校');
--
--
--
--
--
-- DROP TABLE IF EXISTS `user_role`;
--
-- CREATE TABLE `user_role` (
--   `UID` int(11) NOT NULL COMMENT '用户编号',
--   `RID` int(11) NOT NULL COMMENT '角色编号',
--   PRIMARY KEY  (`UID`,`RID`),
--   KEY `FK_Reference_10` (`RID`),
--   CONSTRAINT `FK_Reference_10` FOREIGN KEY (`RID`) REFERENCES `role` (`ID`),
--   CONSTRAINT `FK_Reference_9` FOREIGN KEY (`UID`) REFERENCES `user` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- insert  into `user_role`(`UID`,`RID`) values (41,1),(45,1),(41,2);
--
-- DROP TABLE IF EXISTS `shipins`;
--
-- CREATE TABLE `shipins` (
--   `id` int(11) NOT NULL auto_increment,
--   `url` varchar(600) default NULL COMMENT 'url',
--   `name` varchar(600) default NULL COMMENT '名字',
--   `lujing` varchar(600) default NULL COMMENT '物理路径',
--   PRIMARY KEY  (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- DROP TABLE IF EXISTS `upload`;
--
-- CREATE TABLE `upload` (
--   `id` int(11) NOT NULL auto_increment,
--   `index` varchar(20) default NULL COMMENT 'index',
--   `type` varchar(20) default NULL COMMENT 'type',
--   `url` varchar(600) default NULL COMMENT 'url',
--   `title` varchar(50) default NULL COMMENT 'title',
--   `context` varchar(255) default NULL COMMENT 'context',
--   `name` varchar(600) default NULL COMMENT '名字',
--   `lujing` varchar(600) default NULL COMMENT '物理路径',
--   PRIMARY KEY  (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- insert into upload (name,lujing,url,index,type,title,context)
--         values ("123","123","123","123","123","123","123");
--
-- insert into upload (name,lujing,url,`index`,type,title,context)
--         values ("123","123","123","123","123","123","123");


-- DROP TABLE IF EXISTS `chemicaldrugmanagement`;
--
-- CREATE TABLE `chemicaldrugmanagement` (
--   `id` int(11) NOT NULL auto_increment,
--   `drugname` varchar(32) NOT NULL COMMENT '药品名称',
--   `drugtype` varchar(100) NOT NULL COMMENT '药品类型',
--   `drugnumber` int(11) NOT NULL COMMENT '药品数量',
--   `drugposition` varchar(32) NOT NULL COMMENT '药品位置',
--   `drugexpiration` datetime NOT NULL COMMENT '保质日期',
--   `drugmeasurement` varchar(11) NOT NULL COMMENT '单位',
--   PRIMARY KEY  (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- create database chemicalsystem;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `usernumber` varchar(32) NOT NULL COMMENT '用户账号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `userrole` varchar(15) default NULL COMMENT '身份类别',
  `token` varchar(256) default NULL COMMENT '登录token',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (username,usernumber,password,userrole) VALUES ('叶佳', 'abc001', '$2a$10$3acS3S.OtXYTr54XlXbXROx37veuYiQdhDdtkVSwKETY0CTgViUf2', '高级管理员');

DROP TABLE IF EXISTS `addnewmedicine`;

CREATE TABLE `addnewmedicine` (
  `id` int(11) NOT NULL auto_increment,
  `storagetime` datetime NOT NULL COMMENT '入库日期',
  `medicinename` varchar(32) NOT NULL COMMENT '药品名称',
  `medicinetype` varchar(100) NOT NULL COMMENT '药品类型',
  `medicinenumber` int(11) NOT NULL COMMENT '药品数量',
  `measurement` varchar(11) NOT NULL COMMENT '单位',
  `medicinebox` varchar(64) NOT NULL COMMENT '药品柜',
  `expiredtime` datetime NOT NULL COMMENT '过期日期',
  `storageuser` varchar(32) NOT NULL COMMENT '入库人',
  `approver` varchar(32) NOT NULL COMMENT '审批人',
  `medicinenumber_status` int(11) NOT NULL COMMENT '药品数量是否用完',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `addnewmedicine` (storagetime,medicinename,medicinetype,medicinenumber,measurement,medicinebox,expiredtime,storageuser,approver) VALUES ('2018-02-27 17:47:08', '二氯二乙硫醚', '有毒气体', 100, '瓶', '7-36-8', '2018-09-27 17:47:07', '潘金莲', '西门庆');


DROP TABLE IF EXISTS `laboratory_equipment_management`;

CREATE TABLE `laboratory_equipment_management` (
  `id` int(11) NOT NULL auto_increment,
  `storagetime` datetime NOT NULL COMMENT '入库日期',
  `equipment_name` varchar(32) NOT NULL COMMENT '设备名称',
  `equipment_type` varchar(100) NOT NULL COMMENT '设备类型',
  `equipment_number` int(11) NOT NULL COMMENT '设备数量',
  `equipment_position` varchar(64) NOT NULL COMMENT '设备位置',
  `storageuser` varchar(32) NOT NULL COMMENT '入库人',
  `approver` varchar(32) NOT NULL COMMENT '审批人',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `materials_medicine_management`;

CREATE TABLE `materials_medicine_management` (
  `id` int(11) NOT NULL auto_increment,
  `medicine_name` varchar(32) NOT NULL COMMENT '药品名称',
  `medicine_type` varchar(100) NOT NULL COMMENT '药品类型',
  `medicine_number` int(11) NOT NULL COMMENT '药品数量',
  `measurement` varchar(11) NOT NULL COMMENT '单位',
  `medicine_box` varchar(64) NOT NULL COMMENT '药品柜',
  `expired_time` datetime default NULL COMMENT '过期日期',
  `storage_number_status` varchar(16) default NULL COMMENT '库存状态',
  `expired_time_status` varchar(16) default NULL COMMENT '保质状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `export_chemical_medicine`;

CREATE TABLE `export_chemical_medicine` (
  `id` int(11) NOT NULL auto_increment,
  `export_time` datetime NOT NULL COMMENT '出库日期',
  `medicine_name` varchar(32) NOT NULL COMMENT '药品名称',
  `medicine_type` varchar(100) NOT NULL COMMENT '药品类型',
  `medicine_number` int(11) NOT NULL COMMENT '药品数量',
  `measurement` varchar(11) NOT NULL COMMENT '单位',
  `medicine_box` varchar(64) NOT NULL COMMENT '药品柜',
  `expired_time` datetime NOT NULL COMMENT '过期日期',
  `export_user` varchar(32) NOT NULL COMMENT '出库人',
  `export_approver` varchar(32) NOT NULL COMMENT '审批人',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `system_setting`;

CREATE TABLE `system_setting` (
  `id` int(11) NOT NULL auto_increment,
  `medicine_expired_warning` int(11) NOT NULL COMMENT '药品过期天数设置',
  `medicine_number_warning` int(11) NOT NULL COMMENT '药品库存数量不足提醒设置',
  `equipment_number_warning` int(11) NOT NULL COMMENT '设备库存数量不足提醒设置',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `system_setting`(`id`, `medicine_expired_warning`, `medicine_number_warning`, `equipment_number_warning`) VALUES (1202526328, 100, 50, 50);


DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` int(11) NOT NULL auto_increment,
  `medicine_name` varchar(32) NOT NULL COMMENT '药品名称',
  `medicine_box` varchar(32) NOT NULL COMMENT '药品柜',
  `expired_time` datetime NOT NULL COMMENT '过期日期',
  `expired_number` int(11) default NULL COMMENT '药品还有多少天过期',
  `medicine_number` int(11) default NULL COMMENT '药品库存不足，还剩余多少',
  `notice_time` datetime NOT NULL COMMENT '这条消息提醒推送的日期',
  `notice_status` int(4) NOT NULL COMMENT '消息是否已读',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `login_log`;

CREATE TABLE `login_log` (
  `id` varchar(40) NOT NULL ,
  `login_number` varchar(32) NOT NULL COMMENT '登录账号',
  `login_name` varchar(32) NOT NULL COMMENT '登录账号对应用户名',
  `login_date` datetime NOT NULL COMMENT '登录时间',
  `user_role` varchar(32) NOT NULL COMMENT '登录者权限',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `export_equipment`;

CREATE TABLE `export_equipment` (
  `id` int(11) NOT NULL auto_increment,
  `export_time` datetime NOT NULL COMMENT '出库日期',
  `equipment_name` varchar(32) NOT NULL COMMENT '设备名称',
  `equipment_type` varchar(100) NOT NULL COMMENT '设备类型',
  `equipment_number` int(11) NOT NULL COMMENT '设备数量',
  `equipment_position` varchar(64) NOT NULL COMMENT '设备柜',
  `export_user` varchar(32) NOT NULL COMMENT '出库人',
  `export_approver` varchar(32) NOT NULL COMMENT '审批人',
  `export_equipment_status` varchar(12) NOT NULL COMMENT '出库设备状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `materials_equipment_management`;

CREATE TABLE `materials_equipment_management` (
  `id` int(11) NOT NULL auto_increment,
  `equipment_name` varchar(32) NOT NULL COMMENT '设备名称',
  `equipment_type` varchar(100) NOT NULL COMMENT '设备类型',
  `equipment_number` int(11) NOT NULL COMMENT '设备数量',
  `equipment_position` varchar(64) NOT NULL COMMENT '设备柜',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




