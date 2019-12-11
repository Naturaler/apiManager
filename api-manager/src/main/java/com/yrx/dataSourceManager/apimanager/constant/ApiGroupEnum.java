package com.yrx.dataSourceManager.apimanager.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by r.x on 2019/12/11.
 */
@AllArgsConstructor
@Getter
public enum  ApiGroupEnum {
    OPERATOR(1, "运营商"),
    ;

    private Integer code;
    private String name;


}
