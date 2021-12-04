package com.test.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    /**
     * 用于用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 该方法通过在配置类中定义用户名和密码

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); //定义密码编码器
        String password=bCryptPasswordEncoder.encode("123");
        auth.inMemoryAuthentication().withUser("test").password(password).roles("test");//
         */
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 用于配置访问控制权限
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers(HttpMethod.GET,"/test/login").hasAnyAuthority("1")
                ;

    }

    //@Bean
    //public PasswordEncoder passwordEncoder(){
    //    return new BCryptPasswordEncoder();
    //}
}
