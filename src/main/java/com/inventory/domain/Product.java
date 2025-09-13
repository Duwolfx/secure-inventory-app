// domain/Product.java
package com.inventory.domain;

import java.util.Objects;

/**
 * Entidad de dominio que representa un producto.
 * Sigue los principios de POO encapsulando sus atributos y comportamiento.
 * Es el "corazón" del Domain-Driven Design para esta aplicación.
 */
public class Product {
    private final String id;
    private String name;
    private double price;
    private int stock;

    public Product(String id, String name, double price, int stock) {
        // Validación básica de datos para seguridad y consistencia del dominio.
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo o vacío.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor que cero.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock del producto no puede ser negativo.");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío.");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor que cero.");
        }
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock del producto no puede ser negativo.");
        }
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + name + ", Precio: " + String.format("%.2f", price) + ", Stock: " + stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}