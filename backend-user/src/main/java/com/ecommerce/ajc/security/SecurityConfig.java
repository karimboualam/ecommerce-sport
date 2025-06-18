package com.ecommerce.ajc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Permet @PreAuthorize
//@EnableMethodSecurity(prePostEnabled = true) // Permet @PreAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // Active CORS
                .and()
                .csrf().disable()
                .authorizeRequests()
                // Autorise Swagger
                .antMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v2/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**"
                ).permitAll()
               .antMatchers("/api/auth/**").permitAll()
           //     .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/client/**").hasAuthority("ROLE_CLIENT")

               // .antMatchers("/api/client/**").hasRole("CLIENT")
            //    .antMatchers("/api/client/**").hasAnyRole( "ADMIN", "CLIENT")
            //    .antMatchers("/api/client/**").hasAnyRole("CLIENT")

                .anyRequest().authenticated()
                .and()
            //    .sessionManagement().disable();
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
