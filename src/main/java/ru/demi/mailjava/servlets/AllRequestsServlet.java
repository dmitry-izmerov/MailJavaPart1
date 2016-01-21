package ru.demi.mailjava.servlets;

import ru.demi.mailjava.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by demi on 21.01.16.
 */
public class AllRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVars = createPageVarsMap(req);
        pageVars.put("message", "");
        resp.getWriter().println(PageGenerator.instance().getPage("page.html", pageVars));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> pageVars = createPageVarsMap(req);
        String message = req.getParameter("message");
        resp.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        pageVars.put("message", message != null? message : "");

        resp.getWriter().println(PageGenerator.instance().getPage("page.html", pageVars));
    }

    private static Map<String, Object> createPageVarsMap(HttpServletRequest req) {
        Map<String, Object> pageVars = new HashMap<>();
        pageVars.put("method", req.getMethod());
        pageVars.put("URL", req.getRequestURL().toString());
        pageVars.put("pathInfo", req.getPathInfo());
        pageVars.put("sessionId", req.getSession().getId());
        pageVars.put("params", req.getParameterMap().toString());
        return pageVars;
    }
}
