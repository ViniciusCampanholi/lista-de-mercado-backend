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
@Table(name = "tb_itemCompra")
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeProduto", nullable = false)
    private String nomeProduto;

    @Column(name = "idProduto", nullable = false)
    private Long idProduto;

    @Column(name = "marca", nullable = false)
    private String marca;
    
    @Column(name = "quantidade", nullable = false)
    private double quantidade;

    @Column(name = "valorUnitario", nullable = false)
    private double valorUnitario;

    @Column(name = "valorTotal", nullable = false)
    private double valorTotal;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JsonIgnoreProperties(value = "itemCompra")
    private ListaCompra listaCompra;

    public ItemCompra() {
    }

    public ItemCompra(Long id, String nomeProduto, String marca, double quantidade, double valorUnitario,
            double valorTotal, ListaCompra listaCompra, Long idProduto, boolean status) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.marca = marca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.listaCompra = listaCompra;
        this.idProduto = idProduto;
        this.status = status;
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

    public ListaCompra getListaCompra() {
        return listaCompra;
    }

    public void setListaCompra(ListaCompra listaCompra) {
        this.listaCompra = listaCompra;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
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

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}