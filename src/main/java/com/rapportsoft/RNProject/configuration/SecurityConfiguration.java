package com.rapportsoft.RNProject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.rapportsoft.RNProject.security.JwtAuthenticationEntryPoint;
import com.rapportsoft.RNProject.security.JwtAuthenticationFilter;

@Component
public class SecurityConfiguration {
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    
    @SuppressWarnings("deprecation")
   	@Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
           MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

           http.csrf(csrf -> csrf.disable())
           .cors(cors -> cors.configurationSource(corsConfigurationSource()))
           .authorizeRequests(auth -> auth
        		   .requestMatchers(mvcMatcherBuilder.pattern("/auth/**")).permitAll()
        		   .anyRequest().authenticated())
           .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
           .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
           
           http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

           return http.build();
          
    }
    
    
    
    
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //config.addAllowedOriginPattern("http://103.189.88.215:84");
       config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    
    

}
