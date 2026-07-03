/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : heating

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 15/04/2025 22:46:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accessories
-- ----------------------------
DROP TABLE IF EXISTS `accessories`;
CREATE TABLE `accessories`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `accessories` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配件',
  `inventory` int(0) NULL DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accessories
-- ----------------------------
INSERT INTO `accessories` VALUES (1, '11', 48);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL COMMENT '编号',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', 'admin', 'admin');

-- ----------------------------
-- Table structure for advance_fee
-- ----------------------------
DROP TABLE IF EXISTS `advance_fee`;
CREATE TABLE `advance_fee`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `datetime` datetime(0) NULL DEFAULT NULL COMMENT '缴费时间',
  `paymentAmount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缴费金额',
  `paymentCycle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缴费周期',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '居住地址',
  `living_space` double(255, 0) NULL DEFAULT NULL COMMENT '房屋面积',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of advance_fee
-- ----------------------------
INSERT INTO `advance_fee` VALUES (33, '2', '2', '2025-04-15 22:44:31', '30', '1年', '天小区3号楼104', 1);

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈人姓名',
  `datetime` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈状态',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈人账号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (36, '222', '1', '2025-01-20 00:03:39', '未处理', '1');
INSERT INTO `feedback` VALUES (37, '333', '李四', '2025-01-20 13:05:45', '未处理', '111');

