# NotShitProject
和TGboy，大丸子的每日哲学


### 第一次更新
**SQL语句**
___article___
create table article
(
id          int(10)      not null comment '文章ID'
primary key,
author      varchar(255) not null comment '文章作者',
create_time datetime     null comment '存入时间',
update_time datetime     null comment '更新时间',
title       varchar(255) not null comment '文章标题',
essay       text         null comment '文章',
essay       text         null comment '文章'
);


### 2022/09/27 第二次更新

- **重置自增**：~~ALTER TABLE `article` DROP `id`;
  ALTER TABLE `article` ADD `id` INT( 11 ) NOT NULL FIRST;
  ALTER TABLE `article` MODIFY COLUMN `id` INT( 11 ) NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY(id);~~ 并不是，明天再改