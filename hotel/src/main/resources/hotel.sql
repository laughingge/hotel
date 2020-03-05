/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 05/03/2020 16:10:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `authority` VALUES (2, 'ROLE_USER');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employeeid` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realname` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roles` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`employeeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (5, 'XWQ', '574bbecbb2b183d46cdfb765fd711283', 'XWQ', '88888888', '123', '21', '21', '21', 'ROLE_USER');
INSERT INTO `employee` VALUES (6, 'SAS', '$2a$10$ZiCKoBhVz0d9eO0Ki86b.OUJlVD/VqKureO3xPFLdYJbt64fKEvHC', 'SAS', 'SA', 'SA', 'SA', 'SA', 'SA', 'ROLE_ADMIN');
INSERT INTO `employee` VALUES (7, 'admin', '$2a$10$BZFrM0SpZ3jJAvfcVXXc8.7sOfTAOF.hbVqJIlAE2WA1mx/9jxqbS', '曾铭豪', '13266304809', '440684199801303814', 'sa', 'sa', '靠海', 'ROLE_ADMIN');
INSERT INTO `employee` VALUES (8, '老板', '$2a$10$EYLxHli6/eYauh.JEd2W6OkYUOPt0XH6Nthc6PnyLbchICu226BJS', '卢本伟', '13266304809', '440684199801303814', 'sa', '空闲', '靠海', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for floor
-- ----------------------------
DROP TABLE IF EXISTS `floor`;
CREATE TABLE `floor`  (
  `floorid` int(11) NOT NULL AUTO_INCREMENT,
  `floorname` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`floorid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of floor
-- ----------------------------
INSERT INTO `floor` VALUES (1, '一楼', '漏水');
INSERT INTO `floor` VALUES (2, '二楼', '房门坏了');
INSERT INTO `floor` VALUES (3, '三楼', '无');
INSERT INTO `floor` VALUES (4, '四楼', '');
INSERT INTO `floor` VALUES (5, '五楼', '');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `roomid` int(11) NULL DEFAULT NULL,
  `bookdate` datetime(0) NULL DEFAULT NULL,
  `checkindate` datetime(0) NULL DEFAULT NULL,
  `leavedate` datetime(0) NULL DEFAULT NULL,
  `state` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` int(11) NULL DEFAULT NULL,
  `price` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `orderindex`(`roomid`, `bookdate`) USING BTREE,
  INDEX `leavedateindex`(`leavedate`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (11, 19, '2020-01-24 15:16:47', '2020-01-24 15:39:59', '2020-01-24 15:40:06', '已付款', '靠海', 1, 99);
INSERT INTO `orders` VALUES (14, 24, '2020-01-25 11:31:07', '2020-01-25 12:44:15', '2020-01-26 10:11:10', '已付款', '', 1, 122);
INSERT INTO `orders` VALUES (17, 9, '2020-01-25 20:00:02', '2020-01-26 10:45:52', '2020-01-26 10:22:01', '已付款', '', 1, 99);
INSERT INTO `orders` VALUES (19, 19, '2020-01-26 10:53:24', '2020-01-26 10:56:12', '2020-01-26 10:57:26', '已付款', '', 1, 99);
INSERT INTO `orders` VALUES (20, 16, '2020-01-26 14:07:23', '2020-01-28 13:02:39', '2020-01-29 13:36:09', '已付', '', 1, 122);
INSERT INTO `orders` VALUES (22, 25, '2020-01-29 14:14:51', '2020-01-29 14:48:18', '2020-01-30 13:52:52', '已付', '', 2, 122);
INSERT INTO `orders` VALUES (23, 19, '2020-01-29 14:24:49', '2020-01-30 13:45:41', '2020-01-30 13:49:24', '已付款', '', 1, 99);
INSERT INTO `orders` VALUES (25, 24, '2020-01-30 14:23:00', '2020-01-30 14:23:12', '2020-01-30 14:37:28', '已付款', '', 1, 122);
INSERT INTO `orders` VALUES (26, 18, '2020-01-30 14:37:48', '2020-01-30 14:39:19', '2020-01-30 14:51:06', '已付款', '', 1, 122);
INSERT INTO `orders` VALUES (27, 19, '2020-01-30 14:56:05', '2020-01-30 14:56:22', '2020-01-30 14:58:17', '已付款', '', 1, 99);
INSERT INTO `orders` VALUES (28, 24, '2020-01-30 15:00:24', '2020-01-30 15:07:21', '2020-01-30 15:09:19', '已付款', '', 1, 122);
INSERT INTO `orders` VALUES (29, 25, '2020-01-30 15:11:17', '2020-01-30 15:18:41', '2020-01-31 16:36:29', '已付款', '', 4, 122);
INSERT INTO `orders` VALUES (36, 22, '2020-01-31 21:38:35', '2020-01-31 21:48:48', '2020-01-31 21:50:04', '已付款', '', 1, 99);
INSERT INTO `orders` VALUES (37, 25, '2020-01-31 21:54:24', '2020-01-31 21:59:15', '2020-02-02 16:25:05', '未付', '', 1, 122);
INSERT INTO `orders` VALUES (38, 17, '2020-02-02 16:26:08', '2020-02-03 13:59:50', NULL, '未付', '', 1, 122);
INSERT INTO `orders` VALUES (39, 9, '2020-03-04 12:22:37', '2020-03-04 12:22:52', NULL, '已付', '', 1, 99);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomid` int(11) NOT NULL AUTO_INCREMENT,
  `pic` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roomtypeid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `floorid` int(11) NULL DEFAULT NULL,
  `state` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roomid`) USING BTREE,
  INDEX `stateindex`(`state`) USING BTREE,
  INDEX `roomindex`(`roomid`, `roomtypeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (9, '0 (1).jpg', '314', '1', 1, '已入住', '靠海');
INSERT INTO `room` VALUES (16, '屏幕截图(3).png', '101', '2', 2, '空闲', '靠海');
INSERT INTO `room` VALUES (17, '屏幕截图(2).png', '102', '2', 1, '已入住', '靠山');
INSERT INTO `room` VALUES (18, '屏幕截图(13).png', '103', '2', 1, '空闲', '靠海');
INSERT INTO `room` VALUES (19, '屏幕截图(9).png', '607', '1', 1, '空闲', '靠海');
INSERT INTO `room` VALUES (22, '屏幕截图(5).png', '609', '1', 2, '空闲', '靠海');
INSERT INTO `room` VALUES (24, '屏幕截图(4).png', '124', '2', 1, '空闲', '靠海');
INSERT INTO `room` VALUES (25, '51c73842-8ce9-4555-9f60-40ed4c8bf59d屏幕截图(20).png', '810', '2', 3, '空闲', 'sas');
INSERT INTO `room` VALUES (26, 'fcb6080a-7e36-4dbc-982c-81d33b7be6db屏幕截图(7).png', '709', '1', 1, '空闲', '');

-- ----------------------------
-- Table structure for roomtype
-- ----------------------------
DROP TABLE IF EXISTS `roomtype`;
CREATE TABLE `roomtype`  (
  `roomtypeid` int(11) NOT NULL AUTO_INCREMENT,
  `roomtypename` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int(11) NULL DEFAULT NULL,
  `capacity` int(11) NULL DEFAULT NULL,
  `bed` int(11) NULL DEFAULT NULL,
  `roomnumber` int(11) NULL DEFAULT NULL,
  `booked` int(11) NULL DEFAULT NULL,
  `checkedin` int(11) NULL DEFAULT NULL,
  `state` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roomtypeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roomtype
-- ----------------------------
INSERT INTO `roomtype` VALUES (1, '单人间', 99, NULL, 1, 11, 0, 1, '空闲', '靠海');
INSERT INTO `roomtype` VALUES (2, '双人间', 122, NULL, 2, 9, 0, 1, '空闲', '靠海');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realname` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xiaosan', '1', '第三代', '13266304809', '440684199801303814', '2', '1', '122');
INSERT INTO `user` VALUES (2, '小兰', '1', '花木兰', '13266304809', '440684199801303814', 'sa', '空闲', '靠海');
INSERT INTO `user` VALUES (3, '曾小贤', '123', '曾小贤', '123', '56655566', '麻油地', '1', '1');
INSERT INTO `user` VALUES (4, '曾小贤1', '123', '曾小贤1', '123', '56655566', '麻油地', '1', '1');
INSERT INTO `user` VALUES (5, '曾小贤2', '123', '曾小贤2', '123', '56655566', '麻油地', '1', '1');
INSERT INTO `user` VALUES (6, '曾小贤3', '123', '曾小贤3', '123', '56655566', '麻油地', '1', '1');
INSERT INTO `user` VALUES (9, 'sa', '1', '曾铭豪', '13266304809', '440684199801303814', '1sa', '空闲', '靠海');
INSERT INTO `user` VALUES (10, 'a', '12', 'SA', '13266304809', '12', 'sa', '空闲', '靠海');
INSERT INTO `user` VALUES (11, '柯南', '1', '曾铭豪', '13266304809', '440684199801303814', 'sa', '空闲', '靠海');

SET FOREIGN_KEY_CHECKS = 1;
