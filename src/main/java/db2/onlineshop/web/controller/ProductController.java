package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String list(Model model) {
        log.info("list:start");
        List<Product> products = productService.get();
        model.addAttribute("products", products);

        return "productList.html";
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int id) {
        log.info("edit:id={}", id);
        Product product = productService.get(id);
        model.addAttribute("product", product);

        return "productEdit.html";
    }

    @RequestMapping(path = "/product/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute Product product) {
        log.info("edit:product={}", product);
        productService.update(product);

        return "redirect:/products";
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.GET)
    public String add(Model model) {
        log.info("add:start");
        model.addAttribute("product", new Product());

        return "productAdd.html";
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Product product) {
        log.info("add:product={}", product);
        productService.add(product);

        return "redirect:/products";
    }
    @RequestMapping(path = "/product/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable int id) {
        log.info("delete:id={}", id);
        productService.delete(id);

        return "redirect:/products";
    }
}
