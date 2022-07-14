package com.listamercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listamercado.model.HistoricoPrecoProduto;

@Repository
public interface HistoricoPrecoProdutoRepository extends JpaRepository<HistoricoPrecoProduto, Long>{
    
}