package ru.demi.mailjava;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.demi.mailjava.accounts.AccountService;
import ru.demi.mailjava.servlets.SignInServlet;
import ru.demi.mailjava.servlets.SignUpServlet;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class App {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        accountService.singUp("admin", "pass");
        accountService.singUp("user", "pass");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}