package pl.abramczyk.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getUserPrincipal() != null) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendError(403);
        }
    }
}