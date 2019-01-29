package com.dika.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

/**
 * @ClassName AuthConfig
 * @Description TODO
 * @Author qianqiang
 * @Date 2019/1/11
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder builder)
            throws Exception {
         builder.inMemoryAuthentication()
                 .withUser("123@189.cn").password("user").roles("USER").and()
                 .withUser("jn").password("user").roles("USER").and()
                 .withUser("admin").password("admin").roles("ADMIN").and()
                 .withUser("qd").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bootstrap/**", "/pages/login.html", "/login", "/plugins/**", "/dist/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/pages/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("userName")
                .passwordParameter("password")
                .defaultSuccessUrl("/base.html").permitAll()
                .and()
                .rememberMe()
                .key("9D119EE5A2B7DAF6B9DC1EF871D0AC3C")
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?expired");
        http.csrf().disable();
        http.headers().disable();
    }

}
