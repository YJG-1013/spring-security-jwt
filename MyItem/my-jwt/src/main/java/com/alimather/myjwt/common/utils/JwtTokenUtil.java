package com.alimather.myjwt.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * @author: yjg
 * @date:2020-9-6 13:19:33
 * @description: jwt生成token
 */
public class JwtTokenUtil {

    @Autowired
    private static RedisUtil redisUtil;

    // 寻找证书文件
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, "123456".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray()); // jwt 为 命令生成整数文件时的别名
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成token
     * @param subject （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims 自定义身份信息
     * @return
     */
    public static String generateToken(String subject, int expirationSeconds, Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥私钥
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * @author: yjg
     * @date: 2020-9-7 19:24:46
     * @deprecation: 解析token,获得subject中的信息
     */
    public static String parseToken(String token, String salt) {
        String subject = null;
        try {
            subject = getTokenBody(token).getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

    //获取token自定义属性
    public static Map<String,Object> getClaims(String token){
        Map<String,Object> claims = null;
        try {
            claims = getTokenBody(token);
        }catch (Exception e) {
        }

        return claims;
    }

    // 是否已过期
    public static boolean isExpiration(String expirationTime){
        String currentTime = DateUtil.getTime();
        if(DateUtil.compareDate(currentTime,expirationTime)){
            //当前时间比过期时间小，失效
            return true;
        }else{
            return false;
        }
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }

}
