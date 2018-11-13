package db2.onlineshop;

import db2.onlineshop.dao.ProductDao;
import db2.onlineshop.dao.UserDao;
import db2.onlineshop.dao.jdbc.ProductDb;
import db2.onlineshop.dao.jdbc.UserDb;
import db2.onlineshop.service.impl.DefaultProductService;
import db2.onlineshop.service.impl.DefaultUserService;
import db2.onlineshop.web.filter.AdminRoleSecurityFilter;
import db2.onlineshop.web.filter.UserRoleSecurityFilter;
import db2.onlineshop.web.servlet.EditProductServlet;
import db2.onlineshop.web.servlet.LoginServlet;
import db2.onlineshop.web.servlet.ProductsServlet;
import db2.onlineshop.dao.jdbc.service.LocaleConnection;
import db2.onlineshop.dao.jdbc.service.impl.OwnerConnection;
import db2.onlineshop.security.SecurityService;
/*
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
*/
import javax.servlet.DispatcherType;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Locale;
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
        Locale.setDefault(Locale.ENGLISH); // XE limitation
        LocaleConnection defaultConnection = OwnerConnection.getInstance(properties);
        LocaleConnection userConnection = OwnerConnection.getInstance(properties);
        // configure daos
        ProductDao productDb = new ProductDb();
        UserDao userDb = new UserDb();
        productDb.setDataSource(defaultConnection.getDataSource());
        userDb.setDataSource(userConnection.getDataSource());
        // configure services
        DefaultProductService productService = new DefaultProductService(productDb);
        DefaultUserService userService = new DefaultUserService(userDb);
        SecurityService securityService = new SecurityService(userService);
        // servlets
        ProductsServlet productsServlet = new ProductsServlet();
        LoginServlet loginServlet = new LoginServlet();
        EditProductServlet editServlet = new EditProductServlet();
        //ProductsApiServlet productsApiServlet = new ProductsApiServlet(defaultProductService);
        productsServlet.setProductService(productService);
        loginServlet.setSecurityService(securityService);
        editServlet.setProductService(productService);
        /*
        // config web server
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        //servletContextHandler.addServlet(new ServletHolder(productsApiServlet), "/api/v1/products");
        servletContextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        servletContextHandler.addServlet(new ServletHolder(editServlet), "/product-edit");
        // filters
        FilterHolder userRoleFilterHolder = new FilterHolder(new UserRoleSecurityFilter(securityService));
        FilterHolder adminRoleFilterHolder = new FilterHolder(new AdminRoleSecurityFilter(securityService));
        EnumSet requestEnumSet = EnumSet.of(DispatcherType.REQUEST);
        servletContextHandler.addFilter(userRoleFilterHolder, "/products", requestEnumSet);
        servletContextHandler.addFilter(adminRoleFilterHolder, "/product-edit", requestEnumSet);
        // start
        Server server = new Server(8080);
        server.setHandler(servletContextHandler);
        server.start();
        */
    }
}