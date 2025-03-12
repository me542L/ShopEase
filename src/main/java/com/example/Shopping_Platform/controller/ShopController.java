
package com.example.Shopping_Platform.controller;

import com.example.Shopping_Platform.model.CartItem;
import com.example.Shopping_Platform.model.Product;
import com.example.Shopping_Platform.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private ProductService productService;

    private List<CartItem> cart = new ArrayList<>();

    @GetMapping("/products")
    public String viewProductsPage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product";
    }
    @GetMapping("/products/search")
    public String searchProducts(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Product> products;

        if (keyword == null || keyword.trim().isEmpty()) {
            products = new ArrayList<>(); // Empty list to prevent errors
        } else {
            products = productService.searchProducts(keyword);
        }

        model.addAttribute("products", products);
        return "search"; // Make sure search.html exists!
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity) {
        productService.addToCart(productId, quantity);
        return "redirect:/cart"; // Redirect to the cart page
    }

    @GetMapping("/buy-now/{id}")
    public String buyNow(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "buy-now";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        List<CartItem> cartItems = productService.getCartItems();
        double totalAmount = productService.getTotalAmount();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "checkout";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        List<CartItem> cartItems = productService.getCartItems();
        double totalAmount = productService.getTotalAmount();

        // Debug statements to check data
        cartItems.forEach(cartItem -> {
            System.out.println("Product ID: " + cartItem.getProduct().getId());
            System.out.println("Product Name: " + cartItem.getProduct().getName());
            System.out.println("Product Price: " + cartItem.getProduct().getPrice());
            System.out.println("Quantity: " + cartItem.getQuantity());
        });
        System.out.println("Total Amount: " + totalAmount);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }
    @PostMapping("/place-order")
    public String placeOrder(@RequestParam("address") String address,
                             @RequestParam("payment") String payment,
                             @RequestParam("mobile") String mobile,
                             Model model) {
        List<CartItem> cartItems = productService.getCartItems();
        double totalAmount = productService.getTotalAmount();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("address", address);
        model.addAttribute("payment", payment);
        model.addAttribute("mobile", mobile);
        return "order-confirmation";
    }
    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam("cartItemId") Long cartItemId) {
        productService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }
    @GetMapping("/checkout-single-product")
    public String checkoutSingleProduct(@RequestParam("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "error/404";
        }
        model.addAttribute("product", product);
        return "checkout-single-product";
    }
    @PostMapping("/place-order-single-product")
    public String placeOrderSingleProduct(@RequestParam("productId") Long productId,
                                          @RequestParam("address") String address,
                                          @RequestParam("payment") String payment,
                                          @RequestParam("mobile") String mobile,
                                          Model model) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return "error/404";
        }
        model.addAttribute("product", product);
        model.addAttribute("address", address);
        model.addAttribute("payment", payment);
        model.addAttribute("mobile", mobile);
        model.addAttribute("totalAmount", product.getPrice());
        return "order-confirmation-single-product";

}
}
