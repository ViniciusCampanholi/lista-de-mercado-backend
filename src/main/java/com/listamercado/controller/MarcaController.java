package com.listamercado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listamercado.model.Marca;
import com.listamercado.repository.MarcaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/marcas")
@CrossOrigin("*")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public List<Marca> getAll() {
        List<Marca> listaMarcas = marcaRepository.findAll();
        return listaMarcas;
    }

    @GetMapping(value = "/{id}")
    public Optional<Marca> getById(@PathVariable Long id) {
        Optional<Marca> marcaById = marcaRepository.findById(id);
        return marcaById;
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Marca> create(@RequestBody Marca marca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaRepository.save(marca));
    }

    @PostMapping(value = "/atualizar")
    public ResponseEntity<Marca> update(@RequestBody Marca marca) {
        return ResponseEntity.ok(marcaRepository.save(marca));
    }

    @DeleteMapping(value = "/deletar/{id}")
    public void delete(@PathVariable Long id) {
        marcaRepository.deleteById(id);
    }

}