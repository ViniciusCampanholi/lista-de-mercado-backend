package com.listamercado.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.listamercado.model.ItemCompra;
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

    ItemCompra item = new ItemCompra();
    
    public ResponseEntity<ListaCompra> adicionarNaLista(Long idProduto, Long idLista, ItemCompra item){

        Optional<Produto> produtoBusca = produtoRepository.findById(idProduto);
        Produto produto = produtoBusca.get();

        Optional<ListaCompra> listaCompraBusca = listaCompraRepository.findById(idLista);
        ListaCompra listaCompra = listaCompraBusca.get();

        item.setListaCompra(listaCompra);
        item.setId_produto(produto.getId());
        item.setNomeProduto(produto.getNome());
        item.setMarca(produto.getMarca().getNome());
        item.setValorUnitario(produto.getValorProduto());
        item.setValorTotal(produto.getValorProduto() * item.getQuantidade());
        itemCompraRepository.save(item);
        
        listaCompra.setValorTotalLista(listaCompra.getValorTotalLista() + item.getValorTotal());

        return ResponseEntity.status(HttpStatus.CREATED).body(listaCompraRepository.save(listaCompra));
    }

    public ResponseEntity<ListaCompra> adicionarNaLista2(Long idProduto, Long idLista, ItemCompra item){

        Optional<Produto> produtoBusca = produtoRepository.findById(idProduto);
        Produto produto = produtoBusca.get();

        Optional<ListaCompra> listaCompraBusca = listaCompraRepository.findById(idLista);
        ListaCompra listaCompra = listaCompraBusca.get();

        item.setListaCompra(listaCompra);
        item.setId_produto(produto.getId());
        item.setNomeProduto(produto.getNome());
        item.setMarca(produto.getMarca().getNome());
        item.setValorUnitario(produto.getValorProduto());
        item.setValorTotal(produto.getValorProduto() * item.getQuantidade());
        itemCompraRepository.save(item);
        
        listaCompra.setValorTotalLista(listaCompra.getValorTotalLista() + item.getValorTotal());

        return ResponseEntity.status(HttpStatus.CREATED).body(listaCompraRepository.save(listaCompra));
    }

    public ResponseEntity<ListaCompra> removerProduto(Long idLista, Long idItem){

        // Busca a lista pelo id
        Optional<ListaCompra> listaCompraBusca = listaCompraRepository.findById(idLista);
        ListaCompra listaCompra = listaCompraBusca.get();

        // Busca o item pelo id
        Optional<ItemCompra> itemCompraBusca = itemCompraRepository.findById(idItem);
        ItemCompra item = itemCompraBusca.get();

        // Efetua a subtração do valor total da lista pelo valor total do item
        listaCompra.setValorTotalLista(listaCompra.getValorTotalLista() - item.getValorTotal());

        // Deleta o item da lista
        itemCompraRepository.deleteById(idItem);

        // Atualiza a lista no DB
        return ResponseEntity.ok(listaCompraRepository.save(listaCompra));
    }
}