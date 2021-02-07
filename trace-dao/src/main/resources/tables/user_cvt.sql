CREATE TABLE `qr`.`user_cvt` (
  `open_id` VARCHAR(50) NULL COMMENT '小程序获取的open id',
  `user_id` INT NULL COMMENT '后端通用用户id',
  UNIQUE INDEX `open_id_UNIQUE` (`open_id` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);
