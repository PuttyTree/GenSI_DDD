/*
Navicat MySQL Data Transfer

Source Server         : 192.168.100.122
Source Server Version : 50636
Source Host           : 192.168.100.122:3306
Source Database       : ftoulcloud

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-08-07 10:14:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `gsmanage`
-- ----------------------------
DROP TABLE IF EXISTS `gsmanage`;
CREATE TABLE `gsmanage` (
  `sysId` varchar(16) NOT NULL DEFAULT '' COMMENT '系统标识',
  `sysName` varchar(32) DEFAULT NULL COMMENT '系统名称',
  `privateKey` text COMMENT '私有key',
  `publicKey` text COMMENT '公有Key',
  `authedIp` varchar(256) DEFAULT NULL COMMENT '授权的ip，多个ip以英文分号隔开',
  `userName` varchar(32) DEFAULT NULL COMMENT '系统用户名称',
  `pwd` varchar(32) DEFAULT NULL COMMENT '系统用户密码',
  `notifyUrl` varchar(128) DEFAULT NULL COMMENT '消息推送路径',
  `notifyParam` varchar(32) DEFAULT NULL COMMENT '消息推送参数',
  `deleteFlag` varchar(4) DEFAULT NULL COMMENT '删除标识(预留)',
  `reserve1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
  `reserve2` varchar(64) DEFAULT NULL COMMENT '预留字段2',
  PRIMARY KEY (`sysId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gsmanage
-- ----------------------------
INSERT INTO `gsmanage` VALUES ('MyTestSys', 'genSiClient', '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', '192.168.222.333', 'admin', '123456', 'http://localhost:8890/client/receiveMessage', NULL, NULL, NULL, NULL);
-- ----------------------------
-- Table structure for `gsrequest`
-- ----------------------------
DROP TABLE IF EXISTS `gsrequest`;
CREATE TABLE `gsrequest` (
  `transId` varchar(64) NOT NULL,
  `reqBody` longtext,
  `rspBody` longtext,
  `intime` varchar(32) DEFAULT NULL,
  `serviceCode` varchar(32) NOT NULL,
  `rsptime` varchar(32) DEFAULT NULL,
  `sysId` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`transId`,`serviceCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of fydrequest
-- ----------------------------
