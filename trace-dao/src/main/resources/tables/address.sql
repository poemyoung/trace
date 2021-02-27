CREATE TABLE `qr`.`address` (
  `idaddress` INT NOT NULL,
  `province` VARCHAR(5) NULL,
  `city` VARCHAR(10) NULL,
  `county` VARCHAR(10) NULL,
  `detail` VARCHAR(50) NULL COMMENT '总的详细地址',
  `lat` VARCHAR(10) NOT NULL,
  `lng` VARCHAR(10) NOT NULL,
  `time` TIMESTAMP NULL,
  `user_id` INT NOT NULL,
  `adcode` INT NULL COMMENT '行政区划代码',
  PRIMARY KEY (`idaddress`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);
