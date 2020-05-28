package sap.gb.springmvc.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.gb.springmvc.model.Product;
import sap.gb.springmvc.repo.ProductRepo;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepo.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> filterByMinMax(Integer min, Integer max) {
        List<Product> products;
        if (min == null && max== null) {
            products = getAllProducts();
        } else if (min == null) {
            products = productRepo.findByPriceLessThan(max);
        } else if (max == null) {
            products = productRepo.findByPriceGreaterThan(min);
        } else {
            products = productRepo.findByPriceBetween(min, max);
        }
        return products;
    }
}
