package com.listamercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listamercado.model.ListaDeCompra;

@Repository
public interface ListaDeCompraRepository extends JpaRepository<ListaDeCompra, Long>{
    
}