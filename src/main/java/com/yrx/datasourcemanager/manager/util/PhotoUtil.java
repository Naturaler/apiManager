package com.yrx.datasourcemanager.manager.util;

import com.yrx.datasourcemanager.manager.util.encry.Base64Util;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.plantuml.SourceStringReader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by r.x on 2019/11/7.
 */
@Slf4j
public class PhotoUtil {

    public static String downloadAndBase64Encode(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .callTimeout(3000, TimeUnit.MILLISECONDS) // 总超时，包括连接和读取
                .build();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Response response = okHttpClient.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();
            byte[] source = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(source)) != -1) {
                outputStream.write(source, 0, count);
            }
            outputStream.flush();
            return Base64Util.encode(outputStream.toByteArray());
        } catch (IOException | NullPointerException e) {
            log.error("图片下载或base64加密异常 url:{}", url, e);
            return null;
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error("ByteArrayOutputStream close error url:{}", url, e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573148860437&di=e8491525baa8ce81f34909cdefac83ff&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn08%2F179%2Fw600h379%2F20181116%2Fdd9f-hnvukff6121895.jpg";
        // System.out.println(downloadAndBase64Encode(url));

        OutputStream png = new FileOutputStream(new File("E:\\tmp\\agent\\seq.png"));
        String source = "@startuml\n";
        source += "Bob -> Alice : hello\n";
        source += "@enduml\n";

        SourceStringReader reader = new SourceStringReader(source);
// Write the first image to "png"
//         String desc = reader.outputImage(png).getDescription();
        System.out.println("source = " + source);
        String image = reader.generateImage(png);
        System.out.println(image);
// Return a null string if no generation
    }
}
