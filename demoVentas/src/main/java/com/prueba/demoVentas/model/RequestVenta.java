package com.prueba.demoVentas.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestVenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idLocal;
	private String tipoTrans;
	private List<VentaProducto> productos;

}
