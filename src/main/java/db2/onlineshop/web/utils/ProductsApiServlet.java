package db2.onlineshop.web.utils;

import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductsApiServlet extends HttpServlet {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> products = productService.getItems();
        // products -> json
        String json = OBJECT_MAPPER.writeValueAsString(products);
        resp.getWriter().write(json);
    }

    public ProductsApiServlet(ProductService productService) {
        this.productService = productService;
    }
}
