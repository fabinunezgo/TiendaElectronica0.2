-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-12-2024 a las 16:40:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendaelectronica2`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarCliente` (IN `cedulaCliente` VARCHAR(20), IN `NombreCompleto` VARCHAR(255), IN `Direccion` VARCHAR(255), IN `Telefono` VARCHAR(15), IN `Correo` VARCHAR(100))   BEGIN
    INSERT INTO Cliente (cedula, nombreCompleto, direccion, telefono, correo)
    VALUES (cedulaCliente, NombreCompleto, Direccion, Telefono, Correo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarDetalleVenta` (IN `VentaId` INT, IN `ProductoCodigo` INT, IN `Cantidad` INT, IN `PrecioUnitario` DECIMAL(10,2), IN `Subtotal` DECIMAL(10,2))   BEGIN
    INSERT INTO DetalleVentas (ventaId, productoCodigo, cantidad, precioUnitario, subtotal)
    VALUES (VentaId, ProductoCodigo, Cantidad, PrecioUnitario, Subtotal);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProducto` (IN `Nombre` VARCHAR(255), IN `Categoria` VARCHAR(100), IN `Precio` DECIMAL(10,2), IN `CantidadDisponible` INT, IN `Proveedor` VARCHAR(255))   BEGIN
    INSERT INTO Producto (nombre, categoria, precio, cantidadDisponible, proveedor)
    VALUES (Nombre, Categoria, Precio, CantidadDisponible, Proveedor);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProveedor` (IN `Nombre` VARCHAR(100), IN `Direccion` VARCHAR(150), IN `Telefono` VARCHAR(15), IN `Correo` VARCHAR(100), IN `fechaRegistro` DATE)   BEGIN
    INSERT INTO Proveedor (nombre, direccion, telefono, correo, fechaRegistro)
    VALUES (Nombre, Direccion, Telefono, Correo, fechaRegistro);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarUsuario` (IN `Username` VARCHAR(50), IN `Password` VARCHAR(255), IN `Rol` ENUM('admin','empleado'), IN `Correo` VARCHAR(100))   BEGIN
    INSERT INTO Usuarios (username, password, rol, correo)
    VALUES (Username, Password, Rol, Correo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarVenta` (IN `Fecha` DATETIME, IN `clienteCedula` VARCHAR(20), IN `Total` DECIMAL(10,2))   BEGIN
    INSERT INTO Ventas (fecha, clienteCedula, total)
    VALUES (Fecha, ClienteCedula, Total);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `cedula` varchar(20) NOT NULL,
  `nombreCompleto` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventas`
--

CREATE TABLE `detalleventas` (
  `id` int(11) NOT NULL,
  `ventaId` int(11) NOT NULL,
  `productoCodigo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioUnitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `categoria` varchar(100) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidadDisponible` int(11) NOT NULL,
  `proveedor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `fechaRegistro` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` enum('admin','empleado') DEFAULT 'empleado',
  `correo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` int(11) NOT NULL,
  `fecha` datetime DEFAULT current_timestamp(),
  `clienteCedula` varchar(20) DEFAULT NULL,
  `total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cedula`);

--
-- Indices de la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ventaId` (`ventaId`),
  ADD KEY `productoCodigo` (`productoCodigo`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `clienteCedula` (`clienteCedula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  ADD CONSTRAINT `detalleventas_ibfk_1` FOREIGN KEY (`ventaId`) REFERENCES `ventas` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `detalleventas_ibfk_2` FOREIGN KEY (`productoCodigo`) REFERENCES `productos` (`codigo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`clienteCedula`) REFERENCES `clientes` (`cedula`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
