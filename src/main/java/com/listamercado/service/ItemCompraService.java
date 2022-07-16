package com.listamercado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.listamercado.model.ItemCompra;
import com.listamercado.repository.ItemCompraRepository;

@Service
public class ItemCompraService {
    @Autowired
    private ItemCompraRepository itemCompraRepository;

    // Método para gerenciar o status do item, altera o status para indicar se o item está no carrinho ou não
    public ResponseEntity<ItemCompra> setStatusItem(Long id){
        ItemCompra itemCompra = itemCompraRepository.findById(id).get();

        if (itemCompra.getStatus() == false){
            itemCompra.setStatus(true);
        } else if(itemCompra.getStatus() == true){
            itemCompra.setStatus(false);
        }
        return ResponseEntity.ok(itemCompraRepository.save(itemCompra));
    }
}