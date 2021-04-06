package pl.abramczyk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pl.abramczyk.model.OrderType;
import pl.abramczyk.model.User;
import pl.abramczyk.service.OrderService;

import java.io.IOException;


@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null) {
            long userId = loggedUser.getId();
            long bookId = Long.parseLong(request.getParameter("book_id"));
            updateOrder(userId, bookId);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }

    private void updateOrder(long userId, long bookId) {
        OrderService orderService = new OrderService();
        OrderType orderType = OrderType.WYPOZYCZONA;
        orderService.addOrder(userId, bookId, orderType);
    }
}