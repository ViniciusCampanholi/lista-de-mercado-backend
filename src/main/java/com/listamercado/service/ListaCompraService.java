package com.listamercado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.listamercado.model.ItemCompra;
import com.listamercado.model.ListaDeCompra;
import com.listamercado.model.Produto;
import com.listamercado.repository.ItemCompraRepository;
import com.listamercado.repository.ListaDeCompraRepository;
import com.listamercado.repository.ProdutoRepository;

@Service
public class ListaCompraService {

    @Autowired
    private ListaDeCompraRepository listaCompraRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<ListaDeCompra> adicionarNaLista(Long idProduto, Long idLista, double quantidade) {
        return null;

    //     if (listaCompraRepository.findById(idLista).isPresent()) {
    //         Optional<ListaDeCompra> listaCompra = listaCompraRepository.findById(idLista);

    //         // if (itemCompraRepository.findByIdProduto(idLista, idProduto).getId_produto() != idProduto) {

    //             Optional<Produto> produto = produtoRepository.findById(idProduto);
    //             ItemCompra item = new ItemCompra();
    //             item.setListaCompra(listaCompra.get());
    //             item.setId_produto(produto.get().getId());
    //             item.setNomeProduto(produto.get().getNome());
    //             item.setMarca(produto.get().getMarca().getNome());
    //             item.setQuantidade(quantidade);
    //             item.setValorUnitario(produto.get().getValorProduto());
    //             item.setValorTotal(produto.get().getValorProduto() * quantidade);
    //             itemCompraRepository.save(item);

    //         } else {
    //             ItemCompra item = itemCompraRepository.findByIdProduto(idLista, idProduto);


    //             item.setQuantidade(item.getQuantidade() + quantidade);
    //             item.setValorTotal(item.getValorUnitario() * item.getQuantidade());
    //             itemCompraRepository.save(item);
    //         }
    //         return listaCompraRepository.findById(idLista)
    //         .map(resp -> ResponseEntity.ok(resp))
    //         .orElse(ResponseEntity.notFound().build());
    //     } else {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     }

    }

    public ResponseEntity<ListaDeCompra> removerProduto(Long idLista, Long idItem) {
        // Busca a lista pelo id
        Optional<ListaDeCompra> listaCompraBusca = listaCompraRepository.findById(idLista);
        ListaDeCompra listaCompra = listaCompraBusca.get();
        if (listaCompraRepository.findById(listaCompra.getId()).isPresent()) {
            // Busca o item pelo id
            Optional<ItemCompra> itemCompraBusca = itemCompraRepository.findById(idItem);
            ItemCompra item = itemCompraBusca.get();
            if (itemCompraRepository.findById(item.getId()).isPresent()) {
                // Efetua a subtração do valor total da lista pelo valor total do item
                listaCompra.setValorTotal(listaCompra.getValorTotal() - item.getValorTotal());
                // Deleta o item da lista
                itemCompraRepository.deleteById(idItem);
            }
        }
        // Atualiza a lista no DB para atualizar o valor total
        return ResponseEntity.ok(listaCompraRepository.save(listaCompra));
    }

}