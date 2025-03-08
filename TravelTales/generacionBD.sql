-- Creamos la base de datos
CREATE DATABASE TravelTales;

-- Seleccionamos la base de datos nueva
USE TravelTales;

-- Añadimos la tabla viajes a la base de datos
CREATE TABLE viajes(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre_viaje VARCHAR(100) NOT NULL UNIQUE,
destino_viaje VARCHAR(100) NOT NULL,
fecha_inicio DATE NOT NULL,
fecha_fin DATE NOT NULL
);

-- Mostramos todas las tablas
SHOW TABLES;

-- Mostramos la columna de la tabla
SELECT * FROM viajes;
