package com.example.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoConfig extends WebSecurityConfigurerAdapter {

    /**
     * 1] Authentication Type.
     * 2] Authorization. (roles, antmatchers)
     * 3] Password Encoder.
     * */

    /**
     * UserDetailsService : service used to get the info/details like username, password, authorities.
     * UserDetails : interface which should be implemented by your entity class which is going to be stored in the DB.
     * */

    /**
     * In-Memory Authentication : Uses UsernamePasswordAuthenticationFilter.
     *                            Fetching the user details from some class(User) which is getting the info from memory.
     * UserDetailsService : Uses UsernamePasswordAuthenticationFilter.
     *                      Fetching the user details from our custom class which we have written which is getting the info from external datasource.
     *
     * */

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/testcode").hasAuthority("qa")
                .antMatchers("/developcode").hasAuthority("dev")
                .antMatchers("/accessserver/**").hasAnyAuthority("qa", "dev")
                .antMatchers("/home/**", "/signup/**").permitAll()
                .and()
                .formLogin();

    }

    /** Non safe methods (methods which are changing the state of data in the application) can not be permit all with csrf enabled.
     *  Whenever you try to do permitAll() for postMapping, Putmapping, Deletemapping() or any other mapping other than GetMapping
     *  it will not work with csrf enabled.
     *  By default csrf is enabled.
     */
    // plain text password ==> encoded password ==> BCryptPasswordEncoder
    @Bean
    PasswordEncoder getPE() {
        return new BCryptPasswordEncoder();
    }
}
