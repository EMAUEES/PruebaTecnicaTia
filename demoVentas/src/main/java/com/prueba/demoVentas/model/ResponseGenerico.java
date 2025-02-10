package com.prueba.demoVentas.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGenerico {

	private Integer code = null;
	private String status = null;
	private String message = null;

}
