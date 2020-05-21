package sap.gb.springmvc.persist;

import sap.gb.springmvc.model.Product;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProductRepo {
    private Long genId = 0l;
    private ConcurrentHashMap<String, Product> dataBase;

    public ProductRepo(ConcurrentHashMap<String, Product> dataBase) {
        this.dataBase = dataBase;
    }

    public void saveProduct(Product product) {
        product.setId(generateId());
        dataBase.put(product.getName(), product);

    }

    public Product getProductByTitle(String title) {
        return dataBase.get(title);
    }


    public List<Product> getAllProducts() {
        return dataBase
                        .entrySet()
                        .stream()
                        .map(entry -> entry.getValue())
                        .collect(Collectors.toList());
    }

    private Long generateId() {
        return ++genId;
    }
}
