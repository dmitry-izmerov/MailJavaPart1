package ru.demi.mailjava;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.demi.mailjava.service.UserService;
import ru.demi.mailjava.servlet.SignInServlet;
import ru.demi.mailjava.servlet.SignUpServlet;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class App {
    public static void main(String[] args) throws Exception, DBException {

        DBHelper dbHelper = new DBHelper();
        dbHelper.printConnectInfo();
        UserService userProfileService = new UserService(dbHelper.getSessionFactory());

        userProfileService.singUp("admin", "pass");
        userProfileService.singUp("user", "pass");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(userProfileService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(userProfileService)), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}