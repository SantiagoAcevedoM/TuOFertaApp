package com.negocio.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.negocio.model.JsonApiBodyResponseErrors;
import com.negocio.model.JsonApiBodyResponseSuccessNegocio;
import com.negocio.repository.NegocioRepository;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-17T19:49:20.948Z")

@Controller
public class EliminarApiController implements EliminarApi {

    private static final Logger log = LoggerFactory.getLogger(EliminarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    NegocioRepository negocioRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public EliminarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<JsonApiBodyResponseSuccessNegocio> eliminarIdnegocioDelete(@ApiParam(value = "ID del negocio",required=true) @PathVariable("idnegocio") String idnegocio) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            JsonApiBodyResponseSuccessNegocio responseSuccess = new JsonApiBodyResponseSuccessNegocio();

			responseSuccess.setNombreNegocio(negocioRepository.findOne(idnegocio).getNombreNegocio());
			responseSuccess.setTipo(negocioRepository.findOne(idnegocio).getTipo());
			responseSuccess.setIdnegocio(idnegocio);
			negocioRepository.delete(idnegocio);


//            	responseSuccess.setId(body.getPersona().get(0).getId());
//				responseSuccess.set(persona.getNombre());
//				responseSuccess.setEstado(persona.getEstado());
       //  	return new ResponseEntity<JsonApiBodyResponseSuccess>(objectMapper.readValue("{  \"idnegocio\" : \"idnegocio\",  \"nombre_negocio\" : \"nombre_negocio\",  \"tipo\" : \"tipo\"}", JsonApiBodyResponseSuccess.class), HttpStatus.NOT_IMPLEMENTED);
			return new ResponseEntity<JsonApiBodyResponseSuccessNegocio>(responseSuccess, HttpStatus.OK);
        }

        return new ResponseEntity<JsonApiBodyResponseSuccessNegocio>(HttpStatus.NOT_IMPLEMENTED);
    }

}
