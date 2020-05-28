package sap.gb.springmvc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sap.gb.springmvc.model.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByPriceLessThan(Integer max);
    List<Product> findByPriceGreaterThan(Integer min);
    List<Product> findByPriceBetween(Integer min, Integer max);
}
