package com.yrx.datasourcemanager.manager.pojo.es;

import lombok.Data;

import java.util.List;

/**
 * Created by r.x on 2019/11/12.
 */
@Data
// @Document(indexName = "demo", type = "demo")
public class EsDemo {
    private String name;
    private Integer age;
    private Double salery;
    private Boolean marriage;
    private String crete_time;
    private List<ParentBean> parent;

    @Data
    public static class ParentBean {
        private String name;
        private String major;
    }
}
