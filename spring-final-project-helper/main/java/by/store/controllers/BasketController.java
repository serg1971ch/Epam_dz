package by.store.controllers;

import by.store.Constants;
import by.store.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BasketController {

    BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping(value = "/add/basket/{code}")
    public String addBasket(@PathVariable("code") Long code,
                            @RequestParam Integer count,
                            Model model) {
        basketService.addBasket(code, count);
        return "redirect:/products";
    }

    @GetMapping(value = "/basket")
    public String basket(Model model) {
        model.addAttribute("products", basketService.getBasket());
        return "basket";
    }

    @PostMapping(value = "/buy")
    public String buyProducts(Model model) {
        basketService.buyProducts();
        model.addAttribute(Constants.MESSAGE, Constants.YOU_BOUGHT_THESE_PRODUCTS);
        model.addAttribute("products", basketService.cleanBasket());
        return "basket";
    }

    @PostMapping(value = "/clean")
    public String cleanBasket(Model model) {
        basketService.cleanBasket();
        return "redirect:/basket";
    }

    @PostMapping(value = "/delete/{code}")
    public String deleteProductBasket(Model model, @PathVariable("code") Long code) {
        basketService.deleteProductBasket(code);
        return "redirect:/basket";
    }
}
