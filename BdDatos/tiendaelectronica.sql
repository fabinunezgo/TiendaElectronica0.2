-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2024 a las 09:35:00
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendaelectronica`
--
CREATE DATABASE IF NOT EXISTS `tiendaelectronica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tiendaelectronica`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--
-- Creación: 04-12-2024 a las 08:17:59
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `Cedula` varchar(9) NOT NULL,
  `nombreCompleto` varchar(100) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(100) NOT NULL,
  PRIMARY KEY (`Cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `cliente`:
--

--
-- Truncar tablas antes de insertar `cliente`
--

TRUNCATE TABLE `cliente`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventa`
--
-- Creación: 04-12-2024 a las 08:20:03
--

DROP TABLE IF EXISTS `detalleventa`;
CREATE TABLE IF NOT EXISTS `detalleventa` (
  `id` int(11) NOT NULL,
  `ventaId` int(11) NOT NULL,
  `productoCodigo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioUnitario` decimal(10,0) NOT NULL,
  `subtotal` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `detalleventa`:
--

--
-- Truncar tablas antes de insertar `detalleventa`
--

TRUNCATE TABLE `detalleventa`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--
-- Creación: 04-12-2024 a las 08:24:27
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `categoria` varchar(100) NOT NULL,
  `precio` int(11) NOT NULL,
  `cantidadDisponible` int(11) NOT NULL,
  `proveedor` varchar(200) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `productos`:
--

--
-- Truncar tablas antes de insertar `productos`
--

TRUNCATE TABLE `productos`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--
-- Creación: 04-12-2024 a las 08:26:10
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `fechaRegistro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `proveedor`:
--

--
-- Truncar tablas antes de insertar `proveedor`
--

TRUNCATE TABLE `proveedor`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--
-- Creación: 04-12-2024 a las 08:29:52
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `rol` varchar(20) NOT NULL,
  `correo` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `usuarios`:
--

--
-- Truncar tablas antes de insertar `usuarios`
--

TRUNCATE TABLE `usuarios`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--
-- Creación: 04-12-2024 a las 08:32:24
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `idCliente` varchar(100) NOT NULL,
  `total` int(11) NOT NULL,
  `productosVendidos` int(11) NOT NULL,
  `subtotal` decimal(10,0) NOT NULL,
  `impuesto` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `ventas`:
--

--
-- Truncar tablas antes de insertar `ventas`
--

TRUNCATE TABLE `ventas`;

--
-- Metadatos
--
USE `phpmyadmin`;

--
-- Metadatos para la tabla cliente
--
-- Error leyendo datos de la tabla phpmyadmin.pma__column_info: #1100 - Tabla &#039;pma__column_info&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__table_uiprefs: #1100 - Tabla &#039;pma__table_uiprefs&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__tracking: #1100 - Tabla &#039;pma__tracking&#039; no fue trabada con LOCK TABLES

--
-- Metadatos para la tabla detalleventa
--
-- Error leyendo datos de la tabla phpmyadmin.pma__column_info: #1100 - Tabla &#039;pma__column_info&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__table_uiprefs: #1100 - Tabla &#039;pma__table_uiprefs&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__tracking: #1100 - Tabla &#039;pma__tracking&#039; no fue trabada con LOCK TABLES

--
-- Metadatos para la tabla productos
--
-- Error leyendo datos de la tabla phpmyadmin.pma__column_info: #1100 - Tabla &#039;pma__column_info&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__table_uiprefs: #1100 - Tabla &#039;pma__table_uiprefs&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__tracking: #1100 - Tabla &#039;pma__tracking&#039; no fue trabada con LOCK TABLES

--
-- Metadatos para la tabla proveedor
--
-- Error leyendo datos de la tabla phpmyadmin.pma__column_info: #1100 - Tabla &#039;pma__column_info&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__table_uiprefs: #1100 - Tabla &#039;pma__table_uiprefs&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__tracking: #1100 - Tabla &#039;pma__tracking&#039; no fue trabada con LOCK TABLES

--
-- Metadatos para la tabla usuarios
--
-- Error leyendo datos de la tabla phpmyadmin.pma__column_info: #1100 - Tabla &#039;pma__column_info&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__table_uiprefs: #1100 - Tabla &#039;pma__table_uiprefs&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__tracking: #1100 - Tabla &#039;pma__tracking&#039; no fue trabada con LOCK TABLES

--
-- Metadatos para la tabla ventas
--
-- Error leyendo datos de la tabla phpmyadmin.pma__column_info: #1100 - Tabla &#039;pma__column_info&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__table_uiprefs: #1100 - Tabla &#039;pma__table_uiprefs&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__tracking: #1100 - Tabla &#039;pma__tracking&#039; no fue trabada con LOCK TABLES

--
-- Metadatos para la base de datos tiendaelectronica
--
-- Error leyendo datos de la tabla phpmyadmin.pma__bookmark: #1100 - Tabla &#039;pma__bookmark&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__relation: #1100 - Tabla &#039;pma__relation&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__savedsearches: #1100 - Tabla &#039;pma__savedsearches&#039; no fue trabada con LOCK TABLES
-- Error leyendo datos de la tabla phpmyadmin.pma__central_columns: #1100 - Tabla &#039;pma__central_columns&#039; no fue trabada con LOCK TABLES
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
