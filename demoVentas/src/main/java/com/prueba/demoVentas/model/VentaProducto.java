package com.prueba.demoVentas.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaProducto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idProducto;
	private int cantidad;
	private int iva;

}
