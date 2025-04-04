package com.example.Shopping_Platform.service;
import com.example.Shopping_Platform.model.CartItem;
import com.example.Shopping_Platform.model.Product;
import com.example.Shopping_Platform.model.Seller;
import com.example.Shopping_Platform.model.User;
import com.example.Shopping_Platform.repository.CartItemRepository;
import com.example.Shopping_Platform.repository.SellerRepository;
import com.example.Shopping_Platform.repository.UserRepository;
import com.example.Shopping_Platform.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.StaleObjectStateException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import java.util.ConcurrentModificationException;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SellerRepository sellerRepository;

    public List<CartItem> getCartItems(UserDetails userDetails) {
         User user = userService.findByUsername(userDetails.getUsername());
         return cartItemRepository.findByUser(user);
    }

    public double getTotalAmount(UserDetails userDetails) {
        List<CartItem> cartItems = getCartItems(userDetails);
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void addToCart(Long productId, int quantity, User user) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        CartItem cartItem = new CartItem(product, quantity, user);
        cartItemRepository.save(cartItem);
    }

    public void updateProductsWithNullSeller() {
        List<Product> products = productRepository.findBySellerIsNull();
        Seller defaultSeller = sellerRepository.findByUsername("default_seller");

        for (Product product : products) {
            if (product.getVersion() == null) {
                product.setVersion(0L); // Initialize version to 0 if null
            }

            try {
                product.setSeller(defaultSeller);
                productRepository.save(product);
            } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
                System.out.println("Optimistic locking failure for product ID: " + product.getId());
                throw new ConcurrentModificationException("The product was modified by another user.");
            }
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

}
