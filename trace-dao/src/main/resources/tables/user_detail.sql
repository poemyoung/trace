CREATE TABLE `qr`.`user_detail` (
  `iduser_detail` INT NOT NULL,
  `phone` VARCHAR(20) NULL,
  `addr_id` INT NULL COMMENT '详细地址id',
  `risk_flag` INT NULL COMMENT '风险提示',
  `temp` VARCHAR(5) NULL COMMENT '温度',
  PRIMARY KEY (`iduser_detail`),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE);
