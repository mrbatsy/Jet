package com.first.pract.firstPractice.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtsFilter extends OncePerRequestFilter {

	@Autowired
	JwtAuthService authService;
	@Autowired
	UserRegistrationInfoService userRegistrationInfoService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header=request.getHeader("Authorization");
		String token=null;
		String username=null;
		
		if(header!=null && header.startsWith("Bearer ")) {
			token=header.substring(7);
			username=authService.extractUsername(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userRegistrationInfoService.loadUserByUsername(username);
			
			if(authService.validateToken(token,userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken=new 
						UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,
								userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
