create table blog(
    id int not null primary key auto_increment comment '主键id',
    title varchar(200) not null default '' comment '标题',
    description varchar(300) not null default '' comment '摘要',
    tags varchar(200) not null default '' comment '标签',
    blog text comment '博文',
    insert_time datetime not null default now() comment '插入时间',
    update_time datetime not null default now() comment '更新时间'
) comment '博客表';

# drop table blog;

alter table blog add column soft_delete tinyint not null default 0 comment '软删除标签：0（未删除）；1（删除）';