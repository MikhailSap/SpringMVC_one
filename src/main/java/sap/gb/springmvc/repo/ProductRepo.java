package sap.gb.springmvc.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sap.gb.springmvc.model.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByPriceLessThan(Integer max, Pageable pageable);
    Page<Product> findByPriceGreaterThan(Integer min, Pageable pageable);
    Page<Product> findByPriceBetween(Integer min, Integer max, Pageable pageable);

    Page<Product> findByNameLike(String partOfName, Pageable pageable);
}
