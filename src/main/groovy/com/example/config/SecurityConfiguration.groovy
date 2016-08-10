package com.example.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Spring Security Config
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //Disable csrf because the clients need to support it.
            .csrf().disable()

            .authorizeRequests()
                //Allow anybody to see what we offer
                .antMatchers("/api/items/**").permitAll()

                //all other requests require authentication
                .anyRequest().authenticated()
            .and()
                .httpBasic()
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Simple in memory authentication
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
