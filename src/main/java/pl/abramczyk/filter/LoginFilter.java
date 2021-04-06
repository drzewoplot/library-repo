package pl.abramczyk.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import pl.abramczyk.model.User;
import pl.abramczyk.service.UserService;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        if(httpReq.getUserPrincipal() != null && httpReq.getSession().getAttribute("user") == null) {
            saveUserInSession(httpReq);
        }
        chain.doFilter(request, response);
    }

    private void saveUserInSession(HttpServletRequest request) {
        UserService userService = new UserService();
        String username = request.getUserPrincipal().getName();
        User userByUsername = userService.getUserByUsername(username);
        request.getSession(true).setAttribute("user", userByUsername);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
