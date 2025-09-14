# Secure Inventory App

## Descripción del Proyecto

Secure Inventory App es una aplicación de gestión de inventario desarrollada en Java que sigue principios de Arquitectura Limpia y SOLID, esta app permite agregar, actualizar, eliminar y listar productos, buscando que sea de manera segura a través de una interfaz de línea de comandos (CLI). Como dato adicional el diseño desacoplado facilita la extensión, pruebas y mantenimiento del sistema.

## Guía de Configuración

### Clonar el repositorio

```sh
git clone https://github.com/tu-usuario/secure-inventory-app.git
cd secure-inventory-app
```

### Configurar dependencias

Este proyecto utiliza **Maven** para la gestión de dependencias. Asegúrate de tener Maven instalado:

- [Descargar Maven](https://maven.apache.org/download.cgi)

### Compilar la aplicación

```sh
mvn clean package
```

### Ejecutar la aplicación

```sh
java -jar target/secure-inventory-app-1.0-SNAPSHOT.jar
```

## Instrucciones para la Demostración

1. Al iniciar la aplicación, verás un menú con opciones para agregar, actualizar, eliminar y listar productos.
2. Selecciona la opción deseada ingresando el número correspondiente.
3. Sigue las instrucciones en pantalla para ingresar los datos del producto.
4. Los mensajes de error te guiarán si ingresas datos inválidos (por ejemplo, ID incorrecto, precio fuera de rango, etc.).
5. Puedes salir de la aplicación seleccionando la opción "0".

## Conceptos Avanzados

### Manejo de Transacciones

En una aplicación conectada a una base de datos real, las operaciones que involucran múltiples pasos (por ejemplo, reducir el stock al vender y registrar la venta) consideraria que deberian ejecutarse dentro de una transacción, y el aplicar seguridad es otro factor importante que en este caso aunque no se pregunte es una addicion necesaria. Siguiendo con lo solicitado esto asegura la atomicidad, ahora, si una parte de la operación falla, ninguna de las acciones se aplica, evitando inconsistencias en el inventario. En la Arquitectura Limpia, el manejo de transacciones se implementaría en la capa de infraestructura (adaptadores de persistencia), mientras que los casos de uso orquestarían las operaciones necesarias.

### Autenticación y Autorización
Param la parte de proteger las operaciones del inventario, lo que mejor serviria es una creacion de roles mediante autenticación para verificar la identidad del usuario y que esos roles cada uno tenga funciones y autorizaciónes diferentes para cada version mas que nada para verificar los permisos del usuario. En Arquitectura Limpia segun lo estudiado, la autenticación se gestionaría de mejor manera en la capa de controladores/adaptadores, mientras que la autorización se verificaría en los casos de uso en donde si no se tiene la autorizacion necesaria se podria lanzar por ejemplo un mensaje de error que diga, "Oye no tienes permiso para hacer esto" o "No se cuetna con la autorizacion para este rol". Y estoharia que el caso de uso "Agregar Producto" requeriera que el usuario tenga el rol de "Administrador", evitando que usuarios no autorizados realicen cambios críticos en el inventario.
