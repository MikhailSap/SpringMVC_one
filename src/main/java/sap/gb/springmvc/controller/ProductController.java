package sap.gb.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sap.gb.springmvc.Service.ProductService;
import sap.gb.springmvc.model.Product;

import java.util.Optional;


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

//    @GetMapping("products")
//    public String filter(@RequestParam(value = "minPrice", required = false) Integer min,
//                         @RequestParam(value = "maxPrice", required = false) Integer max, Model model) {
//        model.addAttribute("products", productService.filterByMinMax(min, max));
//        return "products";
//    }

    @GetMapping("products")
    public String filter(@RequestParam(value = "minPrice") Optional<Integer> min,
                         @RequestParam(value = "maxPrice") Optional<Integer> max,
                         @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                         @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                         Model model) {
        model.addAttribute("productsPage",
                productService.filterByMinMax(
                        min.orElse(null),
                        max.orElse(null),
                        PageRequest.of(pageNumber.orElse(1) -1, pageSize.orElse(5)))
        );
        model.addAttribute("min", min.orElse(null));
        model.addAttribute("max", max.orElse(null));
        return "products";
    }


    @GetMapping("products/name")
    public String filter(
                         @RequestParam(value = "partOfName") Optional<String> partOfName,
                         @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                         @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                         Model model) {
        model.addAttribute("productsPage",
                productService.filterByPartOfName(partOfName.orElse(null),
                        PageRequest.of(pageNumber.orElse(1) -1, pageSize.orElse(5)))
        );
        model.addAttribute("partOfName", partOfName.orElse(null));
        return "products";
    }
}
