package com.sunny.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //h2-console 사용에 대한 허용
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //회원 관리 처리 API(POST/user/**)에 대해 CSRF 무시
        http.csrf()
                .ignoringAntMatchers("/user/**");
        http.authorizeRequests()
                .antMatchers()
                .hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and()
                //로그인 기능 허용
                .formLogin()
                //로그인 뷰 제공(Post/user/login)
                .loginPage("/user/login")
                //로그인 처리(POST/user/login)
                .loginProcessingUrl("/user/login")
                //로그인 처리 후 성공 시 URL
                .defaultSuccessUrl("/")
                .usernameParameter("userId")
                .passwordParameter("password")
                //로그인 처리 후 실패 시 URL
                .failureUrl("/user/login?error")
                .permitAll()
                .and()
                //로그아웃 기능 허용
                .logout()
                .permitAll();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("userId")
                .password("password")
                .roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}

