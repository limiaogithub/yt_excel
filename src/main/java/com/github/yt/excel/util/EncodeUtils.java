package com.github.yt.excel.util;

import java.io.UnsupportedEncodingException;

public class EncodeUtils {

    /**
     * 根据请求的浏览器不同将文字编码进行不同转换
     *
     * @param agent agent
     * @param s     名字
     * @return String
     */
    public static String toUtf8String(String agent, String s) {
        try {
            boolean isFireFox = (agent != null && agent.toLowerCase().indexOf("firefox") != -1);
            if (isFireFox) {
                s = new String(s.getBytes("UTF-8"), "ISO8859-1");
            } else {
                s = toUtf8String(s);
                if ((agent != null && agent.indexOf("MSIE") != -1)) {
                    if (s.length() > 150) {
                        s = new String(s.getBytes("UTF-8"), "ISO8859-1");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

}
