package com.maceinho.proyecto.controller;


import com.maceinho.proyecto.dto.ProductoDto;
import com.maceinho.proyecto.model.Producto;
import com.maceinho.proyecto.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {this.productoService = productoService;}

    @GetMapping
    public List<ProductoDto> getAllPersonas() {
        return productoService.getAllPersonas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProductoDto> getProductoById(@PathVariable String codigo) {
        ProductoDto productoDto = productoService.getProductoById(codigo);
        if (productoDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDto);
    }

    @PostMapping
    public ProductoDto createProducto(@RequestBody ProductoDto productoDto) {
        return productoService.saveProducto(productoDto);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ProductoDto> updatePersona(@PathVariable String codigo, @RequestBody ProductoDto productoDto) {
        ProductoDto actualizada = productoService.updateProducto(codigo, productoDto);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String codigo) {
        ProductoDto productoDto = productoService.getProductoById(codigo);
        if (productoDto == null) {
            return ResponseEntity.notFound().build();
        }
        productoService.deleteProducto(codigo);
        return ResponseEntity.noContent().build();
    }

}
