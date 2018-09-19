package com.negocio.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.negocio.model.JsonApiBodyRequest;
import com.negocio.model.RegistrarRequest;
import com.negocio.repository.NegocioRepository;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-17T19:49:20.948Z")

@Controller
public class ListarApiController implements ListarApi {

	@Autowired
	NegocioRepository negocioRepository;

	private static final Logger log = LoggerFactory.getLogger(ListarApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public ListarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<?> listarGet() {
		System.out.println("entro a listar negocios");
		String accept = request.getHeader("Accept");
		JsonApiBodyRequest body = new JsonApiBodyRequest();
		if (accept != null && accept.contains("application/json")) {
			List<RegistrarRequest> lista = (List<RegistrarRequest>) negocioRepository.findAll();
			body.setNegocio(lista);
			System.out.println("devuelve: "+body);
			return new ResponseEntity<JsonApiBodyRequest>(body, HttpStatus.OK);
		}

		return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<?> listarIdadminIdadminGet(
			@ApiParam(value = "Id de la persona dueña del negocio", required = true) @PathVariable("idadmin") String idadmin) {
		String accept = request.getHeader("Accept");
		JsonApiBodyRequest body = new JsonApiBodyRequest();
		if (accept != null && accept.contains("application/json")) {
			List<RegistrarRequest> lista = (List<RegistrarRequest>) negocioRepository.findByIdadmin(idadmin);
			body.setNegocio(lista);

			return new ResponseEntity<JsonApiBodyRequest>(body, HttpStatus.OK);
		}

		return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<JsonApiBodyRequest> listarIdnegocioGet(
			@ApiParam(value = "ID del negocio", required = true) @PathVariable("idnegocio") String idnegocio) {
		String accept = request.getHeader("Accept");

		if (accept != null && accept.contains("application/json")) {
			JsonApiBodyRequest body = new JsonApiBodyRequest();
			List<RegistrarRequest> listabyNegocio = (List<RegistrarRequest>) negocioRepository
					.findByIdnegocio(idnegocio);
			body.setNegocio(listabyNegocio);
			return new ResponseEntity<JsonApiBodyRequest>(body, HttpStatus.OK);
		}

		return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<JsonApiBodyRequest> listarTiponegocioTiponegocioGet(
			@ApiParam(value = "Tipo de negocio", required = true) @PathVariable("tiponegocio") String tiponegocio) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			JsonApiBodyRequest body = new JsonApiBodyRequest();
			List<RegistrarRequest> listatipo = (List<RegistrarRequest>) negocioRepository.findByTipo(tiponegocio);	
			body.setNegocio(listatipo);
			return new ResponseEntity<JsonApiBodyRequest>(body, HttpStatus.OK);
		}

		return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
	}


}
