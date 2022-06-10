package by.store.service;

import by.store.Constants;
import by.store.repository.ProductDAO;
import by.store.form.ProductForm;
import by.store.logs.EventLogger;
import by.store.еxceptions.QuantityWrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    private UserService userService;

    private ProductDAO productDAO;

    private EventLogger eventLogger;

    private List<ProductForm> basket;

    @Autowired
    public BasketServiceImpl(ProductDAO productDAO, EventLogger eventLogger, UserService userService) {
        this.userService = userService;
        this.productDAO = productDAO;
        this.eventLogger = eventLogger;
        this.basket = new ArrayList<>();
    }


    @Override
    public void addBasket(Long code, Integer count) {
        if (count != null && count > 0) {
            ProductForm product = new ProductForm(productDAO.findById(code).get());
            product.setQuantity(count);
            basket.add(product);
            eventLogger.logEvent(userService.usernameNow() + Constants.ADD_BASKET + product.toString());
        } else {
            throw new QuantityWrongInputException();
        }
    }

    @Override
    public List<ProductForm> getBasket() {
        return basket;
    }

    @Override
    public List<ProductForm> buyProducts() {
        basket = basket.stream()
                .filter(product -> product.getQuantity() <= productDAO.findById(product.getCode()).get().getQuantity())
                .peek(product -> productDAO.findById(product.getCode()).get().setQuantityBuy(product.getQuantity()))
                .peek(product -> eventLogger.logEvent(userService.usernameNow() + Constants.BUY + product.toString()))
                .collect(Collectors.toList());
        return basket;
    }

    @Override
    public List<ProductForm> cleanBasket() {
        List<ProductForm> products = new ArrayList<>(basket);
        basket.clear();
        return products;
    }

    @Override
    public void deleteProductBasket(Long code) {
        basket = basket.stream()
                .filter(product -> !product.getCode().equals(code))
                .collect(Collectors.toList());
    }

}
