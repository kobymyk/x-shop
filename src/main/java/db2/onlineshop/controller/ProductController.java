package db2.onlineshop.controller;

import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;
import db2.onlineshop.web.templater.PageGenerator;

import db2.onlineshop.web.utils.ParamConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    private static final String KEY = "id";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    private final PageGenerator PAGE_GENERATOR = PageGenerator.instance();

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    @ResponseBody
    public String allForGet() {
        log.info("START:ProductController.allForGet");
        List<Product> items = productService.getItems();
        HashMap<String, Object> data = ParamConverter.fromList(items, "products");
        String result = PAGE_GENERATOR.getPage("products", data);
        log.info("END:ProductController.allForGet");

        return result;
    }

    @RequestMapping(path = "/product-edit", method = RequestMethod.GET)
    @ResponseBody
    public String editForGet(@RequestParam(KEY) String id) {
        log.info("START:ProductController.editForGet({})", id);
        Object item = productService.getItem(id);

        HashMap<String, Object> data = ParamConverter.fromObject(item, "product");
        String result = PAGE_GENERATOR.getPage("product-edit", data);
        log.info("END:ProductController.editForGet");

        return result;
    }

    @RequestMapping(path = "/product-edit", method = RequestMethod.POST)
    public void editForPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("START:ProductController.editForPost");
        Map<String, String[]> paramMap = request.getParameterMap();
        HashMap<String, String> data = ParamConverter.fromParamMap(paramMap);
        productService.updateItem(data);
        log.info("END:ProductController.editForPost");

        response.sendRedirect("products");
    }

    @RequestMapping(path = "/product-add", method = RequestMethod.GET)
    @ResponseBody
    public String addForGet() {
        log.info("START:ProductController.addForGet");
        String result = PAGE_GENERATOR.getPage("product-add", null);
        log.info("END:ProductController.addForGet");

        return result;
    }
    @RequestMapping(path = "/product-add", method = RequestMethod.POST)
    //@ResponseBody not needed for redirect
    public String addForPost(HttpServletRequest request) {
        log.info("START:ProductController.addForPost");
        Map<String, String[]> paramMap = request.getParameterMap();
        HashMap<String, String> data = ParamConverter.fromParamMap(paramMap);
        productService.addItem(data);
        log.info("END:ProductController.addForPost");

        return "redirect:/products";
    }
    @RequestMapping(path = "/product-delete", method = RequestMethod.POST)
    public String deleteForPost(@RequestParam(KEY) String id) {
        log.info("START:ProductController.deleteForPost");

        productService.removeItem(id);
        log.info("END:ProductController.deleteForPost");

        return "redirect:/products";
    }
}
