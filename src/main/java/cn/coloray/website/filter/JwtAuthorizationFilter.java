package cn.coloray.website.filter;

import cn.coloray.website.security.LoginPrincipal;
import cn.coloray.website.web.JsonResult;
import cn.coloray.website.web.ServiceCode;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${website.jwt.secret-key}")
    private String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        log.debug("JWT: {}", jwt);

        if(!StringUtils.hasText(jwt)||jwt.length()<113){
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = null;
        response.setContentType("application/json; charset=utf-8");
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (SignatureException e) {
            String message = "非法访问！";
            log.warn("解析JWT时出现SignatureException，响应消息：{}", message);
            JsonResult<Void> jsonResult
                    = JsonResult.fail(ServiceCode.ERR_JWT_SIGNATURE, message);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(JSON.toJSONString(jsonResult));
            printWriter.close();
            return;
        } catch (MalformedJwtException e) {
            String message = "非法访问！";
            log.warn("解析JWT时出现MalformedJwtException，响应消息：{}", message);
            JsonResult<Void> jsonResult
                    = JsonResult.fail(ServiceCode.ERR_JWT_MALFORMED, message);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(JSON.toJSONString(jsonResult));
            printWriter.close();
            return;
        } catch (ExpiredJwtException e) {
            String message = "您的登录信息已过期，请重新登录！";
            log.warn("解析JWT时出现ExpiredJwtException，响应消息：{}", message);
            JsonResult<Void> jsonResult
                    = JsonResult.fail(ServiceCode.ERR_JWT_EXPIRED, message);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(JSON.toJSONString(jsonResult));
            printWriter.close();
            return;
        }
        Long id =  claims.get("id", Long.class);
        String username =  claims.get("username", String.class);
        LoginPrincipal principal = new LoginPrincipal();
        principal.setId(id);
        principal.setUsername(username);
    }
}
