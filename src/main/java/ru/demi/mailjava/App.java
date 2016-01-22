package ru.demi.mailjava;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.demi.mailjava.servlets.AllRequestsServlet;

/**
 * Created by demi on 21.01.16.
 */
public class App {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(allRequestsServlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();

        System.out.println("Server started");
        server.join();
    }
}
