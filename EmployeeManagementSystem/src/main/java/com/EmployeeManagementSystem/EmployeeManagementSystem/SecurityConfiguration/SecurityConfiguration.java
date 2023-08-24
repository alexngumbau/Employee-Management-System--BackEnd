package com.EmployeeManagementSystem.EmployeeManagementSystem.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        // Set your configuration on the auth object

        auth.inMemoryAuthentication()
                .withUser("Alex")
                .password("alex")
                .roles("ADMIN")

                .and()

                .withUser("Sammy")
                .password("sammy")
                .roles("USER");


    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/employees/create").hasRole("ADMIN")
                .antMatchers("/api/v1/employees/**").hasRole("ADMIN")
                .antMatchers("/api/v1/employees/all").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and().formLogin();
    }

}
