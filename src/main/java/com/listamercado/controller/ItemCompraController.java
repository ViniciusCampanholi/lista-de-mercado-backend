package com.listamercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listamercado.model.ItemCompra;
import com.listamercado.repository.ItemCompraRepository;



@RestController
@RequestMapping(value = "/itemcompra")
@CrossOrigin(value = "*")
public class ItemCompraController {
    
    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @GetMapping
    public ResponseEntity<List<ItemCompra>> getAll() {
        return ResponseEntity.ok(itemCompraRepository.findAll());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ItemCompra> update(@RequestBody ItemCompra itemCompra) {
        return ResponseEntity.ok(itemCompraRepository.save(itemCompra));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        itemCompraRepository.deleteById(id);
    }
    
    
}