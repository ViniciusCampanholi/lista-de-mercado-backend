package com.listamercado.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_itemCompra")
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private double quantidade;

    @Column(name = "valorTotal", nullable = false)
    private double valorTotal;

    @ManyToOne
    @JsonIgnoreProperties(value = "itemCompra")
    private ListaDeCompra listaDeCompra;

    @OneToOne 
    @JsonIgnoreProperties(value = "itemcompra")
    private Produto produto;

    public ItemCompra() {
    }

    public ItemCompra(Long id, double quantidade, double valorTotal, ListaDeCompra listaDeCompra, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.listaDeCompra = listaDeCompra;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ListaDeCompra getListaDeCompra() {
        return listaDeCompra;
    }

    public void setListaDeCompra(ListaDeCompra listaDeCompra) {
        this.listaDeCompra = listaDeCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    
}