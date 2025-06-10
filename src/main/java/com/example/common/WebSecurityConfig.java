package com.example.common;

import com.example.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .formLogin(form -> form
                    .loginPage("/") // 独自ログインページを指定
                    .loginProcessingUrl("/login") // POST先（<form th:action="@{/login}"）と一致
                    .usernameParameter("mailAddress")
                    .passwordParameter("password")
                    .successHandler(loginSuccessHandler)
                    .failureUrl("/?error")
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
            )
            .authorizeHttpRequests(auth -> auth
                    // ログイン画面・登録画面・CSSなどは認証不要にする
                    .requestMatchers("/", "/toInsert", "/insert", "/css/**", "/js/**", "/img/**").permitAll()
                    .anyRequest().authenticated()
            )
            .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
