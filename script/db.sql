create database data_source;

use data_source;

create table data_source_config (
    id int not null primary key auto_increment comment '自增主键',
    name varchar(50) not null comment '数据源名称',
    create_author varchar(50) not null comment '创建者',
    create_time timestamp not null default now() comment '创建时间',
    update_author varchar(50) not null comment '最后更新者',
    update_time timestamp not null default now() comment '更新时间'
) comment '数据源表';

# drop table api_config;
create table api_config (
    id int not null primary key auto_increment comment '自增主键',
    data_source_id int not null comment '数据源id',
    api_name varchar(50) not null comment '接口名称',
    api_type varchar(50) not null comment '接口类型，运营商、淘宝、ocr等',
    http_url varchar(200) not null comment '接口请求地址',
    http_type varchar(10) not null comment 'http请求方式',
    bill_type varchar(50) not null comment '计费方式',
    bill_field_path varchar(100) not null comment '计费字段json path路径',
    bill_value varchar(20) not null comment '计费值',
    api_docs varchar(500) not null default '' comment '接口文档',
    create_author varchar(50) not null comment '创建者',
    create_time timestamp not null default now() comment '创建时间',
    update_author varchar(50) not null comment '最后更新者',
    update_time timestamp not null default now() comment '更新时间'
) comment '第三方接口配置表';

# drop table param_config;
create table param_config (
    id int not null primary key auto_increment comment '自增主键',
    param_name varchar(50) not null comment '参数名称，英文',
    param_type varchar(20) not null comment '参数类型',
    description varchar(200) not null default '' comment '参数描述，中文',
    create_author varchar(50) not null comment '创建者',
    create_time timestamp not null default now() comment '创建时间',
    update_author varchar(50) not null comment '最后更新者',
    update_time timestamp not null default now() comment '更新时间'
) comment '参数配置表';

# drop table api_param_mapping;
create table api_param_mapping (
    id int not null primary key auto_increment comment '自增主键',
    api_id int not null comment '接口id',
    param_id int not null comment '参数id',
    param_zone tinyint not null comment '参数位置：0(header)、1(body)、2(url)、3(form)',
    is_required boolean not null default false comment '是否必填',
    process_step varchar(100) not null default '' comment '参数加工步骤id，按顺序用英文逗号连接',
    create_author varchar(50) not null comment '创建者',
    create_time timestamp not null default now() comment '创建时间',
    update_author varchar(50) not null comment '最后更新者',
    update_time timestamp not null default now() comment '更新时间'
) comment '接口参数映射表';

create table api_invoke_history (
    id int not null primary key auto_increment comment '自增主键',
    api_id int not null comment '接口id',
    is_fee boolean not null comment '是否计费',
    param varchar(200) not null comment '调用入参',
    data text not null comment '调用结果',
    create_time timestamp not null default now() comment '创建时间'
) comment '接口调用记录';

# drop table param_process_step;
create table param_process_step (
    id int not null primary key auto_increment comment '自增主键',
    api_id int not null comment 'api_config主键',
    param_id int not null comment 'param_config主键',
    process_type varchar(20) not null comment '加工类型',
    process_value varchar(200) not null comment '加工的入参id，param_config的id',
    create_author varchar(50) not null comment '创建者',
    create_time timestamp not null default now() comment '创建时间',
    update_author varchar(50) not null comment '最后更新者',
    update_time timestamp not null default now() comment '更新时间'
) comment '参数加工步骤';