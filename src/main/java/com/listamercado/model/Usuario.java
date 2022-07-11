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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "foto", nullable = false)
    private String foto;

    @Column(name = "userName", nullable = false)
    @Size(max = 8)
    private String userName;

    @Column(name = "senha", nullable = false)
    @Size(min = 8)
    private String senha;

    @UpdateTimestamp
    private Date dataCriacao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "usuario")
    List<ListaDeCompra> listaDeCompra;

    public Usuario(Long id, String nome, String foto, @Size(max = 8) String userName, @Size(min = 8) String senha,
            Date dataCriacao, List<ListaDeCompra> listaDeCompra) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.userName = userName;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.listaDeCompra = listaDeCompra;
    }

    public Usuario() {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<ListaDeCompra> getListaDeCompra() {
        return listaDeCompra;
    }

    public void setListaDeCompra(List<ListaDeCompra> listaDeCompra) {
        this.listaDeCompra = listaDeCompra;
    }

    
}