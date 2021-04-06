package pl.abramczyk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pl.abramczyk.service.UserService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        String email = request.getParameter("inputEmail");
        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        UserService userService = new UserService();
        userService.addUser(username,firstName,lastName,email,password);
        response.sendRedirect(request.getContextPath() + "/");
    }
}