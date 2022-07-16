package com.listamercado.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_listaCompra")
public class ListaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "valorTotal")
    private double valorTotal;

    @Column(name = "valorTotalCarrinho")
    private double valorTotalCarrinho;

    @Column(name = "quantidadeDeItens")
    private int quantidadeDeItens;

    @Column(name = "quantidadeCarrinho")
    private int quantidadeCarrinho;

    @UpdateTimestamp
    @Column(name = "dataCricao")
    private Date dataCriacao;

    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "listaCompra", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "listaCompra")
    private List<ItemCompra> itemCompra;

    public ListaCompra(Long id, String nome, double valorTotal, double valorTotalCarrinho, int quantidadeDeItens, int quantidadeCarrinho,Date dataCriacao, boolean status,
            List<ItemCompra> itemCompra) {
        this.id = id;
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.valorTotalCarrinho = valorTotalCarrinho;
        this.quantidadeDeItens = quantidadeDeItens;
        this.quantidadeCarrinho = quantidadeCarrinho;
        this.dataCriacao = dataCriacao;
        this.itemCompra = itemCompra;
        this.status = status;
        this.itemCompra = itemCompra;
        
    }

    public ListaCompra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQuantidadeDeItens() {
        return quantidadeDeItens;
    }

    public void setQuantidadeDeItens(int quantidadeDeItens) {
        this.quantidadeDeItens = quantidadeDeItens;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<ItemCompra> getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(List<ItemCompra> itemCompra) {
        this.itemCompra = itemCompra;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getValorTotalCarrinho() {
        return valorTotalCarrinho;
    }

    public void setValorTotalCarrinho(double valorTotalCarrinho) {
        this.valorTotalCarrinho = valorTotalCarrinho;
    }

    public int getQuantidadeCarrinho() {
        return quantidadeCarrinho;
    }

    public void setQuantidadeCarrinho(int quantidadeCarrinho) {
        this.quantidadeCarrinho = quantidadeCarrinho;
    } 
    

}
