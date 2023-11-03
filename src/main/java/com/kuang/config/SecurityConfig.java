package com.kuang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/11/3 21:46
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 授权：链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问，但是功能页只有对应有权限的人才能访问
        // 认证请求
        http.authorizeRequests()
                // 首页所有人可以访问
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 没有权限会默认到登录页中:会自动发送/login请求
        http.formLogin();

        // 关闭csrf功能:防止跨站攻击
        http.csrf().disable();

        // 注销:开启了注销功能，跳转到首页
        http.logout().logoutSuccessUrl("/");
    }

    // 认证：
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* Spring Security 5.0+新增了很多加密方法，此时存放password需要加密否则就会报错 */
        // 使用在内存认证，也可以连接数据库认证auth.jdbcAuthentication()
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("tom").password(new BCryptPasswordEncoder().encode("123")).roles("vip2", "vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("vip1")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3");

    }
}
