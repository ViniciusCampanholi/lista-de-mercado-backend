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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listamercado.model.ListaDeCompra;
import com.listamercado.repository.ListaDeCompraRepository;
import com.listamercado.service.ListaCompraService;

@RestController
@RequestMapping(value = "/listacompra")
@CrossOrigin("*")
public class ListaCompraController {

    @Autowired
    private ListaDeCompraRepository listaCompraRepository;

    @Autowired
    private ListaCompraService listaCompraService;

    @GetMapping
    public List<ListaDeCompra> getAll() {
        return listaCompraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaDeCompra> getById(@PathVariable Long id) {
        return listaCompraRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ListaDeCompra> create(@RequestBody ListaDeCompra lista) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listaCompraRepository.save(lista));
    }

    @PostMapping("/adicionar/produto/{idProduto}/lista/{idLista}/quantidade/{quantidade}")
    public ResponseEntity<ListaDeCompra> addProduto(@PathVariable Long idProduto, @PathVariable Long idLista,
            @PathVariable double quantidade) {
        return listaCompraService.adicionarNaLista(idProduto, idLista, quantidade);
    }

    @PutMapping("/atualizar/lista/{id}")
    public ResponseEntity<ListaDeCompra> update(@PathVariable ListaDeCompra listaDeCompra){
        return ResponseEntity.ok(listaCompraRepository.save(listaDeCompra));
    }

    @DeleteMapping("/deletar/lista/{id}")
    public void delete(@PathVariable Long id) {
        listaCompraRepository.deleteById(id);
    }

    @DeleteMapping("/remover/item/{idItem}/lista/{idLista}/")
    public ResponseEntity<ListaDeCompra> remover( @PathVariable Long idLista, @PathVariable Long idItem) {
        return listaCompraService.removerProduto(idLista, idItem);
    }
}