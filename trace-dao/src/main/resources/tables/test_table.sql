CREATE TABLE `qr`.`test_table` (
  `idtest_table` INT NOT NULL,
  `data_varchar` VARCHAR(45) NULL,
  `data_int` INT NULL,
  PRIMARY KEY (`idtest_table`));

  ALTER TABLE `qr`.`test_table`
CHANGE COLUMN `idtest_table` `idtest_table` INT(11) NOT NULL AUTO_INCREMENT ;
