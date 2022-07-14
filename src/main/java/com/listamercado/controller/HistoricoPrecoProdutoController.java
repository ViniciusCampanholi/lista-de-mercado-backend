package com.listamercado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listamercado.repository.HistoricoPrecoProdutoRepository;

@RestController
@RequestMapping(value = "/historicopreco")
@CrossOrigin(value = "*")
public class HistoricoPrecoProdutoController {
    @Autowired
    private HistoricoPrecoProdutoRepository historicoPrecoProdutoRepository;
}