package com.prueba.demoVentas.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductosLocal {

	private Integer idProducto;
	private String nombre;
	private String descripcion;
	private long codigoBarras;
	private double pvp;
	private Integer stock;

}
