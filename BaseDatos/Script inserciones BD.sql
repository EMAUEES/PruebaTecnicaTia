USE [pruebaDB];
GO

-- ============================
-- Insertar Locales (Sucursales)
-- ============================
INSERT INTO [dbo].[local] (idLocal, nombre, provincia, fechaCreacion, estado)
VALUES 
(1, 'Tía Quito Centro', 'Pichincha', GETDATE(), 1),
(2, 'Tía Guayaquil Norte', 'Guayas', GETDATE(), 1),
(3, 'Tía Cuenca Mall', 'Azuay', GETDATE(), 1),
(4, 'Tía Manta Sur', 'Manabí', GETDATE(), 1),
(5, 'Tía Loja Central', 'Loja', GETDATE(), 1);
GO

-- ============================
-- Insertar Productos
-- ============================
INSERT INTO [dbo].[productos] (idProducto, nombre, descripcion, codigoBarras, pvp, fechaCreacion, estado)
VALUES
(1, 'Arroz Súper Extra', 'Arroz premium de alta calidad, 5 kg', 789123456001, 8.50, GETDATE(), 1),
(2, 'Aceite de Girasol', 'Aceite de girasol 1 litro', 789123456002, 4.20, GETDATE(), 1),
(3, 'Azúcar Refinada', 'Azúcar blanca refinada 2 kg', 789123456003, 3.00, GETDATE(), 1),
(4, 'Leche Entera', 'Leche entera UHT 1 litro', 789123456004, 1.20, GETDATE(), 1),
(5, 'Galletas de Chocolate', 'Paquete de galletas de chocolate 300 g', 789123456005, 2.80, GETDATE(), 1),
(6, 'Papel Higiénico', 'Pack de 12 rollos de papel higiénico doble hoja', 789123456006, 6.50, GETDATE(), 1),
(7, 'Jugo de Naranja', 'Jugo natural de naranja 1 litro', 789123456007, 2.00, GETDATE(), 1),
(8, 'Detergente en Polvo', 'Detergente para ropa 3 kg', 789123456008, 5.75, GETDATE(), 1),
(9, 'Shampoo Herbal', 'Shampoo con extractos naturales 400 ml', 789123456009, 4.10, GETDATE(), 1),
(10, 'Café Molido', 'Café molido 500 g', 789123456010, 7.90, GETDATE(), 1);
GO

-- ============================
-- Relacionar Locales con Productos
-- ============================
INSERT INTO [dbo].[localProducto] (idLocalProducto, idLocal, idProducto, stock, fechaCreacion, estado)
VALUES
(1, 1, 1, 150, GETDATE(), 1),
(2, 1, 2, 100, GETDATE(), 1),
(3, 2, 3, 200, GETDATE(), 1),
(4, 2, 4, 180, GETDATE(), 1),
(5, 3, 5, 90, GETDATE(), 1),
(6, 3, 6, 120, GETDATE(), 1),
(7, 4, 7, 250, GETDATE(), 1),
(8, 4, 8, 80, GETDATE(), 1),
(9, 5, 9, 130, GETDATE(), 1),
(10, 5, 10, 70, GETDATE(), 1);
GO

-- ============================
-- Insertar Transacciones
-- ============================
INSERT INTO [dbo].[transacciones] (idTransaccion, idLocal, tipo, fechaCreacion, estado)
VALUES
(1, 1, 'COMP', GETDATE(), 1),
(2, 2, 'VENT', GETDATE(), 1),
(3, 3, 'VENT', GETDATE(), 1),
(4, 4, 'COMP', GETDATE(), 1),
(5, 5, 'VENT', GETDATE(), 1);
GO

-- ============================
-- Insertar Productos por Transacción
-- ============================
INSERT INTO [dbo].[transaccionProducto] (idTransProducto, idTransaccion, idProducto, cantidad, iva, fechaCreacion, estado)
VALUES
(1, 1, 1, 50, 12, GETDATE(), 1),   -- Compra de Arroz para Tía Quito Centro
(2, 1, 2, 30, 12, GETDATE(), 1),   -- Compra de Aceite para Tía Quito Centro

(3, 2, 3, 40, 12, GETDATE(), 1),   -- Venta de Azúcar en Tía Guayaquil Norte
(4, 2, 4, 25, 12, GETDATE(), 1),   -- Venta de Leche en Tía Guayaquil Norte

(5, 3, 5, 20, 12, GETDATE(), 1),   -- Venta de Galletas en Tía Cuenca Mall
(6, 3, 6, 15, 12, GETDATE(), 1),   -- Venta de Papel Higiénico en Tía Cuenca Mall

(7, 4, 7, 60, 12, GETDATE(), 1),   -- Compra de Jugos para Tía Manta Sur
(8, 4, 8, 45, 12, GETDATE(), 1),   -- Compra de Detergente para Tía Manta Sur

(9, 5, 9, 35, 12, GETDATE(), 1),   -- Venta de Shampoo en Tía Loja Central
(10, 5, 10, 50, 12, GETDATE(), 1); -- Venta de Café en Tía Loja Central
GO
