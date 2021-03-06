package com.jcharnet.app.ideas.bin2dec;

import com.okta.spring.boot.oauth.Okta;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;  
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;  


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class Bin2DecSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Override
    public void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/actuator/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2ResourceServer().jwt();

        http.cors();

        // force a non-empty response body for 401's to make the response more browser friendly
        Okta.configureResourceServer401ResponseBody(http);

    }

    @Override
    public void configure (AuthenticationManagerBuilder auth) throws Exception {

    }
}
