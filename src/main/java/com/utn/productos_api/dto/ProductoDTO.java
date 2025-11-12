package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*; // Importa las anotaciones de validación
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok
@NoArgsConstructor // Lombok
@AllArgsConstructor // Lombok
@Schema(description = "DTO para crear o actualizar un producto (sin ID).")
public class ProductoDTO {

    // Nombre: no nulo, no vacío, longitud entre 3 y 100 caracteres [cite: 177]
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre del producto", example = "Televisor Smart 50 pulgadas")
    private String nombre;

    // Descripción: longitud máxima 500 caracteres [cite: 178]
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    @Schema(description = "Descripción detallada", maxLength = 500)
    private String descripcion;

    // Precio: no nulo, valor mínimo 0.01 [cite: 179]
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio mínimo es 0.01")
    @Schema(description = "Precio del producto", example = "599.99")
    private Double precio;

    // Stock: no nulo, valor mínimo 0 [cite: 180]
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock mínimo es 0")
    @Schema(description = "Cantidad de unidades en stock", example = "25", minimum = "0")
    private Integer stock;

    // Categoria: no nula [cite: 180]
    @NotNull(message = "La categoría es obligatoria")
    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;
}
