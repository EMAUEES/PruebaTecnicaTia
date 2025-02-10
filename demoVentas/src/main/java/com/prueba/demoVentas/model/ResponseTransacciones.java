package com.prueba.demoVentas.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTransacciones {

	private Integer idLocal;
	private Integer idTransaccion;
	private String tipoTrans;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;
	private List<ResponseProductos> productos;

}
