package com.listamercado.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.listamercado.model.ListaCompra;
import com.listamercado.model.Produto;
import com.listamercado.repository.ListaCompraRepository;
import com.listamercado.repository.ProdutoRepository;

@Service
public class ListaCompraService {

    @Autowired
    private ListaCompraRepository listaCompraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    public ResponseEntity<ListaCompra> adicionarNaLista(Long idProduto, ListaCompra lista){
        
        Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);
        Produto produto = produtoBuscado.get();
        
        lista.setNomeProduto(produto.getNome());
        lista.setValorUnitario(produto.getValorProduto());
        lista.setValorTotal(lista.getQuantidade() * produto.getValorProduto());

        return ResponseEntity.status(HttpStatus.CREATED).body(listaCompraRepository.save(lista));
    }
}