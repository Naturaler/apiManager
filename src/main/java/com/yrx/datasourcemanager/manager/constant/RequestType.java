package com.yrx.datasourcemanager.manager.constant;

/**
 * Created by r.x on 2019/9/1.
 * 请求方式
 */
public enum RequestType {
    GET("GET", "URL", "get请求"),
    POST_FORM("POST", "FORM", "post表单"),
    POST_JSON("POST", "JSON", "post json"),
    ;

    private String type;
    private String paramType;
    private String desc;

    RequestType(String type, String paramType, String desc) {
        this.type = type;
        this.paramType = paramType;
        this.desc = desc;
    }

    /**
     * 校验请求方式是否符合要求
     *
     * @param requestType 请求方式
     * @return
     * <ul>
     *  <li>true: 符合要求</li>
     *  <li>false: 不符合要求</li>
     * </ul>
     */
    public static boolean verifyRequestType(String requestType) {
        for (RequestType type : RequestType.values()) {
            if (requestType.toUpperCase().equals(type.getType())) {
                return true;
            }
        }
        return false;
    }

    public String getType() {
        return type;
    }

    public String getParamType() {
        return paramType;
    }

    public String getDesc() {
        return desc;
    }
}
