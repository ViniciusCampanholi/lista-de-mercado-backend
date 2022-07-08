package com.listamercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listamercado.model.ListaCompra;
import com.listamercado.repository.ListaCompraRepository;
import com.listamercado.service.ListaCompraService;

@RestController
@RequestMapping(value = "/listacompra")
@CrossOrigin("*")
public class ListaCompraController {

    @Autowired
    private ListaCompraRepository listaCompraRepository;

    @Autowired
    private ListaCompraService listaCompraService;

    @GetMapping
    public List<ListaCompra> getAll(){
        return listaCompraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaCompra> getById(@PathVariable Long id){
        return listaCompraRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ListaCompra> post(@RequestBody ListaCompra lista){
        return ResponseEntity.status(HttpStatus.CREATED).body(listaCompraRepository.save(lista));
    }

  @PostMapping("/adicionar/produto/{idProduto}")
    public ResponseEntity<ListaCompra> addProduto(@PathVariable Long idProduto, @RequestBody ListaCompra lista){
        return listaCompraService.adicionarNaLista(idProduto, lista);
    }

    // @PutMapping("/atualizar")
    
    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable Long id){
        listaCompraRepository.deleteById(id);
    }
}