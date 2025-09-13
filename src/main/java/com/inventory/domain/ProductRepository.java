package com.inventory.domain;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define el contrato para la persistencia de productos.
 * Este es un ejemplo del Principio de Inversión de Dependencias (DIP) de SOLID,
 * ya que la lógica de negocio depende de esta abstracción, no de una implementación concreta.
 * También aplica POO al definir un contrato para la interacción con los datos.
 */
public interface ProductRepository {
    void save(Product product); // Guarda o actualiza un producto.
    Optional<Product> findById(String id); // Busca un producto por su ID.
    List<Product> findAll(); // Obtiene todos los productos.
    void delete(String id); // Elimina un producto por su ID.
}


