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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_listaDeCompra")
public class ListaDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "valorTotal")
    private double valorTotal;

    @Column(name = "quantidadeDeItens")
    private int quantidadeDeItens;

    @UpdateTimestamp
    @Column(name = "dataCricao")
    private Date dataCriacao;

    @OneToMany(mappedBy = "listaDeCompra", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "listaDeCompra")
    private List<ItemCompra> itemCompra;

    @ManyToOne
    @JsonIgnoreProperties(value = "listaDeCompra")
    private Usuario usuario;

    public ListaDeCompra(Long id, String nome, double valorTotal, int quantidadeDeItens, Date dataCriacao,
            List<ItemCompra> itemCompra, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.quantidadeDeItens = quantidadeDeItens;
        this.dataCriacao = dataCriacao;
        this.itemCompra = itemCompra;
        this.usuario = usuario;
    }

    public ListaDeCompra() {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

}
