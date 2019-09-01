package com.yrx.datasourcemanager.manager.util.encry;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by r.x on 2019/9/1.
 */
public class MD5Util {

    public static String encry(String source) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(source.getBytes());
            StringBuilder builder = new StringBuilder();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    builder.append("0");
                }
                builder.append(str);
            }

            // 标准的md5加密后的结果
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encry(InputStream in) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[8192];
            int byteCount;
            while ((byteCount = in.read(bytes)) > 0) {
                digester.update(bytes, 0, byteCount);
            }
            byte[] digest = digester.digest();

            // byte -128 ---- 127
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                int a = b & 0xff;
                String hex = Integer.toHexString(a);
                if (hex.length() == 1) {
                    hex = 0 + hex;
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
