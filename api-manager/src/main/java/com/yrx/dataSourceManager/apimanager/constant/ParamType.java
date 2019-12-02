package com.yrx.dataSourceManager.apimanager.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by r.x on 2019/12/2.
 * 参数类型
 */
@Getter
@AllArgsConstructor
public enum ParamType {
    INTEGER("integer"),
    STRING("string"),
    BOOLEAN("boolean"),
    LIST("list"),
    MAP("map");

    private String type;

}
