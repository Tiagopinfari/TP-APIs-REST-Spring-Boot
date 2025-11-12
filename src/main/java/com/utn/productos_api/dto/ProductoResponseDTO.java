package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok
@NoArgsConstructor // Lombok
@AllArgsConstructor // Lombok
@Schema(description = "DTO para la respuesta que incluye el ID generado.")
public class ProductoResponseDTO {

    @Schema(description = "Identificador único del producto")
    private Long id; // Incluye el ID

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Categoria categoria;
}
