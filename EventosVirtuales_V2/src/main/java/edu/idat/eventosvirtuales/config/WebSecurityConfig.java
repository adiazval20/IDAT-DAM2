package edu.idat.eventosvirtuales.config;

import edu.idat.eventosvirtuales.exception.RestAccessDeniedHandler;
import edu.idat.eventosvirtuales.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario/persona").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario/auth").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler());
    }
}
