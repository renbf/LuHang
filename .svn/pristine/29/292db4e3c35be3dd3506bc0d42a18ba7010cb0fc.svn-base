package com.gfc.library.utils.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author admin
 * @date 2019/4/4
 * @function 注释
 **/
public class EncodeUtil {
    /**
     * 32位MD5算法
     */
    public static String encodeMD5(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
