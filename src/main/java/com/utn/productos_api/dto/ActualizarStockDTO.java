package com.utn.productos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok
@NoArgsConstructor // Lombok
@AllArgsConstructor // Lombok
@Schema(description = "DTO para actualizar solo el stock de un producto.")
public class ActualizarStockDTO {

    // Validación: no nulo, mínimo 0 [cite: 185]
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock mínimo es 0")
    @Schema(description = "Nuevo valor de stock", example = "15", minimum = "0")
    private Integer stock;
}
