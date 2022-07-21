package com.listamercado.service;

import java.util.List;

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
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    public ResponseEntity<ListaCompra> adicionarNaLista(Long idProduto, Long idLista, double quantidade) {

        Produto produto = produtoRepository.findById(idProduto).get();
        ListaCompra listaCompra = listaCompraRepository.findById(idLista).get();
        List<ItemCompra> listaItems = listaCompra.getItemCompra();

        // Verifica se a lista listaItems está vazia, se estiver, adicionar um novo
        // produto
        if (listaItems.isEmpty()) {
            produtoNovo(produto, listaCompra, quantidade);
        } else {
            // controla item repetido dentro da lista
            boolean controleItemRepetido = false;
            // Instancia a classe
            ItemCompra itemCompraRepetido = new ItemCompra();
            // verifica item a item dentro da lista de itens
            for (ItemCompra item : listaItems) {
                // verifica se algum idProduto dento dos itens é igual ao idProduto vindo da
                // requisição
                // Se sim, então a variavel de controle recebe true, e o item repetido é
                // armazenado no itemCompraRepetido
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
            // Se não houver item repetido, levando em conta o valor de
            // controleItemRepetido, o método adicionarProdutoNovo é chamado
            else {
                produtoNovo(produto, listaCompra, quantidade);
            }
            // Ao final, a variável controleItemRepetido tem seu valor false atribuído
            controleItemRepetido = false;
        }
        // chamada do método para atualizar a lista
        atualizarListaCompra(idLista);
        return ResponseEntity.status(HttpStatus.OK).body(listaCompraRepository.save(listaCompra));
    }

    // Método para adicionar um produto novo a lista de compras do usuário
    public void produtoNovo(Produto produto, ListaCompra listaCompra, double quantidade) {
        ItemCompra item = new ItemCompra();
        item.setIdProduto(produto.getId());
        item.setNomeProduto(produto.getNome());
        item.setMarca(produto.getMarca().getNome());
        item.setQuantidade(quantidade);
        item.setValorUnitario(produto.getPreco());
        item.setValorTotal(produto.getPreco() * quantidade);
        item.setStatus(false);
        item.setListaCompra(listaCompra);
        itemCompraRepository.save(item);
        listaCompraRepository.save(listaCompra);
    }

    // Método para adicionar um produto que já existe na lista de compras do usuário
    public void produtoExistente(ItemCompra itemRepetido, double quantidade) {
        itemRepetido.setQuantidade(itemRepetido.getQuantidade() + quantidade);
        itemRepetido.setValorTotal(itemRepetido.getValorUnitario() * itemRepetido.getQuantidade());
        itemCompraRepository.save(itemRepetido);
    }

    // Método para atualizar valores da lista de compras do usuário
    public void atualizarListaCompra(Long idLista) {
        // Armazena uma lista de compras buscada pelo idLista passado na requisição
        ListaCompra listaCompra = listaCompraRepository.findById(idLista).get();
        // A quantidade de itens dentro da lista é o comprimento do array itensCompra
        // dentro da lista
        listaCompra.setQuantidadeDeItens(listaCompra.getItemCompra().size());
        // valor padrão da variavel valorTotal
        double valorTotal = 0;
        double valorTotalCarrinho = 0;
        int contItensCarrinho = 0;
        // Se a lista estiver vazia, então a quantidade de itens e o valor total serão
        if (listaCompra.getItemCompra().isEmpty()) {
            listaCompra.setValorTotal(0);
            listaCompra.setValorTotalCarrinho(0);
            listaCompra.setQuantidadeDeItens(0);
            // se a lista não estiver vazia, então o for irá somar valor
        } else {
            for (ItemCompra itemCompra : listaCompra.getItemCompra()) {
                if (itemCompra.getStatus() == false) {
                } else if (itemCompra.getStatus() == true) {
                    contItensCarrinho = contItensCarrinho + 1;
                    valorTotalCarrinho = valorTotalCarrinho + itemCompra.getValorTotal();
                }
                listaCompra.setQuantidadeCarrinho(contItensCarrinho);
                valorTotal = valorTotal + itemCompra.getValorTotal();
                listaCompra.setValorTotal(valorTotal);
                listaCompra.setValorTotalCarrinho(valorTotalCarrinho);
            }
        }
        listaCompraRepository.save(listaCompra);
    }

    // Método para deletar o item inteiro da lista atraves do id
    public void deletarItem(Long idLista, Long idItem) {
        itemCompraRepository.deleteById(idItem);
        atualizarListaCompra(idLista);
    }

    // deleta todos os itens da lista mas não exclui a lista
    public void limparLista(Long idLista) {
        ListaCompra listaCompra = listaCompraRepository.findById(idLista).get();
        itemCompraRepository.deleteAll(listaCompra.getItemCompra());
        this.atualizarListaCompra(idLista);
    }
}