-- ----------------------------
-- Table structure for heating_engineer
-- ----------------------------
DROP TABLE IF EXISTS `heating_engineer`;
CREATE TABLE `heating_engineer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salary` double(255, 0) NULL DEFAULT NULL COMMENT '工资',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heating_engineer
-- ----------------------------
INSERT INTO `heating_engineer` VALUES (1, 'http://localhost:8080/files/1736952165441-682579873.jpeg', '3', '3', '123456', 3000, 'heating_engineer', '男', '12345678911', 20);
INSERT INTO `heating_engineer` VALUES (3, NULL, '1', '1', '123456', 3000, 'heating_engineer', '女', '12345678911', 32);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作内容',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 操作人',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作人ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 134 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (126, '用户缴费', '2025-01-20 14:22:50', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (127, '供暖师傅维修', '2025-01-20 14:23:16', '3', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (128, '用户缴费', '2025-01-20 14:46:29', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (129, '用户缴费', '2025-01-20 22:26:00', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (130, '用户缴费', '2025-01-20 22:28:04', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (131, '用户缴费', '2025-01-20 22:31:16', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (132, '用户缴费', '2025-01-20 22:37:03', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (133, '用户缴费', '2025-01-20 23:33:27', '6', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (134, '用户缴费', '2025-04-15 20:14:55', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (135, '用户缴费', '2025-04-15 20:58:01', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (136, '用户缴费', '2025-04-15 21:02:40', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (137, '用户缴费', '2025-04-15 21:15:45', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (138, '用户缴费', '2025-04-15 21:18:56', '2', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (139, '用户缴费', '2025-04-15 22:19:59', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (140, '用户缴费', '2025-04-15 22:20:10', '2', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (141, '用户缴费', '2025-04-15 22:22:14', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (142, '用户缴费', '2025-04-15 22:23:14', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (143, '用户缴费', '2025-04-15 22:25:27', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (144, '用户缴费', '2025-04-15 22:27:13', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (145, '用户缴费', '2025-04-15 22:30:08', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (146, '用户缴费', '2025-04-15 22:32:01', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (147, '用户缴费', '2025-04-15 22:33:39', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (148, '用户缴费', '2025-04-15 22:38:13', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (149, '用户缴费', '2025-04-15 22:38:22', '2', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (150, '用户缴费', '2025-04-15 22:39:50', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (151, '用户缴费', '2025-04-15 22:40:27', '2', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (152, '用户缴费', '2025-04-15 22:42:27', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (153, '用户缴费', '2025-04-15 22:43:28', '2', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (154, '用户缴费', '2025-04-15 22:44:14', '2', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (155, '用户缴费', '2025-04-15 22:44:31', '2', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for maintain
-- ----------------------------
DROP TABLE IF EXISTS `maintain`;
CREATE TABLE `maintain`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '住址',
  `datetime` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题描述',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '维修状态',
  `heating_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供暖师傅账号',
  `heating_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供暖师傅姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公告内容',
  `datetime` datetime(0) NULL DEFAULT NULL COMMENT '发布日期',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (8, '系统完成', '2025-01-20 00:00:00', '已公布');
INSERT INTO `notice` VALUES (9, '系统完成1', '2025-01-20 00:00:00', '已公布');

-- ----------------------------
-- Table structure for pay_the_fees
-- ----------------------------
DROP TABLE IF EXISTS `pay_the_fees`;
CREATE TABLE `pay_the_fees`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `datetime` datetime(0) NULL DEFAULT NULL COMMENT '缴费时间',
  `paymentAmount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缴费金额',
  `paymentCycle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缴费周期',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '居住地址',
  `living_space` double(255, 0) NULL DEFAULT NULL COMMENT '房屋面积',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_the_fees
-- ----------------------------
INSERT INTO `pay_the_fees` VALUES (43, '2', '2', '2025-04-15 22:44:31', '30', '1年', '天小区3号楼104', 1);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cell` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '小区',
  `building` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '楼',
  `gate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门牌号',
  `heating_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供暖师傅账号',
  `heating_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供暖师傅姓名',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '龙', '1', '101', '3', '3', '已缴费');
INSERT INTO `room` VALUES (2, '龙', '1', '102', '3', '3', '未缴费');
INSERT INTO `room` VALUES (3, '龙', '2', '103', '3', '3', '未缴费');
INSERT INTO `room` VALUES (4, '凤', '1', '101', '1', '1', '未缴费');
INSERT INTO `room` VALUES (5, '天', '3', '104', '3', '3', '已缴费');
INSERT INTO `room` VALUES (11, '人', '1', '102', '3', '3', '未缴费');
INSERT INTO `room` VALUES (14, '人', '1', '103', '3', '3', '未缴费');
INSERT INTO `room` VALUES (15, '天', '3', '101', '3', '3', '未缴费');
INSERT INTO `room` VALUES (16, '地', '2', '101', NULL, NULL, '未缴费');
INSERT INTO `room` VALUES (17, '天', '3', '未缴费', NULL, NULL, '未缴费');
INSERT INTO `room` VALUES (18, '人', '1', '未缴费', NULL, NULL, '未缴费');
INSERT INTO `room` VALUES (19, '地', '2', '未缴费', NULL, NULL, '未缴费');
INSERT INTO `room` VALUES (22, '天', '3', '102', NULL, NULL, '未缴费');
INSERT INTO `room` VALUES (23, '1', '1', '101', NULL, NULL, '未缴费');
INSERT INTO `room` VALUES (24, '2', '2', '101', NULL, NULL, '未缴费');

-- ----------------------------
-- Table structure for temperature
-- ----------------------------
DROP TABLE IF EXISTS `temperature`;
CREATE TABLE `temperature`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `temperature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '温度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of temperature
-- ----------------------------
INSERT INTO `temperature` VALUES (31, '2', '2', '天小区3号楼104', '25');
INSERT INTO `temperature` VALUES (32, '2', '2', '天小区3号楼104', '25');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `living_space` double(255, 0) NULL DEFAULT NULL COMMENT '住房面积',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `balance` double(255, 0) NULL DEFAULT NULL COMMENT '余额',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `cell` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '小区',
  `building` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '楼',
  `gate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'http://localhost:8080/files/1737338611019-微信图片_20200607101314.jpg', '1', '1', '龙小区1号楼101', 20, '123456', 15000, 'user', '女', 23, '12345678911', '龙', '1', '101');
INSERT INTO `user` VALUES (2, NULL, '2', '2', '天小区3号楼104', 1, '123456', 3580, 'user', '女', 23, '12345678911', '天', '3', '104');
INSERT INTO `user` VALUES (3, NULL, '111', '李四', '凤小区1号楼102', 100, '123456', 3333, 'user', '男', 20, '12345678911', '凤', '1', '102');
INSERT INTO `user` VALUES (4, NULL, '222', '22', '人小区1号楼102', 222, '123456', 2000, 'user', '男', 22, '12345678911', '人', '1', '102');
INSERT INTO `user` VALUES (7, NULL, '9', '3', '龙小区1号楼102', 50, '123456', 0, 'user', NULL, NULL, NULL, '龙', '1', '102');
INSERT INTO `user` VALUES (8, NULL, '7', '7', '龙小区2号楼103', 50, '123456', 0, 'user', NULL, NULL, NULL, '龙', '2', '103');

SET FOREIGN_KEY_CHECKS = 1;
