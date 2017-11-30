package by.tr.web.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        request = (HttpServletRequest) servletRequest;
        response = (HttpServletResponse) servletResponse;


    }

    @Override
    public void destroy() {

    }

    private boolean isSignIn(HttpSession session) {

        return false;
    }
}
