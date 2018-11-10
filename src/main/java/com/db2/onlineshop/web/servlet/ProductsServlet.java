package com.db2.onlineshop.web.servlet;

import com.db2.onlineshop.entity.Product;
import com.db2.onlineshop.service.ProductService;
import com.db2.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ProductsServlet extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //if (isAuth) -> moved to filter
        PageGenerator pageGenerator = PageGenerator.instance();
        List<Product> products = productService.getAll();

        HashMap<String, Object> data = new HashMap<>();
        data.put("products", products);

        String page = pageGenerator.getPage("products", data);
        resp.getWriter().write(page);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
