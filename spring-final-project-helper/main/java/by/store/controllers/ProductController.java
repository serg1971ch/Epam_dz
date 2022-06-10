package by.store.controllers;

import by.store.Constants;
import by.store.form.ProductForm;
import by.store.model.Product;
import by.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }


    @GetMapping("/admin/edit/{code}")
    public String editProductPage(Model model, @PathVariable("code") Long code) {
        Product product = productService.findByCode(code);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/admin/edit/product/{code}")
    public String editProduct(Model model,
                              @PathVariable("code") Long code,
                              @RequestParam String name,
                              @RequestParam Integer price,
                              @RequestParam Integer quantity,
                              @RequestParam MultipartFile file) {
        ProductForm product = new ProductForm(name, price, quantity, file);
        product.setCode(code);
        productService.update(product);
        return "redirect:/products";
    }


    @GetMapping("/admin/delete/{code}")
    public String deleteProduct(Model model, @PathVariable("code") Long code) {
        productService.delete(code);
        return "redirect:/products";
    }


    @GetMapping("/admin/add")
    public String addProductPage() {
        return "addProduct";
    }


    @PostMapping("/admin/add/product")
    public String createProduct(Model model,
                                @RequestParam String name,
                                @RequestParam Integer price,
                                @RequestParam Integer quantity,
                                @RequestParam MultipartFile file) {
        ProductForm product = new ProductForm(name, price, quantity, file);
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/image/{code}")
    public void productImage(HttpServletRequest request, HttpServletResponse response,
                             Model model, @PathVariable("code") Long code) throws IOException {
        Product product = productService.findByCode(code);
        if (product != null && product.getImage() != null) {
            response.setContentType(Constants.IMAGE_JPEG_JPG_PNG_GIF);
            response.getOutputStream().write(product.getImage());
        }
        response.getOutputStream().close();
    }
}
