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

        // Verifica se a lista listaItems está vazia, se estiver, adicionar um novo produto
        if (listaItems.isEmpty()) {
            produtoNovo(produto, listaCompra, quantidade);
        } else {
            // controla item repetido dentro da lista
            boolean controleItemRepetido = false;
            // Instancia a classe
            ItemCompra itemCompraRepetido = new ItemCompra();
            // verifica item a item dentro da lista de itens
            for (ItemCompra item : listaItems) {
                //  verifica se algum idProduto dento dos itens é igual ao idProduto vindo da requisição
                // Se sim, então a variavel de controle recebe true, e o item repetido é armazenado no itemCompraRepetido
                if (idProduto == item.getIdProduto()) {
                    controleItemRepetido = true;
                    itemCompraRepetido = item;
                }
            }
            // Se a variavel controleItemRepetido for true, ou seja se houver item repetido
            // o método adicionarProdutoExistente é chamado
            if (controleItemRepetido == true) {
                produtoExistente(itemCompraRepetido, quantidade);
            } 
            // Se não houver item repetido, levando em conta o valor de controleItemRepetido, o método adicionarProdutoNovo é chamado
            else {
                produtoNovo(produto, listaCompra, quantidade);
            }
            // Ao final, a variável controleItemRepetido tem seu valor false atribuído
            controleItemRepetido = false;
        }
        // chamada do método para atualizar a lista
        atualizarQuantidadeEvalorTotal(idLista);
        return ResponseEntity.status(HttpStatus.OK).body(listaDeCompraRepository.save(listaCompra));
    }

    // Método para adicionar um produto novo a lista de compras do usuário
    public void produtoNovo(Produto produto, ListaDeCompra listaCompra, double quantidade) {
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

    // Método para adicionar um produto que já existe na lista de compras do usuário
    public void produtoExistente(ItemCompra itemRepetido, double quantidade) {
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