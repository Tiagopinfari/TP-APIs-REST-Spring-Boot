package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear o actualizar un producto (sin ID).")
public class ProductoDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre del producto", example = "Televisor Smart 50 pulgadas")
    private String nombre;


    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    @Schema(description = "Descripción detallada", maxLength = 500)
    private String descripcion;


    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio mínimo es 0.01")
    @Schema(description = "Precio del producto", example = "599.99")
    private Double precio;


    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock mínimo es 0")
    @Schema(description = "Cantidad de unidades en stock", example = "25", minimum = "0")
    private Integer stock;


    @NotNull(message = "La categoría es obligatoria")
    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;
}
