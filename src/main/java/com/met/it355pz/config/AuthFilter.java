package com.met.it355pz.config;

import com.met.it355pz.service.CustomUserDetailsService;
import com.met.it355pz.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = extractJwtFromRequest(request);

        if (StringUtils.hasText(jwtToken) && tokenUtil.validateToken(jwtToken)) {

            String username = tokenUtil.getUsernameFromToken(jwtToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);


            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());



            //Ukoliko je sve u redu, login je omogucen
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
            System.out.println("Greska: Cannon set the Security context" );
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
