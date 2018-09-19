package com.accenture.api;

import com.accenture.model.JsonApiBodyRequest;
import com.accenture.model.JsonApiBodyResponseErrors;
import com.accenture.model.JsonApiBodyResponseSuccess;
import com.accenture.model.RegistrarRequest;
import com.accenture.repository.OfertaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-14T12:25:25.106-05:00")

@Controller
public class ListarApiController implements ListarApi {

	private static final Logger log = LoggerFactory.getLogger(ListarApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private OfertaRepository ofertaRepository;

	private JsonApiBodyResponseSuccess exito = new JsonApiBodyResponseSuccess();
	private JsonApiBodyResponseErrors error = new JsonApiBodyResponseErrors();

	@org.springframework.beans.factory.annotation.Autowired
	public ListarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<?> listarOfertas() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				List<RegistrarRequest> ofertas = new ArrayList<>();
				ofertaRepository.findAll().forEach(ofertas::add);
				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
				retorno.setOferta(ofertas);
				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("");
				error.setDetalle("");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		error.setCodigo("");
		error.setDetalle("error de cabezera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// Listar ofertas activas por Negocio
		public ResponseEntity<?> listarOfertasActivas() {
			String accept = request.getHeader("Accept");
			if (accept != null && accept.contains("application/json")) {
				Date fechaActual = new Date();
				Date fechaFin = new Date();
				try {
					List<RegistrarRequest> ofertas;
					List<RegistrarRequest> ofertasVigentes = new ArrayList<RegistrarRequest>();
					// Inicializar variables locales
					ofertas = ofertaRepository.findAll();
					

					for (RegistrarRequest oferta : ofertas) {
						
						fechaActual = obtenerFechaActual();
						fechaFin = ObtenerFechaBD(oferta.getFechaFinal());
						
						if (fechaFin.after(fechaActual)) {
							ofertasVigentes.add(oferta);
						}
					}
					System.out.println("las ofertas son");
					System.out.println(ofertasVigentes);
					JsonApiBodyRequest retorno = new JsonApiBodyRequest();
					retorno.setOferta(ofertasVigentes);
					return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
				} catch (Exception e) {
					error.setCodigo("");
					error.setDetalle("");
					return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			error.setCodigo("");
			error.setDetalle("error de cabecera");
			return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	//Listar ofertas inactivas
	public ResponseEntity<?> listarOfertasInactivas() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			Date fechaActual = new Date();
			Date fechaFin = new Date();
			try {
				List<RegistrarRequest> ofertas;
				List<RegistrarRequest> ofertasVigentes = new ArrayList<RegistrarRequest>();
				// Inicializar variables locales
				ofertas = ofertaRepository.findAll();
				

				for (RegistrarRequest oferta : ofertas) {
					
					fechaActual = obtenerFechaActual();
					fechaFin = ObtenerFechaBD(oferta.getFechaFinal());
					
					if (fechaFin.before(fechaActual)) {
						ofertasVigentes.add(oferta);
					}
				}
				System.out.println("las ofertas son");
				System.out.println(ofertasVigentes);
				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
				retorno.setOferta(ofertasVigentes);
				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("");
				error.setDetalle("");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		error.setCodigo("");
		error.setDetalle("error de cabecera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// Listar ofertas por Negocio
	public ResponseEntity<?> listarOfertasbyNegocio(
			@ApiParam(value = "", required = true) @PathVariable("idnegocio") String idnegocio) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				List<RegistrarRequest> ofertas = new ArrayList<>();
				ofertaRepository.findByIdnegocio(idnegocio).forEach(ofertas::add);
				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
				retorno.setOferta(ofertas);
				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("");
				error.setDetalle("");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		error.setCodigo("");
		error.setDetalle("error de cabecera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// Listar ofertas inactivas por Negocio
	public ResponseEntity<?> listarOfertasInactivasbyNegocio(
			@ApiParam(value = "", required = true) @PathVariable("idnegocio") String idnegocio) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			Date fechaActual = new Date();
			Date fechaFin = new Date();
			try {
				List<RegistrarRequest> ofertas;
				List<RegistrarRequest> ofertasVigentes = new ArrayList<RegistrarRequest>();
				// Inicializar variables locales
				ofertas = ofertaRepository.findByIdnegocio(idnegocio);
				

				for (RegistrarRequest oferta : ofertas) {
					
					fechaActual = obtenerFechaActual();
					fechaFin = ObtenerFechaBD(oferta.getFechaFinal());
					
					if (fechaFin.before(fechaActual)) {
						ofertasVigentes.add(oferta);
					}
				}
				System.out.println("las ofertas son");
				System.out.println(ofertasVigentes);
				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
				retorno.setOferta(ofertasVigentes);
				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("");
				error.setDetalle("");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		error.setCodigo("");
		error.setDetalle("error de cabecera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// Listar ofertas activas por Negocio
	public ResponseEntity<?> listarOfertasActivasbyNegocio(
			@ApiParam(value = "", required = true) @PathVariable("idnegocio") String idnegocio) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			Date fechaActual = new Date();
			Date fechaFin = new Date();
			try {
				List<RegistrarRequest> ofertas;
				List<RegistrarRequest> ofertasVigentes = new ArrayList<RegistrarRequest>();
				// Inicializar variables locales
				ofertas = ofertaRepository.findByIdnegocio(idnegocio);
				

				for (RegistrarRequest oferta : ofertas) {
					
					fechaActual = obtenerFechaActual();
					fechaFin = ObtenerFechaBD(oferta.getFechaFinal());
					
					if (fechaFin.after(fechaActual)) {
						ofertasVigentes.add(oferta);
					}
				}
				System.out.println("las ofertas son");
				System.out.println(ofertasVigentes);
				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
				retorno.setOferta(ofertasVigentes);
				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("");
				error.setDetalle("");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		error.setCodigo("");
		error.setDetalle("error de cabecera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// Listar por tipo oferta
	public ResponseEntity<?> listarOfertasbyTipo(
			@ApiParam(value = "nombre de la oferta asociado a la oferta", required = true) @PathVariable("tipo") String tipo) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				List<RegistrarRequest> ofertas = new ArrayList<>();
				ofertaRepository.findAllByTipo(tipo).forEach(ofertas::add);
				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
				retorno.setOferta(ofertas);
				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("");
				error.setDetalle("");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		error.setCodigo("");
		error.setDetalle("error de cabecera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Listar ofertas por idnegocio y tipo

	public ResponseEntity<?> listarOfertasbyNegocioAndTipo(
			@ApiParam(value = "Id del negocio asociado a la oferta", required = true) @PathVariable("idnegocio") String idnegocio,
			@ApiParam(value = "Id del negocio asociado a la oferta", required = true) @PathVariable("tipo") String tipo) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {

				List<RegistrarRequest> ofertas = new ArrayList<>();
				ofertaRepository.findAllByIdnegocioAndTipo(idnegocio, tipo).forEach(ofertas::add);
				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
				retorno.setOferta(ofertas);
				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("");
				error.setDetalle("");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		error.setCodigo("");
		error.setDetalle("error de cabezera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	//Listar ofertas activas por idnegocio y tipo
	public ResponseEntity<?> listarOfertasActivasbyNegocioAndTipo(
			@ApiParam(value = "Id del negocio asociado a la oferta", required = true) @PathVariable("idnegocio") String idnegocio,
			@ApiParam(value = "Id del negocio asociado a la oferta", required = true) @PathVariable("tipo") String tipo) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				Date fechaActual = new Date();
				Date fechaFin = new Date();
				List<RegistrarRequest> ofertas = new ArrayList<>();
				List<RegistrarRequest> ofertasVigentes = new ArrayList<RegistrarRequest>();

				ofertaRepository.findAllByIdnegocioAndTipo(idnegocio, tipo).forEach(ofertas::add);			
				//
				for (RegistrarRequest oferta : ofertas) {
					
					fechaActual = obtenerFechaActual();
					fechaFin = ObtenerFechaBD(oferta.getFechaFinal());
					
					if (fechaFin.after(fechaActual)) {
						ofertasVigentes.add(oferta);
					}
				}
				//
				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
				retorno.setOferta(ofertasVigentes);
			
				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("");
				error.setDetalle("");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		error.setCodigo("");
		error.setDetalle("error de cabecera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//Listar ofertas activas por idnegocio, tipo y descuento
		public ResponseEntity<?> listarOfertasActivasbyNegocioAndTipoAndDescuento(@ApiParam(value = "Id del negocio asociado a la oferta",required=true) @PathVariable("idnegocio") String idnegocio, 
	    		@ApiParam(value = "tipo del negocio",required=true) @PathVariable("tipo") String tipo, @ApiParam(value = "tipo de descuento",required=true) @PathVariable("descuento") String descuento)

		{
			String accept = request.getHeader("Accept");
			if (accept != null && accept.contains("application/json")) {
				try {
					Date fechaActual = new Date();
					Date fechaFin = new Date();
					List<RegistrarRequest> ofertas = new ArrayList<>();
					List<RegistrarRequest> ofertasVigentes = new ArrayList<RegistrarRequest>();

					ofertaRepository.findAllByIdnegocioAndTipo(idnegocio, tipo).forEach(ofertas::add);					
					//
					for (RegistrarRequest oferta : ofertas) {
						
						fechaActual = obtenerFechaActual();
						fechaFin = ObtenerFechaBD(oferta.getFechaFinal());
						
						if (fechaFin.after(fechaActual)) {
							ofertasVigentes.add(oferta);
						}
					}
					//
					JsonApiBodyRequest retorno = new JsonApiBodyRequest();
					retorno.setOferta(ofertasVigentes);
				
					return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
				} catch (Exception e) {
					error.setCodigo("");
					error.setDetalle("");
					return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			error.setCodigo("");
			error.setDetalle("error de cabecera");
			return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

//		//Listar Descuento
//	    public ResponseEntity<?> listarDescuento(@ApiParam(value = "Id del negocio asociado a la oferta",required=true) @PathVariable("descuento") String descuento){
//			String accept = request.getHeader("Accept");
//			
//	    	if (accept != null && accept.contains("application/json")) {
//				List<RegistrarRequest> ofertas = new ArrayList<>();
//				System.out.println("entro a distinct");
//				System.out.println(descuento);
//				ofertaRepository.findDistinctDescuentoByDescuento(descuento).forEach(ofertas::add);
//				JsonApiBodyRequest retorno = new JsonApiBodyRequest();
//				retorno.setOferta(ofertas);
//				return new ResponseEntity<JsonApiBodyRequest>(retorno, HttpStatus.OK);
//			}
//			error.setCodigo("");
//			error.setDetalle("error de cabecera");
//			return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//
//	    }

	//
	public Date obtenerFechaActual() {
		Date fechaActual = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fechaString = formatter.format(fechaActual);
		try {
			Date date = formatter.parse(fechaString);
			return date;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fechaActual;
	}

	public Date ObtenerFechaBD(String fecha) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(fecha);
			return date;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
