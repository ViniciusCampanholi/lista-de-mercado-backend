package com.listamercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listamercado.model.ItemListaCompra;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemListaCompra, Long>{
    
}