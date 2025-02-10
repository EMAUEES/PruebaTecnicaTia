package com.prueba.demoVentas.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProducto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String descripcion;
	private long codigoBarras;
	private double pvp;

}
