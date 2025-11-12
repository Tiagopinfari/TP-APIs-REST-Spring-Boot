package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.exception.ProductoNotFoundException;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.service.ProductoMapper;
import com.utn.productos_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Gestión completa de productos para el sistema de e-commerce")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoMapper mapper;

    // GET /api/productos - Listar todos
    @GetMapping
    @Operation(summary = "Obtener listado de todos los productos",
            description = "Retorna una lista de ProductoResponseDTOs.")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida con éxito.")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(mapper.toResponseDtoList(productos));
    }

    // GET /api/productos/{id} - Obtener por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    @ApiResponse(responseCode = "200", description = "Producto encontrado con éxito.")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    public ResponseEntity<ProductoResponseDTO> obtenerPorld(
            @Parameter(description = "ID del producto a buscar", example = "1")
            @PathVariable Long id) {

        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id));

        return ResponseEntity.ok(mapper.toResponseDto(producto));
    }

    // GET /api/productos/categoria/{categoria} - Filtrar por categoría
    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Obtener productos por Categoría")
    @ApiResponse(responseCode = "200", description = "Productos filtrados con éxito.")
    @ApiResponse(responseCode = "400", description = "Categoría inválida.")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(
            @PathVariable Categoria categoria) {
        List<Producto> productos = productoService.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(mapper.toResponseDtoList(productos));
    }

    // POST /api/productos - Crear producto
    @PostMapping
    @Operation(summary = "Crear un nuevo producto",
            description = "Registra un nuevo producto en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Producto creado con éxito.")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (errores de validación).")
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoDTO productoDTO) {

        Producto nuevoProducto = mapper.toEntity(productoDTO);
        Producto productoGuardado = productoService.crearProducto(nuevoProducto);

        return new ResponseEntity<>(mapper.toResponseDto(productoGuardado), HttpStatus.CREATED);
    }

    // PUT /api/productos/{id} - Actualizar producto completo
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto completo por ID (Reemplazo)",
            description = "Reemplaza completamente un recurso de producto existente.")
    @ApiResponse(responseCode = "200", description = "Producto actualizado con éxito.")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos.")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO) {

        Producto productoActualizado = mapper.toEntity(productoDTO);
        Producto resultado = productoService.actualizarProducto(id, productoActualizado);

        return ResponseEntity.ok(mapper.toResponseDto(resultado));
    }

    // PATCH /api/productos/{id}/stock - Actualizar solo el stock
    @PatchMapping("/{id}/stock")
    @Operation(summary = "Actualizar parcialmente el stock de un producto",
            description = "Modifica únicamente el campo 'stock' del producto por ID.")
    @ApiResponse(responseCode = "200", description = "Stock actualizado con éxito.")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o stock negativo.")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarStockDTO stockDTO) {

        Producto resultado = productoService.actualizarStock(id, stockDTO.getStock());

        return ResponseEntity.ok(mapper.toResponseDto(resultado));
    }

    // DELETE /api/productos/{id} - Eliminar producto
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto por ID")
    @ApiResponse(responseCode = "204", description = "Producto eliminado con éxito.")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }
}