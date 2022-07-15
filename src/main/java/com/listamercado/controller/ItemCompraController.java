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
import com.listamercado.service.ItemCompraService;



@RestController
@RequestMapping(value = "/itemcompra")
@CrossOrigin(value = "*")
public class ItemCompraController {
    
    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Autowired
    private ItemCompraService itemCompraService;

    @GetMapping
    public ResponseEntity<List<ItemCompra>> getAll() {
        return ResponseEntity.ok(itemCompraRepository.findAll());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ItemCompra> update(@RequestBody ItemCompra itemCompra) {
        return ResponseEntity.ok(itemCompraRepository.save(itemCompra));
    }

    @PutMapping("/carrinho/{id}")
    public ResponseEntity<ItemCompra> setStatus(@PathVariable Long id){
        return itemCompraService.setStatusItem(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        itemCompraRepository.deleteById(id);
    }
    
    
}