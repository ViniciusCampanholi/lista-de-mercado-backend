package com.listamercado.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itemListaCompra")
public class ItemListaCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeProduto")
    private String nomeProduto;

    @Column(name= "marca")
    private String marca;

    @Column(name = "quantidade")
    private int quantidade;
    
    @Column(name = "valorUnitario")
    private double valorUnitario;
    
    @Column(name = "valorTotal")
    private double valorTotal;
    
    @ManyToOne
    @JsonIgnoreProperties("itemListaCompra")
    private ListaCompra listaCompra;

    public ItemListaCompra() {
    }

    public ItemListaCompra(Long id, String nomeProduto, String marca, int quantidade, double valorUnitario,
            double valorTotal, ListaCompra listaCompra) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.marca = marca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.listaCompra = listaCompra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ListaCompra getListaCompra() {
        return listaCompra;
    }

    public void setListaCompra(ListaCompra listaCompra) {
        this.listaCompra = listaCompra;
    }

}