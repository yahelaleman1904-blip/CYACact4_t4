package com.cyac.act4_t4.controller;

import com.cyac.act4_t4.dto.ProductoRequestDTO;
import com.cyac.act4_t4.model.Producto;
import com.cyac.act4_t4.repository.ProductoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // a) GET para listar (con paginación)
    // Spring Boot inyecta automáticamente el objeto Pageable leyendo los parámetros ?page=0&size=10 de la URL
    @GetMapping
    public ResponseEntity<Page<Producto>> listarProductos(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(productoRepository.findAll(pageable)); // 200 OK
    }

    // a) GET para ver un registro individual
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 Not Found
    }

    // b) POST para crear (Usando el DTO con @Valid)
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        
        Producto nuevoProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto); // 201 Created
    }

    // c) PUT para actualizar (Usando el DTO con @Valid)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNombre(dto.getNombre());
            producto.setDescripcion(dto.getDescripcion());
            producto.setPrecio(dto.getPrecio());
            producto.setStock(dto.getStock());
            
            return ResponseEntity.ok(productoRepository.save(producto)); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    // d) DELETE para eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
}