package ru.demi.mailjava.servlet;

import ru.demi.mailjava.DBException;
import ru.demi.mailjava.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author demi
 * @date   24.01.16
 */
public class SignInServlet extends HttpServlet {

    private UserService userProfileService;

    public SignInServlet(UserService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        response.setContentType("text/html;charset=utf-8");
        if (login == null || pass == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            if (!userProfileService.isSingIn(login, pass)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Unauthorized");
                return;
            }
        } catch (DBException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.getWriter().println("Authorized: " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
