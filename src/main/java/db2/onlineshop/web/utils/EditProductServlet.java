package db2.onlineshop.web.utils;

import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;
import db2.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

public class EditProductServlet extends HttpServlet {
    private static final String PKEY = "id";
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        Product product = productService.getItem(PKEY, request.getParameter(PKEY));

        HashMap<String, Object> data = new HashMap<>();
        data.put("product", product);

        String page = pageGenerator.getPage("product-edit", data);
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // if validate(request)
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("id", request.getParameter("id"));
        parameters.put("name", request.getParameter("name"));
        //parameters.put("price", request.getParameter("price"));

        productService.updateItem(parameters);
        response.sendRedirect("/products");
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
