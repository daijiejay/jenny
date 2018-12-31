/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : jenny_sys

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-12-31 16:36:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_action`;
CREATE TABLE `sys_action` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '功能编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  `action_name` varchar(20) NOT NULL COMMENT '功能名称',
  `action_type` enum('OPERATE','TOOLBAR') NOT NULL DEFAULT 'OPERATE' COMMENT '功能类型：列表单行操作,列表工具栏',
  `mutual_type` enum('CONFIRM','FORM') NOT NULL DEFAULT 'CONFIRM' COMMENT '交互类型：确认,表单',
  `interface_server_id` varchar(255) NOT NULL COMMENT '接口服务名',
  `interface_url` varchar(255) NOT NULL COMMENT '接口地址',
  `interface_method` varchar(10) NOT NULL COMMENT '接口方式',
  `form_target` varchar(50) DEFAULT NULL COMMENT '表单目标元素',
  PRIMARY KEY (`action_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_action
-- ----------------------------
INSERT INTO `sys_action` VALUES ('1', '2', '添加', 'TOOLBAR', 'FORM', 'SYS', '/sysuser/add', 'POST', '#saveSysUser');
INSERT INTO `sys_action` VALUES ('2', '2', '编辑', 'OPERATE', 'FORM', 'SYS', '/sysuser/update', 'PUT', '#saveSysUser');
INSERT INTO `sys_action` VALUES ('3', '2', '禁用', 'OPERATE', 'CONFIRM', 'SYS', '/sysuser/enable/{userId}', 'PUT', null);
INSERT INTO `sys_action` VALUES ('4', '2', '启用', 'OPERATE', 'CONFIRM', 'SYS', '/sysuser/notenable/{userId}', 'PUT', null);
INSERT INTO `sys_action` VALUES ('5', '2', '删除', 'OPERATE', 'CONFIRM', 'SYS', '/sysuser/delete/{userId}', 'DELETE', null);
INSERT INTO `sys_action` VALUES ('6', '2', '设置角色', 'OPERATE', 'FORM', 'SYS', '/sys', 'POST', null);
INSERT INTO `sys_action` VALUES ('7', '3', '添加', 'TOOLBAR', 'FORM', 'SYS', '/sysrole/add', 'POST', '#saveSysRole');
INSERT INTO `sys_action` VALUES ('8', '3', '编辑', 'OPERATE', 'FORM', 'SYS', '/sysrole/update', 'PUT', '#saveSysRole');
INSERT INTO `sys_action` VALUES ('9', '3', '禁用', 'OPERATE', 'CONFIRM', 'SYS', '/sysrole/enable/{roleId}', 'PUT', null);
INSERT INTO `sys_action` VALUES ('10', '3', '启用', 'OPERATE', 'CONFIRM', 'SYS', '/sysrole/notenable/{roleId}', 'PUT', null);
INSERT INTO `sys_action` VALUES ('11', '3', '删除', 'OPERATE', 'CONFIRM', 'SYS', '/sysrole/delete/{roleId}', 'DELETE', null);
INSERT INTO `sys_action` VALUES ('12', '3', '设置菜单', 'OPERATE', 'FORM', 'SYS', '/sys', 'POST', '#saveSysAction');
INSERT INTO `sys_action` VALUES ('13', '4', '添加', 'TOOLBAR', 'FORM', 'SYS', '/sysaction/add', 'POST', '#saveSysAction');
INSERT INTO `sys_action` VALUES ('14', '4', '编辑', 'OPERATE', 'FORM', 'SYS', '/sysaction/update', 'PUT', '#saveSysAction');

