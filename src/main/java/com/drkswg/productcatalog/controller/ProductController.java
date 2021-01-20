package com.drkswg.productcatalog.controller;

import com.drkswg.productcatalog.entity.Product;
import com.drkswg.productcatalog.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService theProductService) {
        productService = theProductService;
    }

    @GetMapping("/list")
    public String listProducts(Model theModel) {
        List<Product> theProducts = productService.findAll();
        theModel.addAttribute("products", theProducts);

        return "products/list-products";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Product theProduct = new Product();
        theModel.addAttribute("product", theProduct);

        return "products/product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product theProduct) {
        productService.save(theProduct);

        return "redirect:/products/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId") int theId,
                                    Model theModel) {
        Product theProduct = productService.findById(theId);
        theModel.addAttribute("product", theProduct);

        return "products/product-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("productId") int theId) {
        productService.deleteById(theId);

        return "redirect:/products/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("theSearchName") String theSearchName, Model theModel) {
        List<Product> theProducts = productService.searchProducts(theSearchName);
        theModel.addAttribute("products", theProducts);

        return "products/list-products";
    }
}
