package com.listamercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listamercado.model.ListaCompra;

@Repository
public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long>{
    
}