package pl.abramczyk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pl.abramczyk.service.BookService;

import java.io.IOException;

@WebServlet("/add")
public class BookAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getUserPrincipal() != null){        //sprawdzenie czy uzytkownik jest zalogowany
            request.getRequestDispatcher("WEB-INF/new.jsp").forward(request,response);
        } else {
            response.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        String title = request.getParameter("inputTitle");
        String isbn = request.getParameter("inputIsbn");
        if(request.getUserPrincipal() != null){          // sprawdzenie czy uzytkownik jest zalogowany
            BookService bookService = new BookService();
            bookService.addBook(firstName,lastName,title,isbn);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendError(403);
        }
    }
}