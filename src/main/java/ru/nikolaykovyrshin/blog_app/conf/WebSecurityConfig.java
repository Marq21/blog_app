package ru.nikolaykovyrshin.blog_app.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.nikolaykovyrshin.blog_app.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
        log.info("@Autowired: " + userDetailsService.toString());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Into filter chain");
        http
                .authorizeHttpRequests()
                .requestMatchers("resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")
                .requestMatchers("/**")
                .permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/admin/create_article", true)
                .and()
                .logout()
                .logoutSuccessUrl("/success.html")
                .and()
                .cors().disable()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Into password");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        log.info("Into auth manager");
        log.info("Auth provider: " + authProvider.getUserCache());
        return new ProviderManager(authProvider);
    }

}