-- ----------------------------
-- Table structure for sys_icon
-- ----------------------------
DROP TABLE IF EXISTS `sys_icon`;
CREATE TABLE `sys_icon` (
  `icon_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图标编号',
  `icon_code` varchar(50) NOT NULL COMMENT '图标代码',
  `icon_name` varchar(20) NOT NULL COMMENT '图标名称',
  PRIMARY KEY (`icon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_icon
-- ----------------------------
INSERT INTO `sys_icon` VALUES ('1', 'fas fa-font', '文本');
INSERT INTO `sys_icon` VALUES ('2', 'fas fa-home', '首页');
INSERT INTO `sys_icon` VALUES ('3', 'fas fa-user', '用户');
INSERT INTO `sys_icon` VALUES ('4', 'fas fa-phone', '电话');
INSERT INTO `sys_icon` VALUES ('5', 'fas fa-key', '钥匙');
INSERT INTO `sys_icon` VALUES ('6', 'fas fa-envelope', '邮件');
INSERT INTO `sys_icon` VALUES ('7', 'fas fa-birthday-cake', '蛋糕');
INSERT INTO `sys_icon` VALUES ('8', 'fas fa-plus-square', '添加');
INSERT INTO `sys_icon` VALUES ('9', 'fas fa-edit', '编辑');
INSERT INTO `sys_icon` VALUES ('10', 'fas fa-cogs', '设置中心');
INSERT INTO `sys_icon` VALUES ('11', 'fas fa-cog', '设置');
INSERT INTO `sys_icon` VALUES ('12', 'fas fa-lock', '锁定');
INSERT INTO `sys_icon` VALUES ('13', 'fas fa-unlock', '解锁');
INSERT INTO `sys_icon` VALUES ('14', 'fas fa-trash', '删除');
INSERT INTO `sys_icon` VALUES ('15', 'fas fa-users-cog', '设置角色');
INSERT INTO `sys_icon` VALUES ('16', 'fas fa-tasks', '任务');
INSERT INTO `sys_icon` VALUES ('17', 'fas fa-bell', '消息');
INSERT INTO `sys_icon` VALUES ('18', 'fas fa-bell-slash', '禁止消息');
INSERT INTO `sys_icon` VALUES ('19', 'fas fa-check-circle', '圆形正确');
INSERT INTO `sys_icon` VALUES ('20', 'fas fa-check-square', '方形正确');
INSERT INTO `sys_icon` VALUES ('21', 'fas fa-child', '小孩');
INSERT INTO `sys_icon` VALUES ('22', 'fas fa-clock', '时钟');
INSERT INTO `sys_icon` VALUES ('23', 'fas fa-file-medical', '创建');
INSERT INTO `sys_icon` VALUES ('24', 'fas fa-plus', '增加');
INSERT INTO `sys_icon` VALUES ('25', 'fas fa-search-plus', '批搜索');
INSERT INTO `sys_icon` VALUES ('26', 'fas fa-search-minus', '单搜索');
INSERT INTO `sys_icon` VALUES ('27', 'fas fa-sitemap', '思维图');
INSERT INTO `sys_icon` VALUES ('28', 'fas fa-columns', '列');
INSERT INTO `sys_icon` VALUES ('29', 'fab fa-phabricator', '功能');
INSERT INTO `sys_icon` VALUES ('30', 'fas fa-search', '搜索');
INSERT INTO `sys_icon` VALUES ('31', 'fas fa-mail-reply-all', '返回');
INSERT INTO `sys_icon` VALUES ('32', 'fas fa-fw fa-upload', '上传');
INSERT INTO `sys_icon` VALUES ('33', 'fas fa-chart-line', '图表');
INSERT INTO `sys_icon` VALUES ('34', 'fas fa-flag', '旗帜');
INSERT INTO `sys_icon` VALUES ('35', 'fas fa-folder', '文件');
INSERT INTO `sys_icon` VALUES ('36', 'fas fa-eye', '查看');
INSERT INTO `sys_icon` VALUES ('37', 'fas fa-send', '发送');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_code` int(11) NOT NULL COMMENT '菜单代码',
  `menu_name` varchar(20) NOT NULL COMMENT '菜单名称',
  `level` int(2) NOT NULL COMMENT '菜单等级',
  `parent_id` int(11) NOT NULL COMMENT '父级菜单编号',
  `parent_code` int(11) DEFAULT NULL COMMENT '父级菜单代码',
  `sort` int(2) NOT NULL COMMENT '排序',
  `mapping` varchar(255) DEFAULT NULL COMMENT '映射路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `enable` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否禁用',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '10000', '系统管理', '1', '0', '0', '1', '', 'fa fa-cogs', '\0');
INSERT INTO `sys_menu` VALUES ('2', '10100', '系统用户管理', '2', '1', '10000', '1', 'systemManage/sysUserManage.html', null, '\0');
INSERT INTO `sys_menu` VALUES ('3', '10200', '系统角色管理', '2', '1', '10000', '2', 'systemManage/sysRoleManage.html', '', '\0');
INSERT INTO `sys_menu` VALUES ('4', '10301', '菜单管理', '3', '11', '10300', '1', 'systemManage/developManage/sysMenuManage.html', '', '\0');
INSERT INTO `sys_menu` VALUES ('11', '10300', '系统开发管理', '2', '1', '10000', '3', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('13', '10400', '系统界面管理', '2', '1', '10000', '4', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('17', '10302', '图标管理', '3', '11', '10300', '2', 'systemManage/developManage/sysIconManage.html', '', '\0');
INSERT INTO `sys_menu` VALUES ('18', '10303', '服务管理', '3', '11', '10300', '3', 'systemManage/developManage/sysServerManage.html', '', '\0');
INSERT INTO `sys_menu` VALUES ('19', '20000', '系统监控中心', '1', '0', '0', '2', '', 'fas fa-chart-line', '\0');
INSERT INTO `sys_menu` VALUES ('20', '30000', '任务中心', '1', '0', '0', '3', '', 'fas fa-tasks', '\0');
INSERT INTO `sys_menu` VALUES ('21', '40000', '消息中心', '1', '0', '0', '4', '', 'fas fa-send', '\0');
INSERT INTO `sys_menu` VALUES ('22', '50000', '工作流管理', '1', '0', '0', '5', '', 'fas fa-flag', '\0');
INSERT INTO `sys_menu` VALUES ('23', '20202', '访问日志', '3', '37', '20200', '2', 'surveillanceCenter/visitLog.html', '', '\0');
INSERT INTO `sys_menu` VALUES ('24', '20203', '系统日志', '3', '37', '20200', '3', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('25', '30100', '任务调度', '2', '20', '30000', '1', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('26', '20204', 'SQL日志', '3', '37', '20200', '4', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('27', '20103', 'Session监控', '3', '36', '20100', '3', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('28', '30200', '任务日志', '2', '20', '30000', '2', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('29', '40100', '生产者', '2', '21', '40000', '1', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('30', '40200', '消费者', '2', '21', '40000', '2', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('31', '40300', '消息日志', '2', '21', '40000', '3', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('32', '20102', '服务状态', '3', '36', '20100', '2', 'surveillanceCenter/serverManage/serverStatus.html', '', '\0');
INSERT INTO `sys_menu` VALUES ('34', '60000', '文件管理', '1', '0', '0', '6', '', 'fas fa-folder', '\0');
INSERT INTO `sys_menu` VALUES ('35', '10304', '接口文档', '3', '11', '10300', '4', 'systemManage/developManage/document.html', '', '\0');
INSERT INTO `sys_menu` VALUES ('36', '20100', '服务管理', '2', '19', '20000', '1', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('37', '20200', '日志管理', '2', '19', '20000', '2', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('38', '20201', '日志配置', '3', '37', '20200', '1', null, null, '\0');
INSERT INTO `sys_menu` VALUES ('39', '20101', '服务监控', '3', '36', '20100', '1', 'surveillanceCenter/serverManage/serverMonitoring.html', '', '\0');

-- ----------------------------
-- Table structure for sys_menu_authorized
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_authorized`;
CREATE TABLE `sys_menu_authorized` (
  `menu_authorized_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色菜单授权编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`menu_authorized_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_authorized
-- ----------------------------
INSERT INTO `sys_menu_authorized` VALUES ('1', '1', '1');
INSERT INTO `sys_menu_authorized` VALUES ('2', '1', '2');
INSERT INTO `sys_menu_authorized` VALUES ('3', '1', '3');
INSERT INTO `sys_menu_authorized` VALUES ('4', '1', '4');
INSERT INTO `sys_menu_authorized` VALUES ('15', '2', '1');
INSERT INTO `sys_menu_authorized` VALUES ('16', '2', '2');
INSERT INTO `sys_menu_authorized` VALUES ('18', '3', '1');
INSERT INTO `sys_menu_authorized` VALUES ('19', '3', '3');
INSERT INTO `sys_menu_authorized` VALUES ('20', '1', '11');
INSERT INTO `sys_menu_authorized` VALUES ('21', '1', '17');
INSERT INTO `sys_menu_authorized` VALUES ('22', '1', '18');
INSERT INTO `sys_menu_authorized` VALUES ('23', '4', '1');
INSERT INTO `sys_menu_authorized` VALUES ('24', '4', '11');
INSERT INTO `sys_menu_authorized` VALUES ('25', '4', '4');
INSERT INTO `sys_menu_authorized` VALUES ('26', '4', '17');
INSERT INTO `sys_menu_authorized` VALUES ('27', '4', '18');

-- ----------------------------
-- Table structure for sys_operate_authorized
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_authorized`;
CREATE TABLE `sys_operate_authorized` (
  `operate_authorized_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作授权编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  `operate_type` enum('TABLE') NOT NULL DEFAULT 'TABLE' COMMENT '操作功能类型：表格',
  `operate_id` int(11) NOT NULL COMMENT '操作功能编号',
  PRIMARY KEY (`operate_authorized_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_operate_authorized
-- ----------------------------
INSERT INTO `sys_operate_authorized` VALUES ('35', '2', '2', 'TABLE', '1');
INSERT INTO `sys_operate_authorized` VALUES ('36', '2', '2', 'TABLE', '2');
INSERT INTO `sys_operate_authorized` VALUES ('37', '1', '2', 'TABLE', '1');
INSERT INTO `sys_operate_authorized` VALUES ('38', '1', '2', 'TABLE', '4');
INSERT INTO `sys_operate_authorized` VALUES ('39', '1', '2', 'TABLE', '3');
INSERT INTO `sys_operate_authorized` VALUES ('40', '1', '2', 'TABLE', '6');
INSERT INTO `sys_operate_authorized` VALUES ('41', '1', '2', 'TABLE', '2');
INSERT INTO `sys_operate_authorized` VALUES ('42', '1', '2', 'TABLE', '5');
INSERT INTO `sys_operate_authorized` VALUES ('43', '1', '3', 'TABLE', '8');
INSERT INTO `sys_operate_authorized` VALUES ('44', '1', '3', 'TABLE', '11');
INSERT INTO `sys_operate_authorized` VALUES ('45', '1', '3', 'TABLE', '7');
INSERT INTO `sys_operate_authorized` VALUES ('46', '1', '3', 'TABLE', '10');
INSERT INTO `sys_operate_authorized` VALUES ('47', '1', '3', 'TABLE', '9');
INSERT INTO `sys_operate_authorized` VALUES ('48', '1', '3', 'TABLE', '12');
INSERT INTO `sys_operate_authorized` VALUES ('49', '1', '4', 'TABLE', '15');
INSERT INTO `sys_operate_authorized` VALUES ('50', '1', '4', 'TABLE', '22');
INSERT INTO `sys_operate_authorized` VALUES ('51', '1', '4', 'TABLE', '18');
INSERT INTO `sys_operate_authorized` VALUES ('52', '1', '4', 'TABLE', '14');
INSERT INTO `sys_operate_authorized` VALUES ('53', '1', '4', 'TABLE', '21');
INSERT INTO `sys_operate_authorized` VALUES ('54', '1', '4', 'TABLE', '17');
INSERT INTO `sys_operate_authorized` VALUES ('55', '1', '4', 'TABLE', '13');
INSERT INTO `sys_operate_authorized` VALUES ('56', '1', '4', 'TABLE', '20');
INSERT INTO `sys_operate_authorized` VALUES ('57', '1', '4', 'TABLE', '16');
INSERT INTO `sys_operate_authorized` VALUES ('58', '1', '4', 'TABLE', '23');
INSERT INTO `sys_operate_authorized` VALUES ('59', '1', '4', 'TABLE', '19');
INSERT INTO `sys_operate_authorized` VALUES ('60', '3', '3', 'TABLE', '8');
INSERT INTO `sys_operate_authorized` VALUES ('61', '3', '3', 'TABLE', '11');
INSERT INTO `sys_operate_authorized` VALUES ('62', '3', '3', 'TABLE', '7');
INSERT INTO `sys_operate_authorized` VALUES ('63', '3', '3', 'TABLE', '10');
INSERT INTO `sys_operate_authorized` VALUES ('64', '3', '3', 'TABLE', '9');
INSERT INTO `sys_operate_authorized` VALUES ('65', '3', '3', 'TABLE', '12');
INSERT INTO `sys_operate_authorized` VALUES ('66', '1', '17', 'TABLE', '25');
INSERT INTO `sys_operate_authorized` VALUES ('67', '1', '17', 'TABLE', '24');
INSERT INTO `sys_operate_authorized` VALUES ('68', '1', '17', 'TABLE', '26');
INSERT INTO `sys_operate_authorized` VALUES ('69', '4', '4', 'TABLE', '17');
INSERT INTO `sys_operate_authorized` VALUES ('70', '4', '4', 'TABLE', '13');
INSERT INTO `sys_operate_authorized` VALUES ('71', '4', '4', 'TABLE', '20');
INSERT INTO `sys_operate_authorized` VALUES ('72', '4', '4', 'TABLE', '16');
INSERT INTO `sys_operate_authorized` VALUES ('73', '4', '4', 'TABLE', '23');
INSERT INTO `sys_operate_authorized` VALUES ('74', '4', '4', 'TABLE', '19');
INSERT INTO `sys_operate_authorized` VALUES ('75', '4', '4', 'TABLE', '15');
INSERT INTO `sys_operate_authorized` VALUES ('76', '4', '4', 'TABLE', '22');
INSERT INTO `sys_operate_authorized` VALUES ('77', '4', '4', 'TABLE', '18');
INSERT INTO `sys_operate_authorized` VALUES ('78', '4', '4', 'TABLE', '14');
INSERT INTO `sys_operate_authorized` VALUES ('79', '4', '4', 'TABLE', '21');
INSERT INTO `sys_operate_authorized` VALUES ('80', '4', '17', 'TABLE', '24');
INSERT INTO `sys_operate_authorized` VALUES ('81', '4', '17', 'TABLE', '26');
INSERT INTO `sys_operate_authorized` VALUES ('82', '4', '17', 'TABLE', '25');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_code` varchar(20) NOT NULL COMMENT '角色代码',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `enable` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否禁用',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'TEST', '测试工程师', '\0');
INSERT INTO `sys_role` VALUES ('2', 'USER', '用户管理员', '\0');
INSERT INTO `sys_role` VALUES ('3', 'ROLE', '角色管理员', '\0');
INSERT INTO `sys_role` VALUES ('4', 'MENU', '程序员', '\0');

-- ----------------------------
-- Table structure for sys_role_authorized
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authorized`;
CREATE TABLE `sys_role_authorized` (
  `role_authorized_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色授权编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`role_authorized_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_authorized
-- ----------------------------
INSERT INTO `sys_role_authorized` VALUES ('3', '4', '1');
INSERT INTO `sys_role_authorized` VALUES ('4', '9', '3');

-- ----------------------------
-- Table structure for sys_server
-- ----------------------------
DROP TABLE IF EXISTS `sys_server`;
CREATE TABLE `sys_server` (
  `server_id` varchar(50) NOT NULL COMMENT '服务名',
  `server_addr` varchar(255) NOT NULL COMMENT '服务地址',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_server
-- ----------------------------
INSERT INTO `sys_server` VALUES ('doc', 'http://daijie.org:12502/');
INSERT INTO `sys_server` VALUES ('health', 'http://daijie.org:12503/');
INSERT INTO `sys_server` VALUES ('sys', 'http://daijie.org:12501/');

-- ----------------------------
-- Table structure for sys_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_table`;
CREATE TABLE `sys_table` (
  `table_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表格编号',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单编号',
  `table_name` varchar(20) DEFAULT NULL COMMENT '表格名称',
  `interface_server_id` varchar(255) NOT NULL COMMENT '接口服务名',
  `interface_url` varchar(255) NOT NULL COMMENT '接口地址',
  `interface_method` varchar(10) NOT NULL COMMENT '接口方式',
  `table_target` varchar(50) DEFAULT NULL COMMENT '表格目标元素',
  `unique_id` varchar(50) DEFAULT NULL COMMENT '表格行主键编号名',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_table
-- ----------------------------
INSERT INTO `sys_table` VALUES ('1', '2', '用户列表', 'sys', '/sysuser/query', 'GET', '#sysUserTable', 'userId');
INSERT INTO `sys_table` VALUES ('2', '3', '角色列表', 'sys', '/sysrole/query', 'GET', '#sysRoleTable', 'roleId');
INSERT INTO `sys_table` VALUES ('3', '4', '列表功能列表', 'sys', '/systable/action/query', 'GET', '#sysActionTable', 'actionId');
INSERT INTO `sys_table` VALUES ('4', '4', '列表配置列表', 'sys', '/systable/query', 'GET', '#sysTableTable', 'tableId');
INSERT INTO `sys_table` VALUES ('5', '4', '列表字段列表', 'sys', '/systable/column/query', 'GET', '#sysColumnTable', 'columnId');
INSERT INTO `sys_table` VALUES ('6', '2', '选择角色列表', 'sys', '/sysuser/query/selectedRoles', 'GET', '#sysRoleSelectedTable', 'roleId');
INSERT INTO `sys_table` VALUES ('7', '17', '图标列表', 'sys', '/sysicon/query', 'GET', '#sysIconTable', 'iconId');
INSERT INTO `sys_table` VALUES ('8', '18', '服务列表', 'sys', '/sysserver/query', 'GET', '#sysServerTable', 'serverId');
INSERT INTO `sys_table` VALUES ('9', '32', '服务信息列表', 'health', '/serverMonitoring/applications', 'GET', '#serverMonitoringTable', 'name');

-- ----------------------------
-- Table structure for sys_table_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_table_action`;
CREATE TABLE `sys_table_action` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '功能编号',
  `table_id` int(11) NOT NULL COMMENT '表格编号',
  `action_name` varchar(20) NOT NULL COMMENT '功能名称',
  `action_type` enum('OPERATE','TOOLBAR') NOT NULL DEFAULT 'OPERATE' COMMENT '功能类型：列表单行操作,列表工具栏',
  `mutual_type` enum('CONFIRM','FORM','EXTEND') NOT NULL DEFAULT 'CONFIRM' COMMENT '交互类型：确认,表单,扩展',
  `interface_server_id` varchar(255) DEFAULT NULL COMMENT '接口服务名',
  `interface_url` varchar(255) DEFAULT NULL COMMENT '接口地址',
  `interface_method` varchar(10) DEFAULT NULL COMMENT '接口方式',
  `form_target` varchar(50) DEFAULT NULL COMMENT '表单目标元素',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `show_sort` int(5) DEFAULT NULL COMMENT '展示排序',
  PRIMARY KEY (`action_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_table_action
-- ----------------------------
INSERT INTO `sys_table_action` VALUES ('1', '1', '添加', 'TOOLBAR', 'FORM', 'sys', '/sysuser/add', 'POST', '#saveSysUser', 'fas fa-file-medical', '1');
INSERT INTO `sys_table_action` VALUES ('2', '1', '编辑', 'OPERATE', 'FORM', 'sys', '/sysuser/update', 'PUT', '#saveSysUser', 'fa fa-edit', '2');
INSERT INTO `sys_table_action` VALUES ('3', '1', '禁用', 'OPERATE', 'CONFIRM', 'sys', '/sysuser/enable/{userId}', 'PUT', '', 'fa fa-lock', '3');
INSERT INTO `sys_table_action` VALUES ('4', '1', '启用', 'OPERATE', 'CONFIRM', 'sys', '/sysuser/notenable/{userId}', 'PUT', '', 'fa fa-unlock', '4');
INSERT INTO `sys_table_action` VALUES ('5', '1', '删除', 'OPERATE', 'CONFIRM', 'sys', '/sysuser/delete/{userId}', 'DELETE', '', 'fa fa-trash', '5');
INSERT INTO `sys_table_action` VALUES ('6', '1', '设置角色', 'OPERATE', 'EXTEND', '', '', '', '#setRole', 'fas fa-users-cog', '6');
INSERT INTO `sys_table_action` VALUES ('7', '2', '添加', 'TOOLBAR', 'FORM', 'sys', '/sysrole/add', 'POST', '#saveSysRole', 'fas fa-file-medical', null);
INSERT INTO `sys_table_action` VALUES ('8', '2', '编辑', 'OPERATE', 'FORM', 'sys', '/sysrole/update', 'PUT', '#saveSysRole', 'fa fa-edit', null);
INSERT INTO `sys_table_action` VALUES ('9', '2', '禁用', 'OPERATE', 'CONFIRM', 'sys', '/sysrole/enable/{roleId}', 'PUT', '', 'fa fa-lock', null);
INSERT INTO `sys_table_action` VALUES ('10', '2', '启用', 'OPERATE', 'CONFIRM', 'sys', '/sysrole/notenable/{roleId}', 'PUT', '', 'fa fa-unlock', null);
INSERT INTO `sys_table_action` VALUES ('11', '2', '删除', 'OPERATE', 'CONFIRM', 'sys', '/sysrole/delete/{roleId}', 'DELETE', '', 'fa fa-trash', null);
INSERT INTO `sys_table_action` VALUES ('12', '2', '设置菜单权限', 'OPERATE', 'EXTEND', '', '', '', '#setMenu', 'fa fa-tasks', null);
INSERT INTO `sys_table_action` VALUES ('13', '3', '添加', 'TOOLBAR', 'FORM', 'sys', '/systable/action/add', 'POST', '#saveSysAction', 'fas fa-file-medical', '1');
INSERT INTO `sys_table_action` VALUES ('14', '3', '编辑', 'OPERATE', 'FORM', 'sys', '/systable/action/update', 'PUT', '#saveSysAction', 'fa fa-edit', null);
INSERT INTO `sys_table_action` VALUES ('15', '4', '添加', 'TOOLBAR', 'FORM', 'sys', '/systable/add', 'POST', '#saveSysTable', 'fas fa-file-medical', null);
INSERT INTO `sys_table_action` VALUES ('16', '4', '编辑', 'OPERATE', 'FORM', 'sys', '/systable/update', 'PUT', '#saveSysTable', 'fa fa-edit', null);
INSERT INTO `sys_table_action` VALUES ('17', '4', '设置功能', 'OPERATE', 'EXTEND', '', '', null, '#setAction', 'fab fa-phabricator', null);
INSERT INTO `sys_table_action` VALUES ('18', '5', '添加', 'TOOLBAR', 'FORM', 'sys', '/systable/column/add', 'POST', '#saveSysColumn', 'fas fa-file-medical', null);
INSERT INTO `sys_table_action` VALUES ('19', '5', '编辑', 'OPERATE', 'FORM', 'sys', '/systable/column/update', 'PUT', '#saveSysColumn', 'fa fa-edit', null);
INSERT INTO `sys_table_action` VALUES ('20', '4', '设置字段', 'OPERATE', 'EXTEND', '', '', '', '#setColumn', 'fa fa-columns', null);
INSERT INTO `sys_table_action` VALUES ('21', '5', '删除', 'OPERATE', 'CONFIRM', 'sys', '/systable/column/delete/{columnId}', 'DELETE', '', 'fa fa-trash', null);
INSERT INTO `sys_table_action` VALUES ('22', '4', '删除', 'OPERATE', 'CONFIRM', 'sys', '/systable/delete/{tableId}', 'DELETE', '', 'fa fa-trash', null);
INSERT INTO `sys_table_action` VALUES ('23', '3', '删除', 'OPERATE', 'CONFIRM', 'sys', '/systable/action/delete/{actionId}', 'DELETE', '', 'fa fa-trash', null);
INSERT INTO `sys_table_action` VALUES ('24', '7', '添加', 'TOOLBAR', 'FORM', 'sys', '/sysicon/add', 'POST', '#saveSysIcon', 'fas fa-file-medical', null);
INSERT INTO `sys_table_action` VALUES ('25', '7', '编辑', 'OPERATE', 'FORM', 'sys', '/sysicon/update', 'PUT', '#saveSysIcon', 'fa fa-edit', null);
INSERT INTO `sys_table_action` VALUES ('26', '7', '删除', 'OPERATE', 'CONFIRM', 'sys', '/sysicon/delete', 'DELETE', '', 'fa fa-trash', null);
INSERT INTO `sys_table_action` VALUES ('27', '1', '查看', 'OPERATE', 'CONFIRM', '', '', 'GET', '#saveSysUser', 'fa fa-search', '0');
INSERT INTO `sys_table_action` VALUES ('28', '1', '重置密码', 'OPERATE', 'CONFIRM', 'sys', '/sysuser/reset/password/{userId}', 'PUT', '', 'fa fa-mail-reply-all', '2');
INSERT INTO `sys_table_action` VALUES ('29', '8', '添加', 'TOOLBAR', 'FORM', 'sys', '/sysserver/add', 'POST', '#saveSysServer', 'fas fa-plus', '1');
INSERT INTO `sys_table_action` VALUES ('30', '8', '编辑', 'OPERATE', 'FORM', 'sys', '/sysserver/update', 'PUT', '#saveSysServer', 'fas fa-edit', '2');
INSERT INTO `sys_table_action` VALUES ('31', '8', '删除', 'OPERATE', 'CONFIRM', 'sys', '/sysserver/delete', 'DELETE', '', 'fas fa-trash', '3');

-- ----------------------------
-- Table structure for sys_table_column
-- ----------------------------
DROP TABLE IF EXISTS `sys_table_column`;
CREATE TABLE `sys_table_column` (
  `column_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '行编号',
  `table_id` int(11) NOT NULL COMMENT '表格编号',
  `field` varchar(50) NOT NULL COMMENT '列字段名',
  `title` varchar(20) NOT NULL COMMENT '列字段标题',
  `sortable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示排序按扭',
  `show_sort` int(5) DEFAULT NULL COMMENT '展示排序',
  `width` double(20,0) DEFAULT NULL COMMENT '宽度',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_table_column
-- ----------------------------
INSERT INTO `sys_table_column` VALUES ('1', '1', 'userId', '用户编号', '', null, null);
INSERT INTO `sys_table_column` VALUES ('2', '1', 'userName', '用户名称', '', null, null);
INSERT INTO `sys_table_column` VALUES ('3', '1', 'mobile', '手机号', '', null, null);
INSERT INTO `sys_table_column` VALUES ('4', '1', 'email', '邮箱', '', null, null);
INSERT INTO `sys_table_column` VALUES ('5', '1', 'birthday', '生日', '', null, null);
INSERT INTO `sys_table_column` VALUES ('6', '1', 'gender', '性别', '', null, null);
INSERT INTO `sys_table_column` VALUES ('7', '1', 'createTime', '创建时间', '', null, null);
INSERT INTO `sys_table_column` VALUES ('8', '1', 'enable', '是否禁用', '', null, null);
INSERT INTO `sys_table_column` VALUES ('9', '2', 'roleId', '角色编号', '', null, null);
INSERT INTO `sys_table_column` VALUES ('10', '2', 'roleCode', '角色代码', '', null, null);
INSERT INTO `sys_table_column` VALUES ('11', '2', 'roleName', '角色名称', '', null, null);
INSERT INTO `sys_table_column` VALUES ('12', '2', 'enable', '是否禁用', '', null, null);
INSERT INTO `sys_table_column` VALUES ('13', '3', 'actionId', '功能编号', '', null, null);
INSERT INTO `sys_table_column` VALUES ('15', '3', 'actionName', '功能名称', '', null, null);
INSERT INTO `sys_table_column` VALUES ('16', '3', 'actionType', '功能类型', '', null, null);
INSERT INTO `sys_table_column` VALUES ('17', '3', 'mutualType', '交互类型', '', null, null);
INSERT INTO `sys_table_column` VALUES ('18', '3', 'interfaceServerId', '接口服务名', '', null, null);
INSERT INTO `sys_table_column` VALUES ('19', '3', 'interfaceUrl', '接口地址', '', null, null);
INSERT INTO `sys_table_column` VALUES ('20', '3', 'interfaceMethod', '接口方式', '', null, null);
INSERT INTO `sys_table_column` VALUES ('21', '3', 'formTarget', '表单目标元素', '', null, null);
INSERT INTO `sys_table_column` VALUES ('22', '4', 'tableId', '列表编号', '', null, null);
INSERT INTO `sys_table_column` VALUES ('24', '4', 'tableName', '表格名称', '', null, null);
INSERT INTO `sys_table_column` VALUES ('25', '4', 'interfaceServerId', '接口服务名', '', null, null);
INSERT INTO `sys_table_column` VALUES ('26', '4', 'interfaceUrl', '接口地址', '', null, null);
INSERT INTO `sys_table_column` VALUES ('27', '4', 'interfaceMethod', '接口方式', '', null, null);
INSERT INTO `sys_table_column` VALUES ('28', '4', 'tableTarget', '表格目标元素', '', null, null);
INSERT INTO `sys_table_column` VALUES ('29', '4', 'uniqueId', '行主键名', '', null, null);
INSERT INTO `sys_table_column` VALUES ('30', '5', 'title', '列标题名', '', null, null);
INSERT INTO `sys_table_column` VALUES ('31', '5', 'field', '列字段名', '', null, null);
INSERT INTO `sys_table_column` VALUES ('32', '6', 'roleCode', '角色代码', '', null, null);
INSERT INTO `sys_table_column` VALUES ('33', '6', 'roleName', '角色名称', '', null, null);
INSERT INTO `sys_table_column` VALUES ('34', '7', 'iconId', '图标编号', '', null, null);
INSERT INTO `sys_table_column` VALUES ('35', '7', 'iconCode', '图标代码', '', null, null);
INSERT INTO `sys_table_column` VALUES ('36', '7', 'iconName', '图标名称', '', null, null);
INSERT INTO `sys_table_column` VALUES ('37', '7', 'class', '图标展示', '', null, null);
INSERT INTO `sys_table_column` VALUES ('38', '3', 'icon', '图标', '', null, null);
INSERT INTO `sys_table_column` VALUES ('39', '5', 'sortable', '是否显示排序按扭', '', null, null);
INSERT INTO `sys_table_column` VALUES ('40', '5', 'width', '宽度', '', null, null);
INSERT INTO `sys_table_column` VALUES ('41', '5', 'showSort', '排序', '', null, null);
INSERT INTO `sys_table_column` VALUES ('42', '3', 'showSort', '排序', '', null, null);
INSERT INTO `sys_table_column` VALUES ('43', '8', 'serverId', '服务名称', '', '1', null);
INSERT INTO `sys_table_column` VALUES ('44', '8', 'serverAddr', '服务地址', '', '2', null);
INSERT INTO `sys_table_column` VALUES ('45', '9', 'name', '服务名称', '', '1', null);
INSERT INTO `sys_table_column` VALUES ('47', '9', 'status', '状态', '', '3', null);
INSERT INTO `sys_table_column` VALUES ('48', '9', 'statusTimestamp', '构建时间', '', '4', null);
INSERT INTO `sys_table_column` VALUES ('49', '9', 'serverNumber', '构建数量', '', '5', null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密盐',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` bit(1) DEFAULT NULL COMMENT '性别',
  `portrait` longtext COMMENT '头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `admin` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否管理员用户',
  `enable` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否禁用',
  `cancel` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('4', 'test', '18911351016', 'daijie_jay@163.com', '15cd5339a6458322bba60006bdbc8e9f591a3c7a5e6676fc7a9c25ab909be5ab1197774cfd18eb5396698be5dd0c8e8928155c4c890f37d536805bf5d232267f', '7bf7c0ec62f27641e6ebfec1c9f75cd044b0b935f2efe80f981d57375aa425ab', null, null, null, '2018-06-20 18:12:02', '\0', '\0', '\0');
INSERT INTO `sys_user` VALUES ('5', 'rrrr', '18101081031', '', '0e23e62a8f88e7cdcb2620a5da2b633e5803ee944ade90125134629768bcbe984d6377a8bcd583d8d163658b2e8ccbeebca7c3a4047952c4efd9e3efe5d4ef75', 'eea5dd757c73e94ed2e6a98e7d8ff70c5677ba686f42bd35ffa2e4ec37940cee', null, null, null, '2018-06-21 17:36:27', '\0', '', '');
INSERT INTO `sys_user` VALUES ('6', 'tttt', '18101081031', 'daijie_jay@163.com', '', '6b81340c9e3c605166c1857ce0e7befc359e9ee02c61ef9a66c263f2368cf81c', null, null, null, '2018-06-21 17:54:49', '\0', '\0', '\0');
INSERT INTO `sys_user` VALUES ('8', 'trttrtr', '18101081031', '', 'f72ebcc429c8433b8c58fa579718204d4543a539bd7ecf1c1b58c996e619b86c423e76e722706ea552a89a7e122974e738067d1637446f0e04d5f03f983469ee', '5c4ff08d85dbcd5a5449cce787b0e20bdfc1383e5c691bebd8096d2544ad4ab2', null, null, null, '2018-06-21 17:56:50', '\0', '\0', '');
INSERT INTO `sys_user` VALUES ('9', 'manager', '18101081031', '', '', 'acfc2f206ff4b38c512ef05c079412ec775b310ffc1a7c676f86922b73321dfc', null, null, 'data:image/png;base64,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', '2018-06-21 17:57:16', '\0', '\0', '\0');
INSERT INTO `sys_user` VALUES ('11', 'admin', '18911351016', 'daijie_jay@163.com3', '207c6f514521b2be6bfc93f5b05c3679db7ff9a9f9156a0c2a91e8d273b6444b7dd9c13f4c49e8163b07ceb040867f830451a125615a431aee40db9b358262d0', 'bb5ef70259c43c779fa6b3c7d00d86ea4122d09948607f0bf00701f9ceeb42ad', '1999-04-04', null, null, '2018-07-03 10:50:26', '', '\0', '\0');
