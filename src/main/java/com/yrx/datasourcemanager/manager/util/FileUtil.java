package com.yrx.datasourcemanager.manager.util;

import com.yrx.datasourcemanager.manager.util.encry.Base64Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by r.x on 2019/10/13.
 */
public class FileUtil {

    public static void main(String[] args) throws IOException {
        File file = new File("");
        FileInputStream fileInputStream = new FileInputStream(file);
        // 用 ByteArrayOutputStream 将byte数组保存到内存中，并通过 toByteArray 方法一次性输出
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int read = 0;
        while ((read = fileInputStream.read(b)) > 0) {
            byteArrayOutputStream.write(b, 0, read);
        }
        System.out.println(Base64Util.encode(byteArrayOutputStream.toByteArray()));
    }
}
