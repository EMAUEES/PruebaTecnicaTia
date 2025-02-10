package com.prueba.demoVentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prueba.demoVentas.entity.TransaccionProducto;

public interface TransaccionProductoRepository extends JpaRepository<TransaccionProducto, Integer>{
	
	@Query(value = "select coalesce(max(idTransProducto),0)+1 from dbo.transaccionProducto", nativeQuery = true)
	Integer maxId();

	@Query(value = "select * from dbo.transaccionProducto where idTransaccion =?1", nativeQuery = true)
	List<TransaccionProducto> productosTrans(Integer idTrans);
}
