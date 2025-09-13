package com.inventory.adapters.persistance;

import com.inventory.domain.Product;
import com.inventory.domain.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementación en memoria de ProductRepository.
 * Es un adaptador que implementa la interfaz del dominio.
 * Sigue el Principio Abierto/Cerrado (OCP) de SOLID, ya que puede ser extendido
 * (ej. por una implementación de base de datos) sin modificar el código que lo usa.
 * También respeta el Principio de Segregación de Interfaces (ISP) porque solo
 * implementa los métodos que necesita del ProductRepository.
 */
public class ProductRepositoryImpl implements ProductRepository {
    // Usamos ConcurrentHashMap para simular una "base de datos" simple en memoria.
    // Proporciona acceso concurrente seguro, aunque para esta CLI no es estrictamente necesario.
    private final ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();

    @Override
    public void save(Product product) {
        // En una implementación real, esto gestionaría si es un nuevo producto (insert)
        // o uno existente (update). Aquí, simplemente lo añadimos/sobrescribimos.
        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        // Retornamos una copia de los valores para evitar modificaciones externas directas a la colección interna.
        return new ArrayList<>(products.values());
    }

    @Override
    public void delete(String id) {
        products.remove(id);
    }
}