package sap.gb.springmvc.Service;

import sap.gb.springmvc.model.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    List<Product> filterByMinMax(Integer min, Integer max);
}
