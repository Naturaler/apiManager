package com.yrx.datasourcemanager.manager.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by r.x on 2019/9/1.
 */
@Data
@Builder
public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    private static <T> Response response(Integer code, String msg, T data) {
        return Response.builder().code(code).msg(msg).data(data).build();
    }

    public static <T> Response<T> success(T data) {
        return Response.<T>builder().code(1).msg("成功").data(data).build();
    }

    public static <T> Response<T> fail(T data) {
        return Response.<T>builder().code(-1).msg("失败").data(data).build();
    }
}
