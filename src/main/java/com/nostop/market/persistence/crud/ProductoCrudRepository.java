package com.nostop.market.persistence.crud;

import com.nostop.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// CrudRepository interfaces inherit CrudRepository<T, ID>
//      <T> - Domain (table) type to be managed.
//      <ID> - Type of the id of the entity to be managed by the repo.
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // Query Methods: Methods that are not in the CrudRepository

    // SELECT * FROM productos WHERE id_categoria = ? ORDER BY nombre ASC
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    // SELECT * FROM productos WHERE cantidadStock < ? and estado = ?
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean Estado);

}

