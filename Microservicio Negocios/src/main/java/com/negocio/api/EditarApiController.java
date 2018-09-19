package com.negocio.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.negocio.model.JsonApiBodyRequest;
import com.negocio.model.JsonApiBodyResponseErrors;
import com.negocio.model.JsonApiBodyResponseSuccessNegocio;
import com.negocio.model.RegistrarRequest;
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
public class EditarApiController implements EditarApi {

    private static final Logger log = LoggerFactory.getLogger(EditarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
     NegocioRepository negocioRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public EditarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> editarPut(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	System.out.println(body);
      	negocioRepository.save(body.getNegocio().get(0));
        	RegistrarRequest reg = negocioRepository.findOne(body.getNegocio().get(0).getIdnegocio());
        	reg.setCorreo(body.getNegocio().get(0).getCorreo());
        	reg.setNit(body.getNegocio().get(0).getNit());
        	reg.setNombreNegocio(body.getNegocio().get(0).getNombreNegocio());
        	reg.setCorreo(body.getNegocio().get(0).getCorreo());

            JsonApiBodyResponseSuccessNegocio success = new JsonApiBodyResponseSuccessNegocio();
			success.setIdnegocio(body.getNegocio().get(0).getIdnegocio());
			success.setNombreNegocio(body.getNegocio().get(0).getNombreNegocio());
			success.setTipo(body.getNegocio().get(0).getTipo());
			
			return new ResponseEntity<JsonApiBodyResponseSuccessNegocio>(success, HttpStatus.OK);
        }

        return new ResponseEntity<JsonApiBodyResponseSuccessNegocio>(HttpStatus.NOT_IMPLEMENTED);
    }

}
