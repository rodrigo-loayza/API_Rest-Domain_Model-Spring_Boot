package com.nostop.market.domain.repository;

import com.nostop.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    // Notice that we're using the domain Product not Producto
    // Same methods as in Persistence > ProductoRepository
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarceProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}

