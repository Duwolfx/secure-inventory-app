package com.inventory.usecases;

import com.inventory.domain.Product;
import com.inventory.domain.ProductRepository;

import java.util.List;

/**
 * Caso de uso para listar todos los productos disponibles.
 * Adhiere al SRP.
 */
public class ListAllProductsUseCase {
    private final ProductRepository productRepository;

    public ListAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Ejecuta la lógica para obtener todos los productos.
     * @return Una lista de todos los productos.
     */
    public List<Product> execute() {
        return productRepository.findAll();
    }
}