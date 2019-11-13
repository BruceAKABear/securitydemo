package pro.dengyi.securitydemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pro.dengyi.securitydemo.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //定义header常量
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SUBJECT = "dengyi";
    //定义过期时间常量（毫秒）
    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;
    public static final String APPSECRET_KEY = "congge_secret";
    //定义权限的key常量
    private static final String ROLE_CLAIMS = "role";


    /**
     * 生成jwt加密后字符串
     * <br/>
     *
     * @param user 用户对象
     * @return java.lang.String
     * @author dengyi
     * @date 2019/11/13 13:03
     */
    public static String generateJsonWebToken(User user) {
        if (user.getId() == null || user.getUserName() == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, "admin");
        String token = Jwts
                .builder()
                .setSubject(SUBJECT)
                .setClaims(map)
                .claim("userId", user.getId())
                .claim("userName", user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    /**
     * 生成token
     *
     * @param username
     * @param role
     * @return
     */
    public static String createToken(String username, String role) {
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("userName", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 获取用户角色
     *
     * @param token
     * @return
     */
    public static String getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("rol").toString();
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(12345L);
        user.setUserName("大熊");
        user.setPassword("123456");
        String s = generateJsonWebToken(user);
        System.out.println(s);
        //eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJ1c2VyTmFtZSI6IuWkp-eGiiIsImV4cCI6MTU3NDIyNjEzOSwidXNlcklkIjoxMjM0NSwiaWF0IjoxNTczNjIxMzM5fQ.drjVyhXymOTMIGfVHEjVuk3kldo2x3UERQqcLZT6HA8


    }

}