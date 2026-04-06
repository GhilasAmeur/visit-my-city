package com.example.backend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                        .csrf(csrf -> csrf.disable())
                        .sessionManagement(session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(auth -> auth

                                // public tout le monde
                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/auth/**",
                                        "/register/**",
                                        "/city/cities",
                                        "/city/city/*",
                                        "/building/buildings",
                                        "/category/categories",
                                        "/category/*",
                                        "/building/categorie/*",
                                        "/city/*",
                                        "/building/*",
                                        "/building/city/*",
                                        "/building/cityname/*",
                                        "/building/buildingdto/*",
                                        "/building/buildingsdto/city/*",
                                        "/building/buildingsdto/category/*",
                                        "/building/buildingdto/cityname/*"
                                ).permitAll()

                                // EXPERT juste c tt
                                .requestMatchers(
                                        "/city/add/**",
                                        "/city/update/**",
                                        "/city/delete/**",
                                        "/building/update/**",
                                        "/building/delete/**",
                                        "/building/add/**"
                                ).hasRole("EXPERT")

                                //auhentifier c tt
                                .requestMatchers(
                                        "/comment/**",
                                        "/like/**",
                                        "/favorites/**"
                                ).authenticated() //permitAll() ici ca fait pas mal pour tester sans token

                                .anyRequest().authenticated() //permitAll() ici aussi

                        )

                        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();

        }

        @Bean
         public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
            }

            @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
            return config.getAuthenticationManager();
            }


}


