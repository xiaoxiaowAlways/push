
SET FOREIGN_KEY_CHECKS=0;
-- use db_name;

DROP TABLE IF EXISTS `push_sys_info`;
CREATE TABLE `push_sys_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_name` varchar(255) NOT NULL DEFAULT '' COMMENT '系统名称',
  `sys_id` varchar(255) NOT NULL DEFAULT '' COMMENT '系统ID',
  `sys_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '系统描述',
  `c_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `m_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_id` (`sys_id`) USING BTREE COMMENT 'ID不可重复',
  UNIQUE INDEX `uq_name` (`sys_name`) COMMENT 'NAME不可重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推送-系统信息表';

DROP TABLE IF EXISTS `push_message`;
CREATE TABLE `push_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_id`  varchar(50) NOT NULL DEFAULT '0' COMMENT '系统ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '内容',
  `extras` varchar(2000) DEFAULT '' COMMENT '额外参数，JSON格式',
  `user_id` varchar(11) NOT NULL DEFAULT '' COMMENT '用户ID',
  `push_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '推送类型 1:单播 2:广播',
  `device_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '设备类型 0:所有设备 1:Android 2:iOS',
  `device_token` varchar(255) NOT NULL DEFAULT '' COMMENT '设备token',
  `push_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '推送状态 0:待推送 1:推送中 2:推送完成',
  `count` tinyint(2) NOT NULL DEFAULT '0' COMMENT '推送次数',
  `valid_flag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否有效 1:有效 2:无效',
  `start_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `c_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `m_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_userId` (`user_id`,`push_status`,`valid_flag`) COMMENT '用户消息列表查询'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推送消息记录表';