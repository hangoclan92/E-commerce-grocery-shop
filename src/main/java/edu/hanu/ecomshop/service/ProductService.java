package edu.hanu.ecomshop.service;

import edu.hanu.ecomshop.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    void save(Product product);
    void edit(long id, Product newProduct);
    void delete(long id);
    Product findById(long id);
    List<Product> findAllByOrderByIdAsc();
    List<Product> findAllByCategoryId(long categoryId);
    List<Product> findAllByName(String name);
    long count();
    Page<Product> findPaginated(int pageNo, int pageSize);
}
