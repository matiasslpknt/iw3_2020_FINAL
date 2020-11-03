package ar.edu.iua.rest;

import ar.edu.iua.business.IVentaBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Ingrediente;
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.Venta;
import ar.edu.iua.model.VentaFechaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = Constantes.URL_VENTAS)
public class VentaRestController extends BaseRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IVentaBusiness ventaBusiness;

	@GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> list() {
		try {
			return new ResponseEntity<List<Venta>>(ventaBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Venta venta) {
		try {
			ventaBusiness.save(venta);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_VENTAS + "/" + venta.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Venta venta) {
		try {
			ventaBusiness.save(venta);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> load(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Venta>(ventaBusiness.load(id),HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Venta>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Venta>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			ventaBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

    @GetMapping(value = "/ventas_por_pagina")
    public Page<Venta> loadByPage(Pageable pageable) {
            return ventaBusiness.findAllPage(pageable);
    }

	@GetMapping(value = "/precios")
	public ResponseEntity<List<Venta>> loadByIngredienteListDescripcionContains(@RequestParam("precio") Double precio) {
		try {
			return new ResponseEntity<List<Venta>>(ventaBusiness.findByProductoListPrecioLista(precio), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Venta>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/fechas")
	public ResponseEntity<List<Venta>> loadByFecha(@RequestParam("fecha") String fecha) {
		try {
			return new ResponseEntity<List<Venta>>(ventaBusiness.findByFecha(fecha), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Venta>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/fechas_modo")
	public ResponseEntity<List<Venta>> loadByFechaMayorOMenor(@RequestParam("fecha") String fecha, @RequestParam("modo") String modo) {
		try {
			return new ResponseEntity<List<Venta>>(ventaBusiness.loadByFechaMayorOMenor(fecha, modo), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Venta>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/fecha_venta_nombre_producto")
	public ResponseEntity<List<VentaFechaDTO>> getFechaVentasConProductoNombre(@RequestParam("nombre") String nombre) {
		try {
			return new ResponseEntity<List<VentaFechaDTO>>(ventaBusiness.getFechaVentasConProductoNombre(nombre), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<VentaFechaDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e){
			return new ResponseEntity<List<VentaFechaDTO>>(HttpStatus.NOT_FOUND);
		}
	}
}
