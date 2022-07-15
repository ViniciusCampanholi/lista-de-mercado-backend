package com.listamercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.listamercado.model.HistoricoPrecoProduto;
import com.listamercado.repository.HistoricoPrecoProdutoRepository;

@Service
public class HistoricoPrecoProdutoService {
    @Autowired
    private HistoricoPrecoProdutoRepository historicoPrecoProdutoRepository;

    public ResponseEntity<List<HistoricoPrecoProduto>> buscarHistoricoPorProduto(Long id){
        return null;
    }
}