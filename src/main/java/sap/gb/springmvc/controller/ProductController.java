package sap.gb.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sap.gb.springmvc.Service.ProductService;
import sap.gb.springmvc.model.Product;


@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product";

    }


    @PostMapping("product")
    public String addProduct(Product product) {
        productService.save(product);
        return "product";
    }

//    @GetMapping("products")
//    public String getProducts(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
//        return "products";
//    }

    @GetMapping("products")
    public String filter(@RequestParam(value = "minPrice", required = false) Integer min,
                         @RequestParam(value = "maxPrice", required = false) Integer max, Model model) {
        model.addAttribute("products", productService.filterByMinMax(min, max));
        return "products";
    }
}
