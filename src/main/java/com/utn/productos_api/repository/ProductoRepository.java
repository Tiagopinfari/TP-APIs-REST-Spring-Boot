package com.utn.productos_api.repository;

import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Extiende JpaRepository<Entidad, Tipo_de_ID> para obtener los métodos CRUD básicos
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Método personalizado para buscar productos por categoría [cite: 170, 171]
    // Spring Data JPA genera automáticamente la consulta SQL a partir del nombre del método.
    List<Producto> findByCategoria(Categoria categoria);
}
