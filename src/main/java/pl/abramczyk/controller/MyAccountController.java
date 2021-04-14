package pl.abramczyk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pl.abramczyk.model.Book;
import pl.abramczyk.model.User;
import pl.abramczyk.service.BookService;
import pl.abramczyk.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet("/account")
public class MyAccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User loggedUser = (User) request.getSession().getAttribute("user");
        long userId = loggedUser.getId();
        saveBooksInRequest(request, userId);
        // to be done
        // saveOrderInRequest(request, userId);
        request.getRequestDispatcher("WEB-INF/myAccount.jsp").forward(request, response);
    }

    private void saveBooksInRequest(HttpServletRequest request, long userId) {
        BookService bookService = new BookService();
        List<Book> borrowedBooks = bookService.getBorrowedBooks(userId);
        request.setAttribute("books",borrowedBooks);
    }

}