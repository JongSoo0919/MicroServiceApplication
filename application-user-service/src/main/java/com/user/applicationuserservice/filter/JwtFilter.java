package com.user.applicationuserservice.filter;

import com.user.applicationuserservice.dto.CustomUserDetails;
import com.user.applicationuserservice.service.UserService;
import com.user.applicationuserservice.service.impl.UserServiceImpl;
import com.user.applicationuserservice.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final UserServiceImpl userService;
    private final JwtUtils jwtUtils;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {"/health_check", "/users", "/user/{id}", "/welcome", "/login"};
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");

        if (token != null) {
            if (jwtUtils.validateToken(token)) {
                String email = jwtUtils.getEmailByToken(token);
                CustomUserDetails customUserDetails = (CustomUserDetails) userService.loadUserByUsername(email);
                if (customUserDetails != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(customUserDetails, null, null);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
