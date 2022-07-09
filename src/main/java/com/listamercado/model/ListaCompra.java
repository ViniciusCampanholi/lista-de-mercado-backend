package com.listamercado.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @Column(name = "nome_lista", nullable = false)
    private String nomeLista;

    @Column(name = "valorTotalLista")
    private double valorTotalLista;

    @OneToMany(mappedBy = "listaCompra")
    @JsonIgnoreProperties("listaCompra")
    private List<ItemCompra> item;
    
    public ListaCompra(Long id, String nomeLista, double valorTotalLista) {
        this.id = id;
        this.nomeLista = nomeLista;
        this.valorTotalLista = valorTotalLista;
    }

    public ListaCompra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public double getValorTotalLista() {
        return valorTotalLista;
    }

    public void setValorTotalLista(double valorTotalLista) {
        this.valorTotalLista = valorTotalLista;
    }

    public List<ItemCompra> getItem() {
        return item;
    }

    public void setItem(List<ItemCompra> item) {
        this.item = item;
    }

}
