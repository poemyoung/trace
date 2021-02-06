CREATE TABLE `qr`.`user_info` (
  `id` INT NOT NULL COMMENT '个人信息数据表\nName : 姓名\nCard_id ：身份证号\nDetail ： 居住地址等个人基本信息\nuser_types ： 用户类型',
  `name` VARCHAR(20) NULL,
  `card_id` CHAR(18) NULL,
  `detail` INT NULL,
  `user_type` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `card_id_UNIQUE` (`card_id` ASC));
