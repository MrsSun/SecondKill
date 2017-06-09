
CREATE DATABASE IF NOT EXISTS secondkill;

-- 使用数据库
use secondkill;

-- 用户表
DROP TABLE IF EXISTS user;

CREATE TABLE user(
  `user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(128) NOT NULL UNIQUE COMMENT '用户名',
  `password` VARCHAR(128) NOT NULL COMMENT '密码',
  PRIMARY KEY (user_id)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 物品库存表
DROP TABLE IF EXISTS goods;

CREATE TABLE goods(
  `goods_id` BIGINT NOT NUll AUTO_INCREMENT COMMENT '物品ID',
  `name` VARCHAR(120) NOT NULL COMMENT '物品名称',
  `number` int NOT NULL COMMENT '库存数量',
  `start_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_time`   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (goods_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
INSERT into goods(name,number,start_time,end_time)
VALUES
  ('iphone',100,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('小米',200,'2016-01-01 00:00:00','2016-01-02 00:00:00');

DROP TABLE IF EXISTS record;

-- 记录表
CREATE TABLE record(
  `record_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `goods_id` BIGINT NOT NULL COMMENT '物品ID',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY(record_id),
  KEY idx_user(user_id),
  KEY idx_create_time(create_time)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';
