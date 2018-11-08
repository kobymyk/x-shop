package com.study.onlineshop.web.servlet;

import com.study.onlineshop.entity.Product;
import com.study.onlineshop.service.ProductService;
import com.study.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class EditProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        PageGenerator pageGenerator = PageGenerator.instance();
        Product product = productService.getScalar(id);

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("product", product);

        String page = pageGenerator.getPage("product-edit", parameters);
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // if validate(request)
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("id", request.getParameter("id"));
        parameters.put("name", request.getParameter("name"));
        //parameters.put("price", request.getParameter("price"));

        productService.update(parameters);
        response.sendRedirect("/products");
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
