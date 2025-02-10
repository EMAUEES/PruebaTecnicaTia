package com.prueba.demoVentas.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.prueba.demoVentas.entity.Local;
import com.prueba.demoVentas.entity.LocalProducto;
import com.prueba.demoVentas.entity.Productos;
import com.prueba.demoVentas.entity.TransaccionProducto;
import com.prueba.demoVentas.entity.Transacciones;
import com.prueba.demoVentas.model.ProductoRq;
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
import com.prueba.demoVentas.model.StockProducto;
import com.prueba.demoVentas.model.VentaProducto;
import com.prueba.demoVentas.repository.LocalProductoRepository;
import com.prueba.demoVentas.repository.LocalRepository;
import com.prueba.demoVentas.repository.ProductosRepository;
import com.prueba.demoVentas.repository.TransaccionProductoRepository;
import com.prueba.demoVentas.repository.TransaccionesRepository;

@Component
public class VentasComponent {

	@Autowired
	private LocalRepository localRepository;

	@Autowired
	private ProductosRepository productosRepository;

	@Autowired
	private LocalProductoRepository localProductoRepository;

	@Autowired
	private TransaccionesRepository transaccionesRepository;

	@Autowired
	private TransaccionProductoRepository transaccionProductoRepository;

	private static final Logger logger = LogManager.getLogger(VentasComponent.class);

	public ResponseGenerico crearLocales(List<RequestLocal> requestLocal) {

		ResponseGenerico response = new ResponseGenerico();
		String message = "Locales creados con exito";
		String status = "SUCCESS";
		int code = HttpStatus.OK.value();

		try {
			if (requestLocal.isEmpty()) {
				message = "No hay locales para crear";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			List<Local> locales = new ArrayList<>();
			int id = localRepository.maxId();

			for (RequestLocal rl : requestLocal) {
				Local l = new Local();

				l.setIdLocal(id);
				l.setNombre(rl.getNombre());
				l.setProvincia(rl.getProvincia());
				l.setEstado(1);
				locales.add(l);
				id += 1;
			}

			localRepository.saveAll(locales);

			response.setCode(code);
			response.setMessage(message);
			response.setStatus(status);
			logger.info(message);
		} catch (Exception e) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage(message);
			response.setStatus("ERROR");
			logger.error("Error al crear locales {}", e);
		}

		return response;
	}

	public ResponseGenerico crearProductos(List<RequestProducto> requestProducto) {

		ResponseGenerico response = new ResponseGenerico();
		String message = "Productos creados con exito";
		String status = "SUCCESS";
		int code = HttpStatus.OK.value();

		try {
			if (requestProducto.isEmpty()) {
				message = "No hay productos para crear";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				throw new Exception(message);
			}

			List<Productos> productos = new ArrayList<>();
			int id = productosRepository.maxId();

			for (RequestProducto rp : requestProducto) {
				Productos p = new Productos();
				p = productosRepository.existeCodBarra(rp.getCodigoBarras());

				if (p != null) {
					message = "El producto con el codigo de barra: " + rp.getCodigoBarras() + " ya existe";
					status = "ERROR";
					code = HttpStatus.BAD_REQUEST.value();
					logger.info(message);
					throw new Exception(message);
				}

				p = new Productos();
				p.setIdProducto(id);
				p.setNombre(rp.getNombre());
				p.setDescripcion(rp.getDescripcion());
				p.setCodigoBarras(rp.getCodigoBarras());
				p.setPvp(rp.getPvp());
				p.setEstado(1);
				productos.add(p);
				id += 1;
			}

			productosRepository.saveAll(productos);

			response.setCode(code);
			response.setMessage(message);
			response.setStatus(status);
			logger.info(message);
		} catch (Exception e) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage(message);
			response.setStatus("ERROR");
			logger.error("Error al crear productos {}", e);
		}

