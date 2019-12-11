package com.yrx.dataSourceManager.apimanager.source.vo;

import lombok.Data;

import java.util.Map;

/**
 * Created by r.x on 2019/12/11.
 */
@Data
public class BaseVO {
    // 接口编号
    private Integer apiCode;
    private String idCard;
    private String name;
    private String phone;
    // 其它入参
    private Map<String, Object> additionalParams;
}
