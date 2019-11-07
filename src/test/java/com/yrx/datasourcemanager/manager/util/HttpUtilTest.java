package com.yrx.datasourcemanager.manager.util;

import com.alibaba.fastjson.JSON;
import com.yrx.datasourcemanager.manager.util.encry.Base64Util;
import com.yrx.datasourcemanager.manager.vo.DemoVO;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by r.x on 2019/11/7.
 */
public class HttpUtilTest {

    @Test
    public void get() {
        String url = "http://www.baidu.com";
        System.out.println(HttpUtil.get(url));
    }

    @Test
    public void get1() {
    }

    @Test
    public void getInputStream() {
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573148860437&di=e8491525baa8ce81f34909cdefac83ff&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn08%2F179%2Fw600h379%2F20181116%2Fdd9f-hnvukff6121895.jpg";
        InputStream inputStream = HttpUtil.getInputStream(url);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] source = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(source)) != -1) {
                outputStream.write(source, 0, count);
            }
            outputStream.flush();
            System.out.println(Base64Util.encode(outputStream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postJson() {
        String url = "http://localhost:9090/demo/post";
        DemoVO vo = new DemoVO();
        vo.setIdCardNum("idCardNum");
        vo.setName("name");
        vo.setPhone("phone");
        System.out.println(HttpUtil.postJson(url, JSON.toJSONString(vo)));
    }

    @Test
    public void postJson1() {
    }
}