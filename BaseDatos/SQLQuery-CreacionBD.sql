USE [master]
GO
/****** Object:  Database [pruebaDB]    Script Date: 10/02/2025 2:09:02 ******/
CREATE DATABASE [pruebaDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'pruebaDB', FILENAME = N'C:\SQLData\pruebaDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'pruebaDB_log', FILENAME = N'C:\SQLData\pruebaDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [pruebaDB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [pruebaDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [pruebaDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [pruebaDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [pruebaDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [pruebaDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [pruebaDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [pruebaDB] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [pruebaDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [pruebaDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [pruebaDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [pruebaDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [pruebaDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [pruebaDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [pruebaDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [pruebaDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [pruebaDB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [pruebaDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [pruebaDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [pruebaDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [pruebaDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [pruebaDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [pruebaDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [pruebaDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [pruebaDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [pruebaDB] SET  MULTI_USER 
GO
ALTER DATABASE [pruebaDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [pruebaDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [pruebaDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [pruebaDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [pruebaDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [pruebaDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [pruebaDB] SET QUERY_STORE = ON
GO
ALTER DATABASE [pruebaDB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [pruebaDB]
GO
/****** Object:  Table [dbo].[local]    Script Date: 10/02/2025 2:09:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[local](
	[idLocal] [int] NOT NULL,
	[nombre] [varchar](255) NOT NULL,
	[provincia] [varchar](255) NULL,
	[fechaCreacion] [datetime] NULL,
	[fechaActualizacion] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idLocal] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[localProducto]    Script Date: 10/02/2025 2:09:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[localProducto](
	[idLocalProducto] [int] NOT NULL,
	[idLocal] [int] NOT NULL,
	[idProducto] [int] NOT NULL,
	[stock] [int] NULL,
	[fechaCreacion] [datetime] NULL,
	[fechaActualizacion] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idLocalProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[productos]    Script Date: 10/02/2025 2:09:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[productos](
	[idProducto] [int] NOT NULL,
	[nombre] [varchar](255) NOT NULL,
	[descripcion] [varchar](500) NULL,
	[codigoBarras] [bigint] NOT NULL,
	[pvp] [float] NOT NULL,
	[fechaCreacion] [datetime] NULL,
	[fechaActualizacion] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[codigoBarras] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[transacciones]    Script Date: 10/02/2025 2:09:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transacciones](
	[idTransaccion] [int] NOT NULL,
	[idLocal] [int] NOT NULL,
	[tipo] [varchar](50) NOT NULL,
	[fechaCreacion] [datetime] NULL,
	[fechaActualizacion] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idTransaccion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[transaccionProducto]    Script Date: 10/02/2025 2:09:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transaccionProducto](
	[idTransProducto] [int] NOT NULL,
	[idTransaccion] [int] NOT NULL,
	[idProducto] [int] NOT NULL,
	[cantidad] [int] NOT NULL,
	[iva] [int] NOT NULL,
	[fechaCreacion] [datetime] NULL,
	[fechaActualizacion] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idTransProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[local] ADD  DEFAULT (getdate()) FOR [fechaCreacion]
GO
ALTER TABLE [dbo].[localProducto] ADD  DEFAULT ((0)) FOR [stock]
GO
ALTER TABLE [dbo].[localProducto] ADD  DEFAULT (getdate()) FOR [fechaCreacion]
GO
ALTER TABLE [dbo].[productos] ADD  DEFAULT (getdate()) FOR [fechaCreacion]
GO
ALTER TABLE [dbo].[transacciones] ADD  DEFAULT (getdate()) FOR [fechaCreacion]
GO
ALTER TABLE [dbo].[transaccionProducto] ADD  DEFAULT (getdate()) FOR [fechaCreacion]
GO
ALTER TABLE [dbo].[localProducto]  WITH CHECK ADD  CONSTRAINT [FK_localProducto_local] FOREIGN KEY([idLocal])
REFERENCES [dbo].[local] ([idLocal])
GO
ALTER TABLE [dbo].[localProducto] CHECK CONSTRAINT [FK_localProducto_local]
GO
ALTER TABLE [dbo].[localProducto]  WITH CHECK ADD  CONSTRAINT [FK_localProducto_productos] FOREIGN KEY([idProducto])
REFERENCES [dbo].[productos] ([idProducto])
GO
ALTER TABLE [dbo].[localProducto] CHECK CONSTRAINT [FK_localProducto_productos]
GO
ALTER TABLE [dbo].[transacciones]  WITH CHECK ADD  CONSTRAINT [FK_transacciones_local] FOREIGN KEY([idLocal])
REFERENCES [dbo].[local] ([idLocal])
GO
ALTER TABLE [dbo].[transacciones] CHECK CONSTRAINT [FK_transacciones_local]
GO
ALTER TABLE [dbo].[transaccionProducto]  WITH CHECK ADD  CONSTRAINT [FK_transaccionProducto_productos] FOREIGN KEY([idProducto])
REFERENCES [dbo].[productos] ([idProducto])
GO
ALTER TABLE [dbo].[transaccionProducto] CHECK CONSTRAINT [FK_transaccionProducto_productos]
GO
ALTER TABLE [dbo].[transaccionProducto]  WITH CHECK ADD  CONSTRAINT [FK_transaccionProducto_transacciones] FOREIGN KEY([idTransaccion])
REFERENCES [dbo].[transacciones] ([idTransaccion])
GO
ALTER TABLE [dbo].[transaccionProducto] CHECK CONSTRAINT [FK_transaccionProducto_transacciones]
GO
USE [master]
GO
ALTER DATABASE [pruebaDB] SET  READ_WRITE 
GO
