package com.mark.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.mark.demo.security.ApplicationUserRoles.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Method to enable authorization and authentication
     * of any requests using Basic Auth
     * antMatchers() whitelists URLs
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails markNzeiUser = User.builder()
                .username("marknzei")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name()) // ROLE_STUDENT
                .build();

        UserDetails adminUser = User.builder()
                .username("joanna")
                .password(passwordEncoder.encode("kanyinsola"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                markNzeiUser,
                adminUser
        );
    }
}
