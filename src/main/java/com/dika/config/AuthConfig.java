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
                 .withUser("jn").password("user").roles("USER").and()
                 .withUser("admin").password("admin").roles("ADMIN").and()
                 .withUser("qd").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/bootstrap/**", "/login.html", "/login", "/static/index.html", "/static/**").permitAll()
                .antMatchers("/bootstrap/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("userName")
                .passwordParameter("password")
                .defaultSuccessUrl("/index.html").permitAll()
                .and()
                .rememberMe()
                .key("9D119EE5A2B7DAF6B9DC1EF871D0AC3C")
                .and()
//                .exceptionHandling().authenticationEntryPoint( macLoginUrlAuthenticationEntryPoint());
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?expired");
        http.csrf().disable();
        http.headers().disable();
    }

}
