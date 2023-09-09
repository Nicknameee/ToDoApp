package io.uwp.digital.configuration;

import io.uwp.digital.configuration.filters.AuthorizationTokenRequestFilter;
import io.uwp.digital.configuration.handlers.AuthenticationLogoutSecurityHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration {
    private final AuthenticationLogoutSecurityHandler authenticationLogoutSecurityHandler;
    private final UserDetailsService userDetailsService;
    private final AuthorizationTokenRequestFilter authorizationTokenRequestFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authorizationTokenRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login", "/logout", "/api/v1/users").permitAll()
                .antMatchers("/util/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutSuccessHandler(authenticationLogoutSecurityHandler)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.POST.name()))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(false)
                .ignoring()
                .antMatchers();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(7);
    }

    @Bean
    protected DaoAuthenticationProvider repositoryAuthenticationProvider() {
        DaoAuthenticationProvider repositoryAuthenticationProvider = new DaoAuthenticationProvider();
        repositoryAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        repositoryAuthenticationProvider.setUserDetailsService(userDetailsService);
        return repositoryAuthenticationProvider;
    }
}
