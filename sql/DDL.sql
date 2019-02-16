CREATE SCHEMA redbee;

USE redbee;

CREATE TABLE `Board` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `BoardNm` varchar(255) NOT NULL,
  `OwnerNm` varchar(255) NOT NULL,
  CONSTRAINT `PK_Board` PRIMARY KEY (`Id`)
);