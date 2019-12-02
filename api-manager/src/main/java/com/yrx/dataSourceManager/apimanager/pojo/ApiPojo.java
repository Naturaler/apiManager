package com.yrx.dataSourceManager.apimanager.pojo;

import lombok.Data;

/**
 * Created by r.x on 2019/12/2.
 */
@Data
public class ApiPojo {
    // 主键
    private Integer id;
    // 接口名称
    private String name;
    // 接口状态：是否可用
    private Boolean enabled;
    // 调用次数
    private Integer count;
}
