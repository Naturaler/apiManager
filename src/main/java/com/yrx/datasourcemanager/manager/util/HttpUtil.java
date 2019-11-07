package com.yrx.datasourcemanager.manager.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * Created by r.x on 2019/8/31.
 */
@Slf4j
public class HttpUtil {
    private static final OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final int TIMEOUT = 3000;

    static {
        client = new OkHttpClient.Builder()
                // .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS) // 连接超时
                // .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS) //读取超时
                .retryOnConnectionFailure(false)
                .callTimeout(TIMEOUT, TimeUnit.MILLISECONDS) // 总超时，包括连接和读取
                .build();
    }

    public static String get(String url) {
        return get(url, null, null);
    }

    public static String get(String url, Map<String, String> headers, Map<String, String> queryParams) {
        try {
            if (headers == null) {
                headers = new HashMap<>();
            }
            Headers requestHeaders = Headers.of(headers);
            if (queryParams != null && queryParams.size() > 0) {
                StringJoiner paramJoiner = new StringJoiner("&", "?", "");
                queryParams.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .forEach(paramJoiner::add);
                url += paramJoiner.toString();
            }
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .headers(requestHeaders)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException | NullPointerException e) {
            log.error("get请求失败 url:{}, header:{}, params:{}", url, headers, queryParams, e);
            return null;
        }
    }

    /**
     * 可用于下载图片
     */
    public static InputStream getInputStream(String url) {
        try {
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().byteStream();
        } catch (IOException | NullPointerException e) {
            log.error("get请求失败 url:{}", url, e);
            return null;
        }
    }


    public static String postJson(String url, String json) {
        return postJson(url, json, null, null);
    }

    public static String postJson(String url, String json, Map<String, String> headers, Map<String, String> queryParams) {
        try {
            if (queryParams != null && queryParams.size() > 0) {
                StringJoiner paramJoiner = new StringJoiner("&", "?", "");
                queryParams.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .forEach(paramJoiner::add);
                url += paramJoiner.toString();
            }
            if (headers == null) {
                headers = new HashMap<>();
            }
            Headers requestHeaders = Headers.of(headers);
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .headers(requestHeaders)
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException | NullPointerException e) {
            log.error("post请求失败 url:{}, body:{}", url, json, e);
            return null;
        }
    }
}
