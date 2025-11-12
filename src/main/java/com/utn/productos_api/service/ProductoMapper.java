package com.utn.productos_api.service;

import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Producto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoDTO dto) {
        Producto entity = new Producto();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setCategoria(dto.getCategoria());
        return entity;
    }

    public ProductoResponseDTO toResponseDto(Producto entity) {
        return new ProductoResponseDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecio(),
                entity.getStock(),
                entity.getCategoria()
        );
    }

    public List<ProductoResponseDTO> toResponseDtoList(List<Producto> entities) {
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
