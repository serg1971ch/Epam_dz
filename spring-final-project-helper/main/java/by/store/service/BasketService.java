package by.store.service;

import by.store.form.ProductForm;

import java.util.List;

public interface BasketService {

    void addBasket(Long code, Integer count);

    List<ProductForm> getBasket();

    List<ProductForm> buyProducts();

    List<ProductForm> cleanBasket();

    void deleteProductBasket(Long code);

}
