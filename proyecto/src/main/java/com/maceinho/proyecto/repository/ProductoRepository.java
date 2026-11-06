package com.maceinho.proyecto.repository;

import com.maceinho.proyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, String> {
}
