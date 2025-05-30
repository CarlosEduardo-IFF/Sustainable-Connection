package edu.iff.project.sustainableconnection.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .cors() .configurationSource(corsConfigurationSource())
                .and()  // <--- Continua com o restante da chain
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/drop-off-points").permitAll()
                        .requestMatchers(HttpMethod.GET, "/addresses").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reward-items").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/top-points").permitAll()
                        .requestMatchers(HttpMethod.GET, "/discarded-item-categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/discarded-items/by-client").permitAll()
                        
                        // Libera o Swagger
                        .requestMatchers(
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/addresses").permitAll()
                        .requestMatchers(HttpMethod.POST, "/discarded-item-categories").permitAll()
                        .requestMatchers(HttpMethod.POST, "/discarded-items").permitAll()
                        .requestMatchers(HttpMethod.POST, "/drop-off-points").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reward-items").permitAll()
                        
                        .requestMatchers(HttpMethod.DELETE, "/addresses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/discarded-item-categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/discarded-items").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/drop-off-points").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/reward-items").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("https://front-project-production-c51f.up.railway.app")); // Origem específica
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true); // Agora pode ser true com origem específica

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
}