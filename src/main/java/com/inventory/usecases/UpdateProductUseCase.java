package com.inventory.usecases;

import com.inventory.domain.Product;
import com.inventory.domain.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Caso de uso para actualizar un producto existente.
 * Adhiere al SRP.
 */
public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Ejecuta la lógica para actualizar un producto.
     * @param id El ID del producto a actualizar.
     * @param name El nuevo nombre del producto (puede ser nulo si no se actualiza).
     * @param price El nuevo precio del producto (puede ser nulo si no se actualiza).
     * @param stock El nuevo stock del producto (puede ser nulo si no se actualiza).
     * @throws NoSuchElementException Si el producto no se encuentra.
     * @throws IllegalArgumentException Si los datos de actualización son inválidos.
     */
    public void execute(String id, String name, Double price, Integer stock) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isEmpty()) {
            throw new NoSuchElementException("Producto con ID " + id + " no encontrado para actualizar.");
        }

        Product productToUpdate = existingProductOptional.get();

        // Actualizar solo si los nuevos valores son válidos y diferentes de nulo.
        if (name != null) {
            productToUpdate.setName(name); // La validación se hace en el setter de Product
        }
        if (price != null) {
            productToUpdate.setPrice(price); // La validación se hace en el setter de Product
        }
        if (stock != null) {
            productToUpdate.setStock(stock); // La validación se hace en el setter de Product
        }

        // Se "guarda" el producto. En una implementación real, esto actualizaría el registro.
        // En nuestra implementación en memoria, ya tenemos la referencia y los cambios son persistidos.
        // Sin embargo, mantener la llamada a save() es buena práctica para consistencia.
        productRepository.save(productToUpdate);
    }
}