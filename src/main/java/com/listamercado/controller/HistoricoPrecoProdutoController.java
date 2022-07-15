package com.listamercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listamercado.model.HistoricoPrecoProduto;
import com.listamercado.repository.HistoricoPrecoProdutoRepository;
import com.listamercado.service.HistoricoPrecoProdutoService;

@RestController
@RequestMapping(value = "/historicopreco")
@CrossOrigin(value = "*")
public class HistoricoPrecoProdutoController {
    @Autowired
    private HistoricoPrecoProdutoRepository historicoPrecoProdutoRepository;

    @Autowired
    private HistoricoPrecoProdutoService historicoPrecoProdutoService;

    @GetMapping(value = "/produto/{id}")
    public ResponseEntity<List<HistoricoPrecoProduto>> getAll(@PathVariable Long id){
        return null;
    }

    @PostMapping(value = "")

    @PutMapping

    @DeleteMapping

}