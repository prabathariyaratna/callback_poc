package com.poc.callback.rs;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by ahach on 13/03/2017.
 */
public class RsServer {

    public static void main(String args[]) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8090);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                RsService.class.getCanonicalName());

        try {
            jettyServer.start();
            System.out.println("======================= Rs Server Started ==========================");
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }



}
