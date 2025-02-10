package com.prueba.demoVentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prueba.demoVentas.entity.Local;

public interface LocalRepository extends JpaRepository<Local, Integer> {

	@Query(value = "select coalesce(max(idLocal),0)+1 from dbo.local", nativeQuery = true)
	Integer maxId();

	@Query(value = "select * from dbo.local where idLocal =?1 and estado = 1", nativeQuery = true)
	Local existeLocal(Integer id);
	
	@Query(value = "select * from dbo.local where estado = 1", nativeQuery = true)
	List<Local> locales();

}
