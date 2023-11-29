package br.com.plataforma.ead.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());

        http.csrf((csrf) -> csrf
                .ignoringRequestMatchers("/api/logins/autenticacao")
                .ignoringRequestMatchers("/api/usuarios/cadastro")
                .ignoringRequestMatchers("/api/logins/esqueci-senha")
                .ignoringRequestMatchers("/api/logins/redefinir-senha")
                .ignoringRequestMatchers("/api/logins/alterar-senha")
                .ignoringRequestMatchers("/api/logins/alterar-senha-recuperada")


        );
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/logins/autenticacao").permitAll()
                        .requestMatchers("/api/usuarios/cadastro").permitAll()
                        .requestMatchers("/api/logins/esqueci-senha").permitAll()
                        .requestMatchers("/api/logins/redefinir-senha").permitAll()
                        .requestMatchers("/api/logins/alterar-senha").permitAll()
                        .requestMatchers("/api/logins/alterar-senha-recuperada").permitAll()
                        .anyRequest().authenticated()
                ).formLogin((form) -> form
                        .loginPage("http://localhost:3000/login")
                        .defaultSuccessUrl("/", true)
                        .defaultSuccessUrl("http://localhost:3000/", true)
                        .failureUrl("/login-error")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/").permitAll()
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling((ex) -> ex
                        .accessDeniedPage("/negado")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
