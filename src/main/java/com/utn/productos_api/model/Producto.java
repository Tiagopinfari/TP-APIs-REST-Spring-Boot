package com.utn.productos_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 1. Anota la clase con @Entity [cite: 161]
@Data // Genera Getters, Setters, toString, etc. (Lombok)
@NoArgsConstructor // Constructor sin argumentos (Lombok)
@AllArgsConstructor // Constructor con todos los argumentos (Lombok)
public class Producto {

    @Id // 2. Configura la clave primaria [cite: 162]
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. Estrategia de autogeneración (IDENTITY es común para H2) [cite: 162]
    private Long id; // clave primaria autogenerada [cite: 156]

    private String nombre; // [cite: 156]
    private String descripcion; // [cite: 157]
    private Double precio; // [cite: 158]
    private Integer stock; // [cite: 159]

    @Enumerated(EnumType.STRING) // 4. Usar @Enumerated para persistir el nombre del Enum [cite: 160]
    private Categoria categoria; // [cite: 160]
}
