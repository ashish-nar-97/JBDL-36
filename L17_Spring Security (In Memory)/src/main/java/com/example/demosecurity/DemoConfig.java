package com.example.demosecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Karan")
                .password("karan123")
                .authorities("qa")
                .and()
                .withUser("Rashmi")
                .password("rashmi123")
                .authorities("dev");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/testcode").hasAuthority("qa")
                .antMatchers("/developcode").hasAuthority("dev")
                .antMatchers("/accessserver/**").hasAnyAuthority("qa", "dev")
                .antMatchers("/home/**").permitAll()
                .and()
                .formLogin();

        /**
         * 1] Role based access : your ant matchers can have multiple roles for a set of APIs
         *    and your user in the DB will have single role in the authority column of table.
         *    e.g.
         *    API Level :
         *    /testing ==> "qa"
         *    /developing ==> "dev"
         *    /accessserver ==> "qa", "dev"
         *
         *    Table Level :
         *    username      authority
         *    Karan         qa
         *    Rashmi        dev
         *
         * 2] Action based access : your ant matcher will have only 1 action for a set of APIs
         *    and your user in the DB will have multiple actions in the authorities column.
         *
         *    e.g.
         *    API Level :
         *    /testing ==> "test"
         *    /developing ==> "development"
         *    /accessserver ==> "server"
         *
         *    Table Level :
         *    username      authority
         *    Karan         test:server
         *    Rashmi        development:server
         *
         *
         * */

    }

    @Bean
    PasswordEncoder getPE() {
        return NoOpPasswordEncoder.getInstance();
    }
}
