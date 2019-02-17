DROP SCHEMA IF EXISTS redbee;

CREATE SCHEMA redbee;

USE redbee;

CREATE USER IF NOT EXISTS 'api.user'@'localhost' IDENTIFIED BY 'Pa$$w0rD';

GRANT ALL PRIVILEGES ON redbee.* TO 'api.user'@'localhost';

CREATE TABLE `Board` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `BoardNm` varchar(255) NOT NULL,
  `OwnerNm` varchar(255) NOT NULL,
  CONSTRAINT `PK_Board` PRIMARY KEY (`Id`)
);

create table `BoardHashtag` (
	`Id` BIGINT NOT NULL AUTO_INCREMENT,
    `BoardId` BIGINT NOT NULL,
    `HashTagTxt` VARCHAR(255) NOT NULL,
    CONSTRAINT `PK_BoardHashtag` PRIMARY KEY(`Id`),
    CONSTRAINT `FK_BoardHashTag_BoardId` FOREIGN KEY (`BoardId`) REFERENCES Board(`Id`)
);