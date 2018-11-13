package db2.onlineshop.controller;

import db2.onlineshop.service.ProductService;
import db2.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    private static final PageGenerator PAGE_GENERATOR = PageGenerator.instance();

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public void getAll(HttpServletResponse response) throws IOException {
        response.getWriter().write("XXX");
    }
}
