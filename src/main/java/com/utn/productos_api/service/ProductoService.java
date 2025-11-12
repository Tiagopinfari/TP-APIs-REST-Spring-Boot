package com.utn.productos_api.service;

import com.utn.productos_api.exception.ProductoNotFoundException;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Anota con @Service [cite: 174]
@RequiredArgsConstructor // Inyecta ProductoRepository por constructor (Lombok) [cite: 175]
public class ProductoService {

    private final ProductoRepository productoRepository;

    // crearProducto (guarda un nuevo producto) [cite: 177]
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // obtenerTodos (retorna lista de todos los productos) [cite: 178]
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // obtenerPorId (retorna Optional<Producto>) [cite: 180]
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // obtenerPorCategoria [cite: 181]
    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    // actualizarProducto (reemplaza el recurso completo) [cite: 182]
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        // 1. Validar existencia y obtener el producto, si no existe lanza 404
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id));

        // 2. Actualizar todos los campos
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setCategoria(productoActualizado.getCategoria());

        // 3. Guardar y retornar
        return productoRepository.save(productoExistente);
    }

    // actualizarStock (actualiza parcialmente un campo) [cite: 183]
    public Producto actualizarStock(Long id, Integer nuevoStock) {
        // 1. Validar existencia
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id));

        // 2. Actualizar solo el stock
        productoExistente.setStock(nuevoStock);

        // 3. Guardar y retornar
        return productoRepository.save(productoExistente);
    }

    // eliminarProducto [cite: 190]
    public void eliminarProducto(Long id) {
        // 1. Validar existencia antes de eliminar
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException("Producto no encontrado con ID: " + id);
        }
        // 2. Eliminar
        productoRepository.deleteById(id);
    }
}
