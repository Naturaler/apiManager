package com.yrx.dataSourceManager.apimanager.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by r.x on 2019/12/2.
 * 请求方式
 */
@Getter
@AllArgsConstructor
public enum HttpType {
    GET("get"),
    POST("post");

    private String type;

}
