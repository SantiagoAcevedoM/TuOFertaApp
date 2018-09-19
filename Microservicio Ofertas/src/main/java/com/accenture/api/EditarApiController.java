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
public class EditarApiController implements EditarApi {

    private static final Logger log = LoggerFactory.getLogger(EditarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private OfertaRepository oferta_repository;
    
    private JsonApiBodyResponseSuccess exito= new JsonApiBodyResponseSuccess();
    private JsonApiBodyResponseErrors error = new JsonApiBodyResponseErrors();
    private RegistrarRequest oferta = new RegistrarRequest();

    @org.springframework.beans.factory.annotation.Autowired
    public EditarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> editarPut(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	oferta=oferta_repository.findById(body.getOferta().get(0).getId());
            	if (oferta.equals(null) ||oferta.getId().isEmpty()) {
					error.setCodigo("");
					error.setDetalle("la oferta no existe");
					return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.BAD_REQUEST);
				}
            	if (!body.getOferta().get(0).getIdnegocio().equals(oferta.getIdnegocio())) {
            		error.setCodigo("");
					error.setDetalle("no se puede cambiar el id del negocio");
					return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.BAD_REQUEST);
				}
            	oferta_repository.save(body.getOferta().get(0));
            	exito.setId(oferta.getId());
            	exito.setProducto(oferta.getProducto());
            	exito.setEstado("La oferta fue editada exitosamente");
                return new ResponseEntity<JsonApiBodyResponseSuccess>(exito,HttpStatus.OK);
            } catch (Exception e) {
            	error.setCodigo("");
				error.setDetalle("Error interno");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        error.setCodigo("");
		error.setDetalle("Error en la cabezera");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.BAD_REQUEST);
    }

}
