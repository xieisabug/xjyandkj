/*
MySQL Data Transfer
Source Host: localhost
Source Database: teamhome
Target Host: localhost
Target Database: teamhome
Date: 2013-3-15 9:37:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(20) default NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for article
-- ----------------------------
CREATE TABLE `article` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `content` varchar(5000) default NULL,
  `date` date default NULL,
  `authorName` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for attribute
-- ----------------------------
CREATE TABLE `attribute` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `value` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
CREATE TABLE `menu` (
  `id` int(11) NOT NULL auto_increment,
  `code` varchar(4) default NULL,
  `name` varchar(50) default NULL,
  `link` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
CREATE TABLE `message` (
  `id` int(11) NOT NULL auto_increment,
  `content` varchar(500) default NULL,
  `contact` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'xjy..xjy', 'xjy..xjy');
