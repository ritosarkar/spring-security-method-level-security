package com.springSecurity.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidationBeforeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        //First extract the value from header without Basic
        if(null!= header){
            if(StringUtils.startsWithIgnoreCase(header,"Basic ")){
                byte[] encodedHeaderRemain = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decode;
                try{
                    decode=Base64.getDecoder().decode(encodedHeaderRemain);
                    String token = new String(decode, StandardCharsets.UTF_8);
                    /*
                    After line number 31 we have a token value which is a representation of
                    username:password ==> un:pwd
                    */
                    //Check the index of delimiter colon
                    int index=token.indexOf(":");
                    if(index==-1){
                        throw new BadCredentialsException("Invalid basic authentication token!!");
                    }
                    String email= token.substring(0,index);
                    if(email.toLowerCase().contains("test")){
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                } catch (IllegalArgumentException e) {
                    throw new BadCredentialsException("Failed to decode basic authentication token!!");
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
