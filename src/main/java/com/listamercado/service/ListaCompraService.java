package com.listamercado.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.listamercado.model.ItemListaCompra;
import com.listamercado.model.ListaCompra;
import com.listamercado.model.Produto;
import com.listamercado.repository.ItemCompraRepository;
import com.listamercado.repository.ListaCompraRepository;
import com.listamercado.repository.ProdutoRepository;

@Service
public class ListaCompraService {

    @Autowired
    private ListaCompraRepository listaCompraRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    public ResponseEntity<ListaCompra> adicionarNaLista(Long idProduto, Long idLista){
        
        System.out.println("Print ln aqui: "+ idProduto + " " + idLista);
        Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);
        Produto produto = produtoBuscado.get();

        Optional<ListaCompra> listaCompraBuscada = listaCompraRepository.findById(idLista);
        ListaCompra lista = listaCompraBuscada.get();

        ItemListaCompra item = new ItemListaCompra();
        item.setListaCompra(lista);
        item.setNomeProduto(produto.getNome());
        item.setMarca(produto.getMarca().getNome());
        item.setValorUnitario(produto.getValorProduto());
        item.setValorTotal(produto.getValorProduto() * item.getQuantidade());
        System.out.println("Print ln aqui: "+item.getListaCompra().getNomeLista());
        itemCompraRepository.save(item);

        lista.getItem(item);
        System.out.println("Print ln aqui: "+lista.getItem(item));

        return ResponseEntity.status(HttpStatus.CREATED).body(listaCompraRepository.save(lista));
    }
}