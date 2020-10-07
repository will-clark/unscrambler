package org.willclark.unscrambler.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.NonNull;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(@NonNull HttpSecurity security) throws Exception {
        security.httpBasic().disable();
        security.formLogin().disable();
    }

}
