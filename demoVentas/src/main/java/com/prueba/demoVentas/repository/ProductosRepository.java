package com.prueba.demoVentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prueba.demoVentas.entity.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {

	@Query(value = "select coalesce(max(idProducto),0)+1 from dbo.productos", nativeQuery = true)
	Integer maxId();

	@Query(value = "select * from dbo.productos where idProducto =?1 and estado = 1", nativeQuery = true)
	Productos existeProducto(Integer id);

	@Query(value = "select * from dbo.productos where codigoBarras =?1", nativeQuery = true)
	Productos existeCodBarra(Long id);

	@Query(value = "select * from dbo.productos where estado = 1", nativeQuery = true)
	List<Productos> productos();

}
