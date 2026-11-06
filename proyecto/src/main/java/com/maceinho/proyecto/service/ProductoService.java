package com.maceinho.proyecto.service;

import com.maceinho.proyecto.dto.ProductoDto;
import com.maceinho.proyecto.model.Producto;
import com.maceinho.proyecto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {this.productoRepository = productoRepository;}


    public List<ProductoDto> getAllPersonas () {
        List<Producto> productos = productoRepository.findAll();

        List<ProductoDto> productoDtos = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoDto dto = convertToDto(producto);
            productoDtos.add(dto);
        }
        return productoDtos;
    }

    public ProductoDto getProductoById(String codigo){
        Producto producto = productoRepository.findById(codigo).orElse(null);
        if (producto == null) {
            return null;
        }
        return convertToDto(producto);
    }

    public ProductoDto saveProducto(ProductoDto productoDto) {
        Producto producto = convertToEntity(productoDto);
        Producto savedProducto = productoRepository.save(producto);

        return convertToDto(savedProducto);
    }

    public ProductoDto updateProducto(String codigo, ProductoDto dto) {
        Producto producto = productoRepository.findById(codigo).orElse(null);
        if (producto == null) {
            return null;
        }
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());

        return convertToDto(productoRepository.save(producto));
    }

    public void deleteProducto(String codigo) { productoRepository.deleteById(codigo);}

    private ProductoDto convertToDto(Producto producto) {
        ProductoDto dto = new ProductoDto();
        dto.setCodigo(producto.getCodigo());
        dto.setDescripcion(producto.getDescripcion());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());

        return dto;
    }

    private Producto convertToEntity(ProductoDto dto) {
        Producto producto = new Producto();
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());

        return producto;
    }

}
