package com.accenture.api;

import com.accenture.model.JsonApiBodyRequest;
import com.accenture.model.JsonApiBodyResponseErrors;
import com.accenture.model.JsonApiBodyResponseSuccess;
import com.accenture.repository.OfertaRepository;
import com.accenture.util.CopiAndWrite;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-14T12:25:25.106-05:00")

@Controller
public class RegistrarApiController implements RegistrarApi {

    private static final Logger log = LoggerFactory.getLogger(RegistrarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private CopiAndWrite copiarAndWrite;
    @Autowired
    private OfertaRepository oferta_repository;
    
    private JsonApiBodyResponseSuccess exito = new JsonApiBodyResponseSuccess();
    private JsonApiBodyResponseErrors error = new JsonApiBodyResponseErrors();

    @org.springframework.beans.factory.annotation.Autowired
    public RegistrarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> registrarPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	if (body.getOferta().get(0).getValor().isEmpty() || body.getOferta().get(0).getDescuento().isEmpty()
        			|| body.getOferta().get(0).getProducto().isEmpty() || body.getOferta().get(0).getDetalle().isEmpty()){
        			//body.getOferta().get(0).getIdnegocio().equals(null)) {
        		error.setCodigo("002");
        		System.out.println(body);
				error.setDetalle("Faltan campos por completar");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.BAD_REQUEST);
			}
        	try {
        		//body.getOferta().get(0).setId(copiarAndWrite.leer());
        		//copiarAndWrite.escribir();
				oferta_repository.save(body.getOferta().get(0));
				exito.setId("OK");
				exito.setEstado("Oferta registrada exitosamente");
				exito.setProducto(body.getOferta().get(0).getProducto());
				return new ResponseEntity<JsonApiBodyResponseSuccess>(exito, HttpStatus.OK);
			} catch (Exception e) {
				error.setCodigo("001");
				error.setDetalle("Error interno a la hora de registra oferta");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
        	

        }

        return new ResponseEntity<JsonApiBodyResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
    }

}
