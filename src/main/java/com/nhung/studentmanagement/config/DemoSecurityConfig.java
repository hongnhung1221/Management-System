package com.nhung.studentmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nhung.studentmanagement.service.StudentService;
import com.nhung.studentmanagement.service.TeacherService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(studentAuthenticationProvider());

        auth.authenticationProvider(teacherAuthenticationProvider());


        auth.inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("1"))
            .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() 
            .authorizeRequests()
            .antMatchers("/css/**", "/js/**", "/images/**").permitAll() 
            .antMatchers("/").authenticated()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/student/**").hasRole("STUDENT")
            .antMatchers("/teacher/**").hasRole("TEACHER")
            .and()
            .formLogin()
                .loginPage("/showLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
            .and()
            .logout().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider studentAuthenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(studentService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    @Bean
    public DaoAuthenticationProvider teacherAuthenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(teacherService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}