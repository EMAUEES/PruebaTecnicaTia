package com.prueba.demoVentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prueba.demoVentas.entity.LocalProducto;

public interface LocalProductoRepository extends JpaRepository<LocalProducto, Integer> {

	@Query(value = "select coalesce(max(idLocalProducto),0)+1 from dbo.localProducto", nativeQuery = true)
	Integer maxId();

	@Query(value = "select * from dbo.localProducto where idLocal = ?1 and idProducto = ?2", nativeQuery = true)
	LocalProducto existeProducto(Integer idLocal, Integer idProducto);
	
	@Query(value = "select * from dbo.localProducto where idLocal = ?1 and estado = 1", nativeQuery = true)
	List<LocalProducto> productosLocal(Integer idLocal);

}
