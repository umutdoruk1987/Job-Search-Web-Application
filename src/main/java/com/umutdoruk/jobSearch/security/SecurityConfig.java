package com.umutdoruk.jobSearch.security;

import com.umutdoruk.jobSearch.enums.Role;
import com.umutdoruk.jobSearch.security.utils.EndPointsPatterns;
import com.umutdoruk.jobSearch.service.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserServiceImpl customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()/*.anyRequest()*/
                .requestMatchers(EndPointsPatterns.noRolesPatterns)
                .permitAll()
                .requestMatchers(EndPointsPatterns.bothRolesPatterns)
                .hasAnyAuthority(Role.ROLE_EMPLOYER.name(),Role.ROLE_CANDIDATE.name())
                .requestMatchers(EndPointsPatterns.candidatesPatterns)
                .hasAnyAuthority(Role.ROLE_CANDIDATE.name())
                .requestMatchers(EndPointsPatterns.employersPatterns)
                .hasAnyAuthority(Role.ROLE_EMPLOYER.name())
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


}