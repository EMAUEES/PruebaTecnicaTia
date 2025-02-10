package com.prueba.demoVentas.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductos {

	private Integer idProducto;
	private String nombre;
	private String descripcion;
	private long codigoBarras;
	private double pvp;
	private Date fechaCreacion;
	private Date fechaActualizacion;

}
