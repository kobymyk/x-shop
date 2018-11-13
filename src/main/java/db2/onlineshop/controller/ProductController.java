package db2.onlineshop.controller;

import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;
import db2.onlineshop.web.templater.PageGenerator;

import db2.onlineshop.web.utils.ParamConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    private final PageGenerator PAGE_GENERATOR = PageGenerator.instance();

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    @ResponseBody
    public String getAll() {
        List<Product> items = productService.getAll();
        HashMap<String, Object> data = ParamConverter.fromList(items, "products");

        return PAGE_GENERATOR.getPage("products", data);
    }
}
