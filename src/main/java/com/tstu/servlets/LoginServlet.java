package com.tstu.servlets;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.User;
import com.tstu.service.UserService;
import com.tstu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User loginUser = userService.login(username, password);
            req.getSession().setAttribute("user", loginUser);
            resp.getWriter().println(req.getSession().getAttribute("user"));
        } catch (MovieLibraryException e) {
            System.out.println(e.getMessage());
            resp.setStatus(400);
            resp.getWriter().println(e.getMessage());
        }
    }
}
