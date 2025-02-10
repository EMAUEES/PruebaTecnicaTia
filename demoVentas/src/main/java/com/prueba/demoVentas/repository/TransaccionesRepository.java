package com.prueba.demoVentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prueba.demoVentas.entity.Transacciones;

public interface TransaccionesRepository extends JpaRepository<Transacciones, Integer>{
	
	@Query(value = "select coalesce(max(idTransaccion),0)+1 from dbo.transacciones", nativeQuery = true)
	Integer maxId();
	
	@Query(value = "select * from dbo.transacciones where idTransaccion =?1", nativeQuery = true)
	Transacciones existeTransaccion(Integer id);
	
	@Query(value = "select * from dbo.transacciones", nativeQuery = true)
	List<Transacciones> transacciones();

}
