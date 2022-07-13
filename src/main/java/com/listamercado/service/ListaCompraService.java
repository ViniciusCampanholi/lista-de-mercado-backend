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
    private ListaDeCompraRepository listaDeCompraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    public ResponseEntity<ListaDeCompra> adicionarNaLista(Long idProduto, Long idLista, double quantidade) {

        Produto produto = produtoRepository.findById(idProduto).get();
        ListaDeCompra listaCompra = listaDeCompraRepository.findById(idLista).get();
        List<ItemCompra> listaItems = listaCompra.getItemCompra();

        if (listaItems.isEmpty()) {
            adicionarProdutoNovo(produto, listaCompra, quantidade);
        } else {
            boolean controleItemRepetido = false;
            ItemCompra itemCompraRepetido = new ItemCompra();
            for (ItemCompra item : listaItems) {
                if (idProduto == item.getIdProduto()) {
                    controleItemRepetido = true;
                    itemCompraRepetido = item;
                }
            }
            if (controleItemRepetido == true) {
                adicionarProdutoExistente(itemCompraRepetido, quantidade);
            } else {
                adicionarProdutoNovo(produto, listaCompra, quantidade);
            }
            controleItemRepetido = false;
        }
        atualizarQuantidadeEvalorTotal(idLista);
        return ResponseEntity.status(HttpStatus.OK).body(listaDeCompraRepository.save(listaCompra));
    }

    public void adicionarProdutoNovo(Produto produto, ListaDeCompra listaCompra, double quantidade) {
        ItemCompra item = new ItemCompra();
        item.setIdProduto(produto.getId());
        item.setNomeProduto(produto.getNome());
        item.setMarca(produto.getMarca().getNome());
        item.setQuantidade(quantidade);
        item.setValorUnitario(produto.getPreco());
        item.setValorTotal(produto.getPreco() * quantidade);
        item.setListaDeCompra(listaCompra);
        itemCompraRepository.save(item);
        listaDeCompraRepository.save(listaCompra);
    }

    public void adicionarProdutoExistente(ItemCompra itemRepetido, double quantidade) {
        itemRepetido.setQuantidade(itemRepetido.getQuantidade() + quantidade);
        itemRepetido.setValorTotal(itemRepetido.getValorUnitario() * itemRepetido.getQuantidade());
        itemCompraRepository.save(itemRepetido);
    }

    public void atualizarQuantidadeEvalorTotal(Long idLista) {
        Optional<ListaDeCompra> listaDeCompra = listaDeCompraRepository.findById(idLista);

        listaDeCompra.get().setQuantidadeDeItens(listaDeCompra.get().getItemCompra().size());

        double valorTotal = 0;
        if (listaDeCompra.get().getItemCompra().isEmpty()) {
            listaDeCompra.get().setValorTotal(0);
            listaDeCompra.get().setQuantidadeDeItens(0);
        } else {
            for (ItemCompra itemCompra : listaDeCompra.get().getItemCompra()) {
                valorTotal = valorTotal + itemCompra.getValorTotal();
                listaDeCompra.get().setValorTotal(valorTotal);
            }
        }

        listaDeCompraRepository.save(listaDeCompra.get());
    }

    public ResponseEntity<ListaDeCompra> removerItem(Long idLista, Long idItem) {
        Optional<ListaDeCompra> listaCompraBusca = listaDeCompraRepository.findById(idLista);
        ListaDeCompra listaCompra = listaCompraBusca.get();
        if (listaDeCompraRepository.findById(listaCompra.getId()).isPresent()) {
            Optional<ItemCompra> itemCompraBusca = itemCompraRepository.findById(idItem);
            ItemCompra item = itemCompraBusca.get();
            if (itemCompraRepository.findById(item.getId()).isPresent()) {
                itemCompraRepository.deleteById(idItem);
            }
        }

        this.atualizarQuantidadeEvalorTotal(idLista);
        return ResponseEntity.ok(listaDeCompraRepository.save(listaCompra));
    }

    public ResponseEntity<ListaDeCompra> limparListaDeCompra(Long idLista) {
        Optional<ListaDeCompra> listaDeCompra = listaDeCompraRepository.findById(idLista);
        List<ItemCompra> listaItemCompra = listaDeCompra.get().getItemCompra();
        if (!listaItemCompra.isEmpty()) {
            itemCompraRepository.deleteAll(listaItemCompra);
            this.atualizarQuantidadeEvalorTotal(idLista);
        }
        return listaDeCompraRepository.findById(idLista).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }
}