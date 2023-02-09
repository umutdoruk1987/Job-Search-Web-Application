package com.umutdoruk.jobSearch.security;

import com.umutdoruk.jobSearch.exception.UnauthorizedException;
import com.umutdoruk.jobSearch.security.utils.JwtUtils;
import com.umutdoruk.jobSearch.service.serviceImpls.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


/*import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try{
            String jwtToken=jwtParser(request);
            if(jwtToken!=null && jwtUtils.isJWTTokenValid(jwtToken)) {
                String username=jwtUtils.getUserFromJWTToken(jwtToken);
                UserDetails userDetails= userService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                     UsernamePasswordAuthenticationToken.authenticated(userDetails,null, userDetails.getAuthorities());   /*new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());*/
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }catch (Exception e){
            throw new UnauthorizedException(e.getMessage());

        }
        filterChain.doFilter(request, response);
    }

    private String jwtParser(HttpServletRequest request ){
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }

}
