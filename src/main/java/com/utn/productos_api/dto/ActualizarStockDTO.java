package com.utn.productos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar solo el stock de un producto.")
public class ActualizarStockDTO {

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock mínimo es 0")
    @Schema(description = "Nuevo valor de stock", example = "15", minimum = "0")
    private Integer stock;
}
