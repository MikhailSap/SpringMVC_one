package sap.gb.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sap.gb.springmvc.model.Product;
import sap.gb.springmvc.persist.ProductRepo;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepo productRepo;

    @Autowired
    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product";

    }

    @PostMapping("product")
    public String addProduct(Product product) {
        productRepo.saveProduct(product);
        return "product";
    }

    @GetMapping("products")
    public String getProducts(Model model) {
        List<Product> products = productRepo.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }
}
