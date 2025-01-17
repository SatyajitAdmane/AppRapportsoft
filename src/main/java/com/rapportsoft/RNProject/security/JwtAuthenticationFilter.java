package com.rapportsoft.RNProject.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rapportsoft.RNProject.Service.UserServiceImpl;
import com.rapportsoft.RNProject.entities.AppUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {
	
	@Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserServiceImpl userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestHeader = request.getHeader("Authorization");
		
		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			
			String token = requestHeader.substring(7);
			try {
                String username = this.jwtHelper.getUsernameFromToken(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                   AppUser  user = (AppUser) this.userService.loadUserByUsername(username);
                    if (this.jwtHelper.validateToken(token, username)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                user, null, user.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
//                        logger.info("Validation fails!");
                    	System.out.println("Validation fails!");
                    }
                }
            } catch (IllegalArgumentException e) {
//                logger.info("Illegal Argument while fetching the username!");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Invalid Header Value!");
        }

        filterChain.doFilter(request, response);
			
		}
		
}
