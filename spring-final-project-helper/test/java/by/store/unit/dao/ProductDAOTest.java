package by.store.unit.dao;


import by.store.config.AppConfig;
import by.store.model.Product;
import by.store.repository.ProductDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ProductDAOTest {

    @Autowired
    private ProductDAO productDAO;

    private static final long code = 5;

    @Test
    public void testFindByCode() {
        Product product = productDAO.findById(code).get();
        assertEquals(code, product.getCode());
    }

    @Test
    public void testUpdate() {
        Product product = productDAO.findById(code).get();
        product.setQuantityBuy(1);
        productDAO.save(product);
    }

    @Test
    public void testGetAll() {
        List<Product> products = productDAO.findAll();
        assertNotNull(products);
    }

}
