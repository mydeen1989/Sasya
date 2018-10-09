package com.sasya.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${basic_auth_user}")
    private String USER;

    @Value("${basic_auth_pasword}")
    private String PASSWORD;

    @Value("${basic_auth_admin}")
    private String ADMIN;


    @Value("${role_user}")
    private String ROLE_USER;


    @Value("${role_admin}")
    private String ROLE_ADMIN;

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/v1/user/**").hasRole(ROLE_USER)
                .anyRequest().authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(encoder()).withUser(USER).password(new BCryptPasswordEncoder().encode(PASSWORD)).roles(ROLE_USER);
        auth.inMemoryAuthentication().passwordEncoder(encoder()).withUser(ADMIN).password(new BCryptPasswordEncoder().encode(PASSWORD)).roles(ROLE_ADMIN);
    }


}