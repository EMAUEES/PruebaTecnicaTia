package com.prueba.demoVentas.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseLocales {
	
	private Integer idLocal;
    private String nombre;
    private String provincia;
    private Date fechaCreacion;
    private Date fechaActualizacion;

}
