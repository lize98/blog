/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50544
 Source Host           : localhost:3306
 Source Schema         : blogs

 Target Server Type    : MySQL
 Target Server Version : 50544
 File Encoding         : 65001

 Date: 19/04/2020 17:21:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章内容',
  `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '首图地址',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章类型，原创、转载',
  `views` int(11) NOT NULL COMMENT '浏览次数',
  `appreciation` bit(1) NOT NULL COMMENT '是否开启赞赏',
  `share_statement` bit(1) NOT NULL COMMENT '是否开启分享声明',
  `commentabled` bit(1) NOT NULL COMMENT '是否开启评论',
  `published` bit(1) NOT NULL COMMENT '是否发布',
  `recommend` bit(1) NOT NULL COMMENT '是否推荐',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '描述',
  `type_id` int(11) NOT NULL COMMENT '分类编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `tag_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签编号1,2,3',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_type`(`type_id`) USING BTREE,
  INDEX `fk_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_type` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (5, '学习测试改工作测试', '# fafasdfsafasfasfas', 'https://unsplash.it/800/450?image=1005', '转载', 120, b'1', b'1', b'1', b'1', b'1', '2020-04-17 10:35:25', '2020-04-18 05:47:29', '学习测试改工作测试', 2, 1, '7');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论内容',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '头像地址',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  `admin_comment` bit(1) NOT NULL COMMENT '管理员评论',
  `blog_id` int(11) NOT NULL COMMENT '博客编号',
  `reply_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被回复人昵称',
  `parent_comment_id` int(11) NOT NULL COMMENT '父评论编号',
  `top_comment_id` int(11) NOT NULL COMMENT '记录回复的编号，例如5回复的1，记录的是1',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_blog`(`blog_id`) USING BTREE,
  CONSTRAINT `fk_blog` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, '用户1', '670551262@qq.com', '1测试', '/images/avatar.png', '2020-04-19 06:50:10', b'0', 5, NULL, -1, -1);
INSERT INTO `t_comment` VALUES (3, '用户2', '670551262@qq.com', '测试2', '/images/avatar.png', '2020-04-19 06:55:09', b'0', 5, NULL, -1, -1);
INSERT INTO `t_comment` VALUES (4, '李泽', 'ee@163.comaa', '你好，测试', '/images/avatar.png', '2020-04-19 07:20:50', b'0', 5, '用户1', 1, 1);
INSERT INTO `t_comment` VALUES (5, '管理员', 'ee@163.comaa', '你好，测试成功了吗', '/images/avatar.png', '2020-04-19 07:22:30', b'0', 5, '李泽', 4, 1);
INSERT INTO `t_comment` VALUES (6, '管理员', '670551262@qq.com', '测试成功 了', '/images/me.jpg', '2020-04-19 07:24:00', b'1', 5, '管理员', 5, 1);
INSERT INTO `t_comment` VALUES (7, '管理员', '670551262@qq.com', '测试成功了', '/images/me.jpg', '2020-04-19 07:24:44', b'1', 5, '管理员', 5, 1);
INSERT INTO `t_comment` VALUES (8, '管理员', '670551262@qq.com', '正在测试中', '/images/me.jpg', '2020-04-19 07:30:28', b'1', 5, '管理员', 5, 1);
INSERT INTO `t_comment` VALUES (9, '沐安', 'ee@163.comaa', '再次测试', '/images/avatar.png', '2020-04-19 07:47:04', b'0', 5, '用户2', 3, 3);
INSERT INTO `t_comment` VALUES (10, '沐安', 'ee@163.comaa', '发发发', '/images/avatar.png', '2020-04-19 07:47:18', b'0', 5, '沐安', 9, 3);
INSERT INTO `t_comment` VALUES (11, '用户1', '670551262@qq.com', '你好', '/images/avatar.png', '2020-04-19 08:10:43', b'0', 5, '李泽', 4, 1);

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (4, 'Java');
INSERT INTO `t_tag` VALUES (5, '前端基础');
INSERT INTO `t_tag` VALUES (6, '动漫');
INSERT INTO `t_tag` VALUES (7, '面试');
INSERT INTO `t_tag` VALUES (8, '数据库');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, '学习');
INSERT INTO `t_type` VALUES (2, '工作');
INSERT INTO `t_type` VALUES (3, '影视');
INSERT INTO `t_type` VALUES (4, '总结');
INSERT INTO `t_type` VALUES (5, '生活');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，编号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱地址',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '头像地址',
  `type` int(1) NOT NULL COMMENT '用户类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '管理员', 'admin', '96e79218965eb72c92a549dd5a330112', '670551262@qq.com', '/images/me.jpg', 1, '2020-04-17 09:19:57', '2020-04-17 09:20:00');

SET FOREIGN_KEY_CHECKS = 1;
