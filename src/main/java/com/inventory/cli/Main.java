package com.inventory.cli;

import com.inventory.adapters.controller.ProductController;
import com.inventory.adapters.persistance.ProductRepositoryImpl;
import com.inventory.domain.ProductRepository;
import com.inventory.usecases.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal de la aplicación CLI (Command Line Interface).
 * Esta es la capa más externa de la Arquitectura Limpia, "Frameworks & Drivers".
 * Se encarga de la interacción con el usuario (entrada/salida de consola) y
 * la configuración de las dependencias de la aplicación (composición).
 */
public class Main {
    public static void main(String[] args) {
        // --- Configuración de Dependencias (Composición) ---
        // Aquí se instancian las implementaciones concretas y se inyectan en sus dependientes.
        // Esto demuestra el Principio de Inversión de Dependencias (DIP) y facilita las pruebas.

        ProductRepository productRepository = new ProductRepositoryImpl();

        AddProductUseCase addProductUseCase = new AddProductUseCase(productRepository);
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(productRepository);
        DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(productRepository);
        ListAllProductsUseCase listAllProductsUseCase = new ListAllProductsUseCase(productRepository);

        ProductController productController = new ProductController(
                addProductUseCase,
                updateProductUseCase,
                deleteProductUseCase,
                listAllProductsUseCase
        );

        Scanner scanner = new Scanner(System.in);
        int choice;

        // --- Bucle principal de la interfaz de usuario ---
        // Maneja la interacción con el usuario y delega las operaciones al controlador.
        do {
            printMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea restante

                switch (choice) {
                    case 1:
                        addProduct(scanner, productController);
                        break;
                    case 2:
                        updateProduct(scanner, productController);
                        break;
                    case 3:
                        deleteProduct(scanner, productController);
                        break;
                    case 4:
                        productController.listAllProducts();
                        break;
                    case 0:
                        System.out.println("👋 Saliendo de la aplicación. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("⚠️ Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.err.println("❌ Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar la entrada inválida
                choice = -1; // Para que el bucle continúe
            } catch (Exception e) {
                System.err.println("❌ Ocurrió un error inesperado: " + e.getMessage());
                choice = -1;
            }
            System.out.println(); // Salto de línea para mejor legibilidad
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--- Sistema de Gestión de Inventario ---");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Actualizar Producto");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Listar Todos los Productos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void addProduct(Scanner scanner, ProductController controller) {
        System.out.print("Ingrese ID del producto: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese Nombre del producto: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese Precio del producto: ");
        double price = readDouble(scanner, "Precio");
        System.out.print("Ingrese Stock del producto: ");
        int stock = readInt(scanner, "Stock");

        if (price == -1 || stock == -1) { // Error de lectura
            return;
        }

        controller.addProduct(id, name, price, stock);
    }

    private static void updateProduct(Scanner scanner, ProductController controller) {
        System.out.print("Ingrese ID del producto a actualizar: ");
        String id = scanner.nextLine();

        System.out.println("Ingrese nuevos datos (deje en blanco o 0 si no desea actualizar):");

        System.out.print("Nuevo Nombre del producto: ");
        String name = scanner.nextLine();
        name = name.trim().isEmpty() ? null : name.trim(); // Si está en blanco, pasa null.

        System.out.print("Nuevo Precio del producto: ");
        Double price = readNullableDouble(scanner, "Precio");

        System.out.print("Nuevo Stock del producto: ");
        Integer stock = readNullableInt(scanner, "Stock");

        controller.updateProduct(id, name, price, stock);
    }

    private static void deleteProduct(Scanner scanner, ProductController controller) {
        System.out.print("Ingrese ID del producto a eliminar: ");
        String id = scanner.nextLine();
        controller.deleteProduct(id);
    }

    // Métodos auxiliares para lectura segura de entrada
    private static double readDouble(Scanner scanner, String fieldName) {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea
                return value;
            } catch (InputMismatchException e) {
                System.err.println("❌ Entrada inválida para " + fieldName + ". Por favor, ingrese un número decimal.");
                scanner.nextLine(); // Limpiar la entrada inválida
                System.out.print("Ingrese " + fieldName + " del producto: ");
            }
        }
    }

    private static int readInt(Scanner scanner, String fieldName) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                return value;
            } catch (InputMismatchException e) {
                System.err.println("❌ Entrada inválida para " + fieldName + ". Por favor, ingrese un número entero.");
                scanner.nextLine(); // Limpiar la entrada inválida
                System.out.print("Ingrese " + fieldName + " del producto: ");
            }
        }
    }

    private static Double readNullableDouble(Scanner scanner, String fieldName) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.err.println("❌ Entrada inválida para " + fieldName + ". Por favor, ingrese un número decimal o deje en blanco.");
                System.out.print("Nuevo " + fieldName + " del producto: ");
            }
        }
    }

    private static Integer readNullableInt(Scanner scanner, String fieldName) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("❌ Entrada inválida para " + fieldName + ". Por favor, ingrese un número entero o deje en blanco.");
                System.out.print("Nuevo " + fieldName + " del producto: ");
            }
        }
    }
}

