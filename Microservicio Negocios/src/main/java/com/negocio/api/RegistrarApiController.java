package com.negocio.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.negocio.model.JsonApiBodyRequest;
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
public class RegistrarApiController implements RegistrarApi {

    private static final Logger log = LoggerFactory.getLogger(RegistrarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
	@Autowired
	NegocioRepository negocioRepository;



    @org.springframework.beans.factory.annotation.Autowired
    public RegistrarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<JsonApiBodyResponseSuccessNegocio> registrarPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            //System.out.println(body);
			JsonApiBodyResponseSuccessNegocio responseSuccess = new JsonApiBodyResponseSuccessNegocio();
			responseSuccess.setIdnegocio(body.getNegocio().get(0).getIdnegocio());
			responseSuccess.setNombreNegocio(body.getNegocio().get(0).getNombreNegocio());
			responseSuccess.setTipo(body.getNegocio().get(0).getTipo());

			negocioRepository.save(body.getNegocio().get(0));
			return new ResponseEntity<JsonApiBodyResponseSuccessNegocio>(responseSuccess, HttpStatus.OK);
        }

        return new ResponseEntity<JsonApiBodyResponseSuccessNegocio>(HttpStatus.NOT_IMPLEMENTED);
    }

}
