package com.study.onlineshop.web.filter;

import com.study.onlineshop.entity.UserRole;
import com.study.onlineshop.security.SecurityService;
import com.study.onlineshop.security.Session;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

public class UserRoleSecurityFilter implements Filter {
    private SecurityService securityService;

    public UserRoleSecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    // chain of responsibility
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpServletRequest.getCookies();
        boolean isAuth = false;

        String token = securityService.getToken(cookies);
        // not good
        Session session = securityService.getSession(token);
        if (session != null) {
            if (EnumSet.of(UserRole.ADMIN, UserRole.USER).contains(session.getUser().getUserRole())) {
                isAuth = true;
            }
        }

        if (isAuth) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("/login");
        }


    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
