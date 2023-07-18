package com.reboot.rebootweb.common.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class Md5Util {
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean md5Equals(String md5Str, String str) {
        return md5Str.equals(md5(str));
    }
}
