package db2.onlineshop.controller;

import db2.onlineshop.service.ProductService;
import db2.onlineshop.web.templater.PageGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    private final PageGenerator PAGE_GENERATOR = PageGenerator.instance();

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    @ResponseBody
    public String getAll() throws IOException {
        //return PAGE_GENERATOR.getPage(, );
        return "XXX";
    }
}
