package cn.coloray.website.config;

import cn.coloray.website.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启访问方法的权限验证配合@PreAuthorize注解使用
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthorizationFilter jwtFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);
        // 禁止启用“防止伪造的跨域攻击”这种防御机制
        http.csrf().disable();
        // 调用formLogin()表示启用登录表单页和登出页，如果未调用此方法，则没有登录表单页和登出页
        //http.formLogin();
        // 白名单
// 使用1个星号，表示通配此层级的任意资源，例如：/admin/*，可以匹配 /admin/delete、/admin/add-new
// 但是，不可以匹配多个层级，例如：/admin/*，不可以匹配 /admin/9527/delete
// 使用2个连续的星号，表示通配任何层级的任意资源，例如：/admin/**，可以匹配 /admin/delete、/admin/9527/delete
        String[] urls = {
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs",
                "/admin/login"
        };
        // 配置URL的访问控制
        http.authorizeRequests() // 配置URL的访问控制
                .mvcMatchers(urls) // 匹配某些URL
                .permitAll() // 直接许可，即：不需要通过认证就可以直接访问
                .anyRequest() // 任何请求
                .authenticated(); // 以上配置的请求需要是通过认证的

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }
}
