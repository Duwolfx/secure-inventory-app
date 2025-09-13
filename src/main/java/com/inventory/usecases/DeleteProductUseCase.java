package com.inventory.usecases;

import com.inventory.domain.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Caso de uso para eliminar un producto existente.
 * Adhiere al SRP.
 */
public class DeleteProductUseCase {
    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Ejecuta la lógica para eliminar un producto por su ID.
     * @param id El ID del producto a eliminar.
     * @throws NoSuchElementException Si el producto no se encuentra.
     */
    public void execute(String id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Producto con ID " + id + " no encontrado para eliminar.");
        }
        productRepository.delete(id);
    }
}