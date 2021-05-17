package com.example.dashboardspring;

import com.example.dashboardspring.entity.User;
import com.example.dashboardspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    public SecurityConfig(UserService userService, BCryptPasswordEncoder passwordEncoder, CustomOAuth2UserService oAuth2UserService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (UserDetailsService) email -> {
            Optional<User> user = userService.findUserByEmail(email);
            if(user.isEmpty()) {
                throw new UsernameNotFoundException("No user found with email adress: " + email);
            }
            return user.get();
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/", "assets/**", "/inscription", "/css/*", "/css/**", "/img/*", "/img/**", "/js/*", "/js/**",  "/accueil")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/connection")
                .defaultSuccessUrl("/authentifier")
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/connection")
                .userInfoEndpoint().userService(oAuth2UserService)
                .and()
                .and()
                .logout();

    }
}
