package com.inventory.usecases;

import com.inventory.domain.Product;
import com.inventory.domain.ProductRepository;

/**
 * Caso de uso para agregar un nuevo producto.
 * Sigue el Principio de Responsabilidad Única (SRP) de SOLID,
 * ya que su única responsabilidad es manejar la lógica de adición de productos.
 */
public class AddProductUseCase {
    private final ProductRepository productRepository;

    public AddProductUseCase(ProductRepository productRepository) {
        // Inyección de dependencias: el repositorio se pasa como argumento.
        // Esto refuerza DIP y facilita la prueba.
        this.productRepository = productRepository;
    }

    /**
     * Ejecuta la lógica para agregar un producto.
     * Realiza validaciones de negocio antes de la persistencia.
     * @param id El ID único del producto.
     * @param name El nombre del producto.
     * @param price El precio del producto.
     * @param stock La cantidad en stock del producto.
     * @throws IllegalArgumentException Si el producto con el ID ya existe o los datos son inválidos.
     */
    public void execute(String id, String name, double price, int stock) {
        if (productRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Ya existe un producto con ese ID. Por favor, elija otro.");
        }
        try {
            Product newProduct = new Product(id, name, price, stock);
            productRepository.save(newProduct);
        } catch (IllegalArgumentException e) {
            // Mensaje seguro, no revela detalles internos
            throw new IllegalArgumentException("Datos inválidos: " + e.getMessage());
        }
    }

}