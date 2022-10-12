package com.example.minor1.configuration;

import com.example.minor1.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Value("${users.student.authority}")
    String studentAuthority;

    @Value("${users.admin.authority}")
    String adminAuthority;

    // Spring Security Configuration.

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/student-for-admin/**").hasAuthority(adminAuthority)
                .antMatchers(HttpMethod.POST, "/student/**").permitAll()
                .antMatchers("/student/**", "/transaction/**").hasAuthority(studentAuthority)
                .antMatchers("/books/search").hasAnyAuthority(studentAuthority, adminAuthority)
                .antMatchers("/book/**").hasAnyAuthority(adminAuthority)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();

    }



    // Redis Configuration.


}
