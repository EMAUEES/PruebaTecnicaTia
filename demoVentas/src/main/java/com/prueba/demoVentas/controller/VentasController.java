package com.prueba.demoVentas.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prueba.demoVentas.component.VentasComponent;
import com.prueba.demoVentas.model.RequestLocal;
import com.prueba.demoVentas.model.RequestLocalProducto;
import com.prueba.demoVentas.model.RequestProducto;
import com.prueba.demoVentas.model.RequestStock;
import com.prueba.demoVentas.model.RequestVenta;
import com.prueba.demoVentas.model.ResponseGenerico;
import com.prueba.demoVentas.model.ResponseLocales;
import com.prueba.demoVentas.model.ResponseProductos;
import com.prueba.demoVentas.model.ResponseProductosLocal;
import com.prueba.demoVentas.model.ResponseTransacciones;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-02T14:50:01.213-05:00")
public class VentasController {

	@Autowired
	private VentasComponent ventasComponent;

	@Transactional
	@PostMapping(path = "/crearLocal", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseGenerico> crearLocales(@RequestBody List<RequestLocal> request)
			throws JsonProcessingException {
		ResponseGenerico result = new ResponseGenerico();
		result = ventasComponent.crearLocales(request);
		return ResponseEntity.ok().body(result);

	}

	@Transactional
	@PostMapping(path = "/crearProducto", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseGenerico> crearProductos(@RequestBody List<RequestProducto> requestProducto)
			throws JsonProcessingException {
		ResponseGenerico result = new ResponseGenerico();
		result = ventasComponent.crearProductos(requestProducto);
		return ResponseEntity.ok().body(result);

	}

	@Transactional
	@PostMapping(path = "/asignarProductosLocal", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseGenerico> asignarProductoLocal(@RequestBody RequestLocalProducto requestLocalProducto)
			throws JsonProcessingException {
		ResponseGenerico result = new ResponseGenerico();
		result = ventasComponent.asignarProductoLocal(requestLocalProducto);
		return ResponseEntity.ok().body(result);

	}

	@Transactional
	@PutMapping(path = "/actualizarStock", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseGenerico> actualizarStock(@RequestBody RequestStock requestStock)
			throws JsonProcessingException {
		ResponseGenerico result = new ResponseGenerico();
		result = ventasComponent.actualizarStock(requestStock);
		return ResponseEntity.ok().body(result);

	}

	@Transactional
	@PostMapping(path = "/ventaProducto", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseGenerico> ventaProducto(@RequestBody RequestVenta requestVenta)
			throws JsonProcessingException {
		ResponseGenerico result = new ResponseGenerico();
		result = ventasComponent.ventaProducto(requestVenta);
		return ResponseEntity.ok().body(result);

	}

	@GetMapping(value = "/getlocales")
	public ResponseEntity<List<ResponseLocales>> getAllLocales() {

		return ResponseEntity.ok().body(ventasComponent.obtenerLocales());
	}

	@GetMapping(value = "/getProductos")
	public ResponseEntity<List<ResponseProductos>> getAllProductos() {

		return ResponseEntity.ok().body(ventasComponent.obtenerProductos());
	}

	@GetMapping(value = "/getProductosDeLocal/{idLocal}")
	public ResponseEntity<List<ResponseProductosLocal>> getProductosLocal(@PathVariable Integer idLocal) {

		return ResponseEntity.ok().body(ventasComponent.obtenerProductosLocal(idLocal));
	}

	@GetMapping(value = "/getTransacciones")
	public ResponseEntity<List<ResponseTransacciones>> getAllTransacciones() {

		return ResponseEntity.ok().body(ventasComponent.obtenerTransacciones());
	}

	@GetMapping(value = "/getTransaccion/{idTransaccion}")
	public ResponseEntity<ResponseTransacciones> getTransaccion(@PathVariable Integer idTransaccion) {

		return ResponseEntity.ok().body(ventasComponent.obtenerTransaccion(idTransaccion));
	}

}
