/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : ampliar_demo

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-29 10:50:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advertisments
-- ----------------------------
DROP TABLE IF EXISTS `advertisments`;
CREATE TABLE `advertisments` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PUBLISHED_BY` int(11) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `PRICE` double(10,2) DEFAULT NULL,
  `CATEGORY` varchar(255) DEFAULT NULL,
  `SUB_CATEGORY` varchar(255) DEFAULT NULL,
  `DISTRICT` varchar(255) DEFAULT NULL,
  `DISTRICT_LOCAL_AREA` varchar(255) DEFAULT NULL,
  `ATTRIBUTES` json DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_PB_BY` (`PUBLISHED_BY`),
  CONSTRAINT `FK_PB_BY` FOREIGN KEY (`PUBLISHED_BY`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertisments
-- ----------------------------
INSERT INTO `advertisments` VALUES ('2', '1', 'Samsung S6', '40000.00', 'Electronics', 'Mobile', 'Colombo', 'Nugegoda', '{\"brand\": \"samsung\", \"model\": \"galaxy S6\", \"bluetooth\": \"yes\", \"condition\": \"best\", \"Authenticity\": \"Original \"}', '1', '2017-10-06 01:58:55', '2017-10-06 01:58:55');

-- ----------------------------
-- Table structure for advertisment_images
-- ----------------------------
DROP TABLE IF EXISTS `advertisment_images`;
CREATE TABLE `advertisment_images` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADVERTISMENT_ID` int(11) DEFAULT NULL,
  `IMAGE_URL` varchar(1000) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_ADD` (`ADVERTISMENT_ID`),
  CONSTRAINT `FK_ADD` FOREIGN KEY (`ADVERTISMENT_ID`) REFERENCES `advertisments` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertisment_images
-- ----------------------------
INSERT INTO `advertisment_images` VALUES ('1', '2', 'data layer.png', '1', '2017-10-06 01:58:55', '2017-10-06 01:58:55');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'sampel');
SET FOREIGN_KEY_CHECKS=1;
