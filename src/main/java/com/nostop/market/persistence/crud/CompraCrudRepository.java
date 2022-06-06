package com.nostop.market.persistence.crud;

import com.nostop.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    Optional<List<Compra>> findByIdCliente(String idCliente);

}
