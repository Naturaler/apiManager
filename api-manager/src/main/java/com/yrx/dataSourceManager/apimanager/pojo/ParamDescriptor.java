package com.yrx.dataSourceManager.apimanager.pojo;

import lombok.Data;
import org.springframework.asm.Type;

/**
 * Created by r.x on 2019/12/2.
 * 参数说明
 */
@Data
public class ParamDescriptor {
    private String name;
    private String desc;
    private Type type;
}
