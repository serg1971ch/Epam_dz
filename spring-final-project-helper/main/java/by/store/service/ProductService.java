package by.store.service;

import by.store.form.ProductForm;
import by.store.model.Product;

import java.util.List;

public interface ProductService {

    Product findByCode(Long code);

    void save(ProductForm product);

    List<Product> getAll();

    void delete(Long code);

    void update(ProductForm product);

}
