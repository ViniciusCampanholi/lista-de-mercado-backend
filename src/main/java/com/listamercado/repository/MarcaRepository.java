package com.listamercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listamercado.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{

    
}