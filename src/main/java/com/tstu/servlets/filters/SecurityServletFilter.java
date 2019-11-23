package com.tstu.servlets.filters;

import com.tstu.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = {"homeServlet"})
public class SecurityServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            throw new ServletException("Извините вам нельзя)");
        }
    }

    @Override
    public void destroy() {

    }
}
