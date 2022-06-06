package com.nostop.market.persistence;

import com.nostop.market.domain.Product;
import com.nostop.market.domain.repository.ProductRepository;
import com.nostop.market.persistence.crud.ProductoCrudRepository;
import com.nostop.market.persistence.entity.Producto;
import com.nostop.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //@Component can also be used, but it only means that this is a component of Spring
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        // since there's no mapper method for an optional of a list,
        // we map the list of the Optional using toProducts method
        // and it return the Optional with the list mapped
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        Optional<Producto> producto = productoCrudRepository.findById(productId);
        return producto.map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        // since the save method receives a Producto, we make
        // the conversion from Product to Producto and save it
        Producto producto = mapper.toProducto(product);
        // save method returns a Producto, so we convert it to Product
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

}


