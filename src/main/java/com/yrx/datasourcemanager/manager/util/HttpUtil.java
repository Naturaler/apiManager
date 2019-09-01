package com.yrx.datasourcemanager.manager.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by r.x on 2019/8/31.
 */
@Slf4j
public class HttpUtil {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static String get(String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("第三方接口请求失败 url:{}", url, e);
            return null;
        }
    }


    public static String postJson(String url, String json) {
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("第三方接口请求失败 url:{}, body:{}", url, json, e);
            return null;
        }
    }

    public static String postJsonWithHeader(String url, String json, Map<String, String> headers) {
        try {
            OkHttpClient client = new OkHttpClient();
            Headers requestHeaders = Headers.of(headers);
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .headers(requestHeaders)
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("第三方接口请求失败 url:{}, body:{}", url, json, e);
            return null;
        }
    }
}
