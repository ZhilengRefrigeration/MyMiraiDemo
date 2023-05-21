package com.function.jrrp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * @author 制冷
 * @date 2023/5/21 14:20
 * @description JrrpMain
 * 实现.jrrp指令
 */
public class JrrpMain  {


    /**
     * 根据QQid在每一天生成一个不同的随机数
     * @param QQid 指令发起者QQid
     * @return 0-99的随机数
     */
    public int getUserCharacterValue(int QQid){
        String hashString = getHashString(QQid + LocalDate.now(ZoneOffset.UTC).toString());
        int hashInt = Math.abs(hashString.hashCode());
        return hashInt % 100;
    }

    /**
     * 计算 SHA-256 哈希值的方法。在此方法中，我们将 `input` 字符串转换成一个字节数组，
     * 接着使用 java.security.MessageDigest 类中的 `getInstance("SHA-256")`
     * 方法以获取 SHA-256 算法的实例，并使用 `digest()` 方法计算哈希值
     * @param input 字符串
     * @return 哈希
     */
    private static String getHashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

