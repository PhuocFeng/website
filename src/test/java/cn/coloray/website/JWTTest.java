package cn.coloray.website;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JWTTest {

    String secretKey = "8ugLIU$#%^*&dlii9MutjKJoHhldSF)JFDL*urfda(&%&^invjfdsa";

    @Test
    void generateJWT() {
        Date date = new Date(System.currentTimeMillis() + 5 * 60 * 1000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 9527);
        claims.put("username", "Zhangsan");

        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload
                .setClaims(claims)
                // Signature
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // 完成
                .compact();
        System.out.println(jwt);
    }

    @Test
    void parse() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTUyNywiZXhwIjoxNzA0Nzg3MzE3LCJ1c2VybmFtZSI6IlpoYW5nc2FuIn0.yJgO7UtK_2i9vF78otwWaebCjKqh8Dck_JVgEVwG1Zs";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        Object id = claims.get("id");
        Object username = claims.get("username");
        System.out.println("id=" + id);
        System.out.println("username=" + username);
    }

}
