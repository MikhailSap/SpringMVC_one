package sap.gb.springmvc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sap.gb.springmvc.model.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);

    Product getProductById(Long id);

    Page<Product> getAllProducts(Pageable pageable);

    Page<Product> filterByMinMax(Integer min, Integer max, Pageable pageable);

    Page<Product> filterByPartOfName(String partOfName, Pageable pageable);
}
