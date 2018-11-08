package com.study.onlineshop;

import com.study.onlineshop.dao.*;
import com.study.onlineshop.dao.jdbc.*;
import com.study.onlineshop.dao.jdbc.service.LocaleConnection;
import com.study.onlineshop.dao.jdbc.service.impl.OwnerConnection;
import com.study.onlineshop.security.SecurityService;
import com.study.onlineshop.service.impl.DefaultProductService;
import com.study.onlineshop.service.impl.DefaultUserService;
import com.study.onlineshop.web.filter.UserRoleSecurityFilter;
import com.study.onlineshop.web.servlet.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.io.InputStream;
import java.sql.Connection;
import java.util.EnumSet;
import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {
        // load properties
        Properties properties = new Properties();
        ClassLoader classLoader = Starter.class.getClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("application.properties")) {
            properties.load(stream);
        }
        // config connections
        LocaleConnection defaultConnection = OwnerConnection.getInstance(properties);
        LocaleConnection userConnection = OwnerConnection.getInstance(properties);
        // configure daos
        ProductDao productDb = new ProductDb();
        ((ProductDb) productDb).setConnection(defaultConnection.getConnection());
        UserDao userDb = new UserDb();
        ((UserDb) userDb).setConnection(userConnection.getConnection());
        // configure services
        DefaultProductService productService = new DefaultProductService(productDb);
        DefaultUserService userService = new DefaultUserService(userDb);
        SecurityService securityService = new SecurityService(userService);
        // servlets
        ProductsServlet productsServlet = new ProductsServlet();
        productsServlet.setProductService(productService);
        //ProductsApiServlet productsApiServlet = new ProductsApiServlet(defaultProductService);
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.setSecurityService(securityService);
        EditProductServlet editServlet = new EditProductServlet();
        editServlet.setProductService(productService);


        // config web server
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        // redirect from login
        //servletContextHandler.addServlet(new ServletHolder(productsServlet), "/");
        //servletContextHandler.addServlet(new ServletHolder(productsApiServlet), "/api/v1/products");

        //servletContextHandler.addServlet(new ServletHolder(new LoginServlet(activeTokens)), "/login");
        servletContextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        //
        servletContextHandler.addServlet(new ServletHolder(editServlet), "/product-edit");

        servletContextHandler.addFilter(new FilterHolder(new UserRoleSecurityFilter(securityService)), "/products",
                EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(servletContextHandler);
        server.start();
    }
}