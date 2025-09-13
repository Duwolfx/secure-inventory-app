package com.inventory.adapters.controller;

import com.inventory.domain.Product;
import com.inventory.usecases.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Controlador que actúa como fachada para los casos de uso.
 * Recibe solicitudes de la capa de presentación (CLI) y las delega a los casos de uso.
 * Centraliza el manejo de excepciones de los casos de uso para presentarlas de forma amigable.
 * Adhiere a SRP al tener la responsabilidad de coordinar las operaciones de producto.
 */
public class ProductController {
    private final AddProductUseCase addProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ListAllProductsUseCase listAllProductsUseCase;

    public ProductController(AddProductUseCase addProductUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             DeleteProductUseCase deleteProductUseCase,
                             ListAllProductsUseCase listAllProductsUseCase) {
        // Inyección de dependencias de todos los casos de uso.
        this.addProductUseCase = addProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.listAllProductsUseCase = listAllProductsUseCase;
    }

    public void addProduct(String id, String name, double price, int stock) {
        try {
            addProductUseCase.execute(id, name, price, stock);
            System.out.println("✅ Producto '" + name + "' agregado con éxito.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error al agregar producto: " + e.getMessage());
        }
    }

    public void updateProduct(String id, String name, Double price, Integer stock) {
        try {
            updateProductUseCase.execute(id, name, price, stock);
            System.out.println("✅ Producto con ID '" + id + "' actualizado con éxito.");
        } catch (NoSuchElementException e) {
            System.err.println("❌ Error al actualizar producto: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error de validación al actualizar producto: " + e.getMessage());
        }
    }

    public void deleteProduct(String id) {
        try {
            deleteProductUseCase.execute(id);
            System.out.println("✅ Producto con ID '" + id + "' eliminado con éxito.");
        } catch (NoSuchElementException e) {
            System.err.println("❌ Error al eliminar producto: " + e.getMessage());
        }
    }

    public void listAllProducts() {
        List<Product> products = listAllProductsUseCase.execute();
        if (products.isEmpty()) {
            System.out.println("ℹ️ No hay productos en el inventario.");
        } else {
            System.out.println("\n--- Lista de Productos ---");
            products.forEach(System.out::println);
            System.out.println("--------------------------");
        }
    }
}