		return response;
	}

	public ResponseGenerico asignarProductoLocal(RequestLocalProducto requestLocalProducto) {

		ResponseGenerico response = new ResponseGenerico();
		String message = "Productos asignados con exito";
		String status = "SUCCESS";
		int code = HttpStatus.OK.value();

		try {

			if (requestLocalProducto.getIdLocal() == null) {
				message = "Debe ingresar un local";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			if (requestLocalProducto.getProductos().isEmpty()) {
				message = "Debe ingresar productos";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			Local l = new Local();
			l = localRepository.existeLocal(requestLocalProducto.getIdLocal());

			if (l == null) {
				message = "El local: " + requestLocalProducto.getIdLocal() + " no existe o se encuentra inactivo";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			List<LocalProducto> localProductos = new ArrayList<>();
			int id = localProductoRepository.maxId();

			for (ProductoRq pq : requestLocalProducto.getProductos()) {
				LocalProducto lp = new LocalProducto();
				Productos p = new Productos();
				p = productosRepository.existeProducto(pq.getIdProducto());

				if (p == null) {
					message = "El producto: " + pq.getIdProducto() + " no existe o se encuentra inactivo";
					status = "ERROR";
					code = HttpStatus.BAD_REQUEST.value();
					logger.info(message);
					throw new Exception(message);
				}

				lp = localProductoRepository.existeProducto(requestLocalProducto.getIdLocal(), pq.getIdProducto());

				if (lp == null) {
					lp = new LocalProducto();
					lp.setIdLocalProducto(id);
					lp.setIdLocal((l));
					lp.setIdProducto(p);
					lp.setStock(pq.getStock());
					lp.setEstado(1);
					localProductos.add(lp);
					id += 1;
				}
			}

			localProductoRepository.saveAll(localProductos);

			response.setCode(code);
			response.setMessage(message);
			response.setStatus(status);
			logger.info(message);
		} catch (Exception e) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage(message);
			response.setStatus("ERROR");
			logger.error("Error al asignar productos {}", e);
		}

		return response;
	}

	public ResponseGenerico actualizarStock(RequestStock requestStock) {

		ResponseGenerico response = new ResponseGenerico();
		String message = "Stock actualizado con exito";
		String status = "SUCCESS";
		int code = HttpStatus.OK.value();
		Date fechaActualizacion = new Date();

		try {
			if (requestStock.getIdLocal() == null) {
				message = "Debe ingresar un local";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			if (requestStock.getTipoTrans() == null) {
				message = "Debe ingresar un tipo: COMP o VENT";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			if (requestStock.getStockProducto().isEmpty()) {
				message = "Debe ingresar productos";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			List<LocalProducto> productosStock = new ArrayList<>();

			for (StockProducto sp : requestStock.getStockProducto()) {
				Integer nuevoStock = 0;
				LocalProducto lp = new LocalProducto();
				lp = localProductoRepository.existeProducto(requestStock.getIdLocal(), sp.getIdProducto());

				if (lp == null) {
					message = "El producto: " + sp.getIdProducto() + " no existe en el local";
					status = "ERROR";
					code = HttpStatus.BAD_REQUEST.value();
					logger.info(message);
					throw new Exception(message);
				}

				if (requestStock.getTipoTrans().equals("COMP"))
					nuevoStock = lp.getStock() + sp.getCantidad();
				else {
					if (lp.getStock() != 0)
						nuevoStock = lp.getStock() - sp.getCantidad();
				}

				lp.setStock(nuevoStock);
				lp.setFechaActualizacion(fechaActualizacion);
				productosStock.add(lp);

			}

			localProductoRepository.saveAll(productosStock);

			response.setCode(code);
			response.setMessage(message);
			response.setStatus(status);
			logger.info(message);
		} catch (Exception e) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage(message);
			response.setStatus("ERROR");
			logger.error("Error al actualizar stock {}", e);
		}

		return response;
	}

	public ResponseGenerico ventaProducto(RequestVenta requestVenta) {

		ResponseGenerico response = new ResponseGenerico();
		ResponseGenerico responseStock = new ResponseGenerico();
		String message = "Venta registrada con exito";
		String status = "SUCCESS";
		int code = HttpStatus.OK.value();

		try {
			if (requestVenta.getIdLocal() == null) {
				message = "Debe ingresar un local";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			if (requestVenta.getTipoTrans().equals("")) {
				message = "Debe ingresar el tipo de transaccion COMP o VENT";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			if (requestVenta.getProductos().isEmpty()) {
				message = "Debe ingresar productos";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			int id = transaccionesRepository.maxId();
			Transacciones t = new Transacciones();
			Local l = new Local();
			l = localRepository.existeLocal(requestVenta.getIdLocal());

			if (l == null) {
				message = "El local: " + requestVenta.getIdLocal() + " no existe o se encuentra inactivo";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			t.setIdTransaccion(id);
			t.setIdLocal(l);
			t.setTipo(requestVenta.getTipoTrans());
			t.setEstado(1);

			List<TransaccionProducto> productos = new ArrayList<>();
			List<StockProducto> stockProducto = new ArrayList<>();
			int idTrans = transaccionProductoRepository.maxId();

			for (VentaProducto vp : requestVenta.getProductos()) {
				TransaccionProducto tp = new TransaccionProducto();
				StockProducto sp = new StockProducto();
				LocalProducto lp = new LocalProducto();
				lp = localProductoRepository.existeProducto(requestVenta.getIdLocal(), vp.getIdProducto());

				if (lp == null) {
					message = "El producto: " + vp.getIdProducto() + " no existe en el local";
					status = "ERROR";
					code = HttpStatus.BAD_REQUEST.value();
					logger.info(message);
					throw new Exception(message);
				}

				tp.setIdTransProducto(idTrans);
				tp.setIdTransaccion(t);
				tp.setIdProducto(lp.getIdProducto());
				tp.setCantidad(vp.getCantidad());
				tp.setIva(vp.getIva());
				tp.setEstado(1);
				productos.add(tp);
				idTrans += 1;

				sp.setIdProducto(vp.getIdProducto());
				sp.setCantidad(vp.getCantidad());
				stockProducto.add(sp);

			}

			transaccionesRepository.save(t);
			transaccionProductoRepository.saveAll(productos);

			RequestStock rs = new RequestStock();

			rs.setIdLocal(requestVenta.getIdLocal());
			rs.setTipoTrans(requestVenta.getTipoTrans());
			rs.setStockProducto(stockProducto);

			responseStock = actualizarStock(rs);

			if (!responseStock.getStatus().equals("SUCCESS")) {
				message = "Error al actualizar stock de productos";
				status = "ERROR";
				code = HttpStatus.BAD_REQUEST.value();
				logger.info(message);
				throw new Exception(message);
			}

			response.setCode(code);
			response.setMessage(message);
			response.setStatus(status);
			logger.info(message);
		} catch (Exception e) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage(message);
			response.setStatus("ERROR");
			logger.error("Error al registrar venta {}", e);
		}

		return response;

	}

	public List<ResponseLocales> obtenerLocales() {

		List<ResponseLocales> rl = new ArrayList<>();
		try {
			List<Local> locales = new ArrayList<>();
			locales = localRepository.locales();

			if (!locales.isEmpty()) {
				for (Local l : locales) {
					ResponseLocales response = new ResponseLocales();
					response.setIdLocal(l.getIdLocal());
					response.setNombre(l.getNombre());
					response.setProvincia(l.getProvincia());
					response.setFechaCreacion(l.getFechaCreacion());
					response.setFechaActualizacion(l.getFechaActualizacion());
					rl.add(response);
				}
			}

		} catch (Exception e) {
			logger.error("Error al obtener locales {}", e);
		}

		return rl;
	}

	public List<ResponseProductos> obtenerProductos() {

		List<ResponseProductos> rp = new ArrayList<>();

		try {
			List<Productos> productos = new ArrayList<>();
			productos = productosRepository.productos();

			if (!productos.isEmpty()) {
				for (Productos p : productos) {
					ResponseProductos response = new ResponseProductos();
					response.setIdProducto(p.getIdProducto());
					response.setNombre(p.getNombre());
					response.setDescripcion(p.getDescripcion());
					response.setCodigoBarras(p.getCodigoBarras());
					response.setPvp(p.getPvp());
					response.setFechaCreacion(p.getFechaCreacion());
					response.setFechaActualizacion(p.getFechaActualizacion());
					rp.add(response);
				}
			}

		} catch (Exception e) {
			logger.error("Error al obtener productos {}", e);
		}

		return rp;
	}

	public List<ResponseProductosLocal> obtenerProductosLocal(Integer idLocal) {

		List<ResponseProductosLocal> rpl = new ArrayList<>();

		try {
			List<LocalProducto> productosLocal = new ArrayList<>();
			productosLocal = localProductoRepository.productosLocal(idLocal);

			if (!productosLocal.isEmpty()) {
				for (LocalProducto lp : productosLocal) {
					ResponseProductosLocal response = new ResponseProductosLocal();
					response.setIdProducto(lp.getIdProducto().getIdProducto());
					response.setNombre(lp.getIdProducto().getNombre());
					response.setDescripcion(lp.getIdProducto().getDescripcion());
					response.setCodigoBarras(lp.getIdProducto().getCodigoBarras());
					response.setPvp(lp.getIdProducto().getPvp());
					response.setStock(lp.getStock());
					rpl.add(response);
				}
			}

		} catch (Exception e) {
			logger.error("Error al obtener productos del local: " + idLocal, e);
		}

		return rpl;
	}

	public List<ResponseTransacciones> obtenerTransacciones() {

		List<ResponseTransacciones> rt = new ArrayList<>();

		try {
			List<Transacciones> transacciones = new ArrayList<>();
			transacciones = transaccionesRepository.transacciones();

			if (!transacciones.isEmpty()) {
				for (Transacciones t : transacciones) {
					ResponseTransacciones response = new ResponseTransacciones();
					response.setIdTransaccion(t.getIdTransaccion());
					response.setIdLocal(t.getIdLocal().getIdLocal());
					response.setTipoTrans(t.getTipo());
					response.setFechaCreacion(t.getFechaCreacion());
					response.setFechaActualizacion(t.getFechaActualizacion());
					response.setEstado(t.getEstado());

					List<ResponseProductos> productos = new ArrayList<>();
					List<TransaccionProducto> tp = new ArrayList<>();
					tp = transaccionProductoRepository.productosTrans(t.getIdTransaccion());
					if (!tp.isEmpty()) {
						for (TransaccionProducto productoTrans : tp) {
							ResponseProductos p = new ResponseProductos();
							p.setIdProducto(productoTrans.getIdProducto().getIdProducto());
							p.setNombre(productoTrans.getIdProducto().getNombre());
							p.setDescripcion(productoTrans.getIdProducto().getDescripcion());
							p.setCodigoBarras(productoTrans.getIdProducto().getCodigoBarras());
							p.setPvp(productoTrans.getIdProducto().getPvp());
							p.setFechaCreacion(productoTrans.getIdProducto().getFechaCreacion());
							p.setFechaActualizacion(productoTrans.getIdProducto().getFechaActualizacion());
							productos.add(p);
						}
					}
					response.setProductos(productos);
					rt.add(response);
				}
			}

		} catch (Exception e) {
			logger.error("Error al obtener transacciones {}", e);
		}

		return rt;
	}

	public ResponseTransacciones obtenerTransaccion(Integer idTrans) {

		ResponseTransacciones response = new ResponseTransacciones();

		try {
			Transacciones transaccion = new Transacciones();
			transaccion = transaccionesRepository.existeTransaccion(idTrans);

			if (transaccion != null) {
				response.setIdTransaccion(transaccion.getIdTransaccion());
				response.setIdLocal(transaccion.getIdLocal().getIdLocal());
				response.setTipoTrans(transaccion.getTipo());
				response.setFechaCreacion(transaccion.getFechaCreacion());
				response.setFechaActualizacion(transaccion.getFechaActualizacion());
				response.setEstado(transaccion.getEstado());

				List<ResponseProductos> productos = new ArrayList<>();
				List<TransaccionProducto> tp = new ArrayList<>();
				tp = transaccionProductoRepository.productosTrans(idTrans);
				if (!tp.isEmpty()) {
					for (TransaccionProducto productoTrans : tp) {
						ResponseProductos p = new ResponseProductos();
						p.setIdProducto(productoTrans.getIdProducto().getIdProducto());
						p.setNombre(productoTrans.getIdProducto().getNombre());
						p.setDescripcion(productoTrans.getIdProducto().getDescripcion());
						p.setCodigoBarras(productoTrans.getIdProducto().getCodigoBarras());
						p.setPvp(productoTrans.getIdProducto().getPvp());
						p.setFechaCreacion(productoTrans.getIdProducto().getFechaCreacion());
						p.setFechaActualizacion(productoTrans.getIdProducto().getFechaActualizacion());
						productos.add(p);
					}
				}
				response.setProductos(productos);
			}

		} catch (Exception e) {
			logger.error("Error al obtener transaccion {}", e);
		}

		return response;
	}

}
