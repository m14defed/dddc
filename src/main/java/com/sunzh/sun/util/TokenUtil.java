package com.sunzh.sun.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TokenUtil {
    private static String key="sunzh";
    private static Integer exp=3600*1000;

    /**
    * 生成Jwttoken
    * @param username
    * @return token
    * */
    public static String getJwt(String username,Integer type){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username",username);
        claims.put("type",type);
        String compact = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .compact();
        return compact;
    }
    /**
    * jwt解析
    * @param jwt
     * @return username
    * */
    public static String getUsername(String jwt){
        String body = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody().get("username").toString();
        return body;
    }
    public static Integer getType(String jwt){
        String body = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody().get("type").toString();
        int i = Integer.parseInt(body);
        return i;
    }
    public static boolean verify(String jwt) {
        try {
                Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody().get("username").toString();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

}
