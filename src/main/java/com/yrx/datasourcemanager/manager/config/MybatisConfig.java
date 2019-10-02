package com.yrx.datasourcemanager.manager.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by r.x on 2019/9/12.
 */
@Configuration
@MapperScan("com.yrx.datasourcemanager.manager.dao")
public class MybatisConfig {

}
