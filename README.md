# EA3 - Incorporando UI y BD

## Descripción del proyecto

Este proyecto consiste en desarrollar una aplicación de escritorio utilizando Java Swing para probar la conexión con una base de datos SQLite mediante JDBC.

## Objetivo

Implementar una interfaz gráfica que permita ingresar los datos de conexión y verificar si es posible establecer comunicación con una base de datos.

## Tecnologías utilizadas

- Java JDK.
- Java Swing.
- JDBC (Java Database Connectivity).
- SQLite.
- Visual Studio Code.

## Estructura del proyecto

- `src/`: contiene el código fuente de Java.
- `lib/`: contiene el controlador JDBC de SQLite.
- `datos/`: almacena la base de datos `inventario.db`.
- `evidencias/`: contiene las capturas de las pruebas realizadas.
- `.vscode/`: contiene la configuración del proyecto.

## Ejecución del proyecto

1. Abrir la carpeta del proyecto en Visual Studio Code.
2. Verificar que el controlador `sqlite-jdbc.jar` esté disponible en la carpeta `lib`.
3. Compilar el programa:

   `javac -cp "lib\sqlite-jdbc.jar" -d . src\ConexionTester.java`

4. Ejecutar la aplicación:

   `java -cp ".;lib\sqlite-jdbc.jar" ConexionTester`

5. Pulsar el botón **Probar conexión** para verificar la conexión con SQLite.

## Resultados

Se realizaron pruebas de conexión exitosa y de conexión fallida, verificando que la aplicación muestra mensajes informativos según el resultado obtenido.

## Autor

Wendy Mosquera

## Tipo de trabajo

Actividad académica.
