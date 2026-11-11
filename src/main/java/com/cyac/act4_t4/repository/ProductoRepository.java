package com.cyac.act4_t4.repository;

import com.cyac.act4_t4.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // JpaRepository ya incluye findAll, findById, save, deleteById por defecto
}