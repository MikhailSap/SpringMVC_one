package sap.gb.springmvc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> filterByMinMax(Integer min, Integer max, Pageable pageable) {
        Page<Product> productsPage;
        if (min == null && max== null) {
            productsPage = getAllProducts(pageable);
        } else if (min == null) {
            productsPage = productRepo.findByPriceLessThan(max, pageable);
        } else if (max == null) {
            productsPage = productRepo.findByPriceGreaterThan(min, pageable);
        } else {
            productsPage = productRepo.findByPriceBetween(min, max, pageable);
        }
        return productsPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> filterByPartOfName(String partOfName, Pageable pageable) {
        return productRepo.findByNameLike("%"+partOfName+"%", pageable);
    }

}
