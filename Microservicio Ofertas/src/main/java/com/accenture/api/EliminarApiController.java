package com.accenture.api;

import com.accenture.model.JsonApiBodyRequestDelete;
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
public class EliminarApiController implements EliminarApi {

    private static final Logger log = LoggerFactory.getLogger(EliminarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private OfertaRepository ofertaRepository;
    
    private JsonApiBodyResponseSuccess exito = new JsonApiBodyResponseSuccess();
    private JsonApiBodyResponseErrors error= new JsonApiBodyResponseErrors();

    @org.springframework.beans.factory.annotation.Autowired
    public EliminarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> eliminarDelete(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequestDelete body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	RegistrarRequest existe =ofertaRepository.findById(body.getOferta().get(0).getId()); 
            	//Verificar que la oferta existe
            	if(existe.equals(null) || existe.toString().isEmpty()) {
            		 error.setCodigo("");
                     error.setDetalle("Error interno al eliminar oferta");
                     return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.BAD_REQUEST);          
            	}else {
            		ofertaRepository.delete(body.getOferta().get(0).getId());
                	exito.setId(body.getOferta().get(0).getId());           	
                	exito.setEstado("La oferta fue eliminada exitosamente");
                    return new ResponseEntity<JsonApiBodyResponseSuccess>(exito,HttpStatus.OK);
             
            	}
            	   } catch (Exception e) {
                error.setCodigo("");
                error.setDetalle("Error interno al eliminar oferta");
                return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        error.setCodigo("");
        error.setDetalle("problema de las cabeceras");
        return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
