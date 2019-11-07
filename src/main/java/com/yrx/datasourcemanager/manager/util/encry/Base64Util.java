package com.yrx.datasourcemanager.manager.util.encry;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;

/**
 * Created by r.x on 2019/9/1.
 */
@Slf4j
public class Base64Util {
    private static final String SERVICE_NAME = "base64";
    private static final String UTF8_CHARSET = "utf-8";

    public static String encode(String source) {
        return encode(source.getBytes(StandardCharsets.UTF_8));
    }

    public static String encode(byte[] source) {
        try {
            return new BASE64Encoder().encode(source);
        } catch (Exception e) {
            log.error("{}编码异常！source:{}", SERVICE_NAME, source, e);
            return null;
        }
    }
}
