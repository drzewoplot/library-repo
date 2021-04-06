package pl.abramczyk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pl.abramczyk.model.Book;
import pl.abramczyk.service.BookService;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveBooksInRequest(request);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

    }

    private void saveBooksInRequest(HttpServletRequest request) {
        BookService bookService = new BookService();
        List<Book> allBooks = bookService.getAllBooks((o1, o2) -> {
            if (o1.getOrderType().getIndex() > o2.getOrderType().getIndex()) {
                return 1;
            } else if (o1.getOrderType().getIndex() < o2.getOrderType().getIndex()) {
                return -1;
            }
            return 0;
        });
        request.setAttribute("books", allBooks);
    }
}