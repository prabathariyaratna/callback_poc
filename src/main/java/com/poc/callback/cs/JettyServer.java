package com.poc.callback.cs;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by ahach on 13/03/2017.
 */
public class JettyServer implements Runnable {

    public void run() {
        try {
            startJettyServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startJettyServer()throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8091);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                CsService.class.getCanonicalName());
        try {
            jettyServer.start();
            System.out.println("======================= Cs Server Started ==========================");
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
