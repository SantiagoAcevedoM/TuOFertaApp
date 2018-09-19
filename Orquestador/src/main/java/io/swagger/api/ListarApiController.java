package io.swagger.api;

import io.swagger.model.JsonApiBodyRequest2;
import io.swagger.model.JsonApiBodyRequestNegocio;
import io.swagger.model.JsonApiBodyRequestOferta;
import io.swagger.model.JsonApiBodyResponseErrors;
import io.swagger.model.JsonApiBodyResponseErrors2;
import io.swagger.model.JsonApiBodyResponseSuccess;
import io.swagger.model.JsonApiBodyResponseSuccessNegocio;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.JsonApiBodyRequest;

import io.swagger.annotations.*;
import io.swagger.model.JsonApiBodyResponseSuccessPersona;
import io.swagger.model.RegistrarRequestNegocio;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-24T20:51:44.531Z")

@Controller
public class ListarApiController implements ListarApi {

    private static final Logger log = LoggerFactory.getLogger(ListarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    //Get para negocios
    @EndpointInject(uri="direct:get-negocios")
    private FluentProducerTemplate producerNegocio;
    
    //Get negocios by admin
    @EndpointInject(uri="direct:get-negocios-by-admin")
    private FluentProducerTemplate producerNegocioByAdmin;
    
    //Get para ofertas
    @EndpointInject(uri="direct:get-ofertas")
    private FluentProducerTemplate producerOferta;
    
    //Get para personas
    @EndpointInject(uri="direct:post-personas")
    private FluentProducerTemplate producerPersonas;


    @org.springframework.beans.factory.annotation.Autowired
    public ListarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        
    }

    public ResponseEntity<JsonApiBodyRequestNegocio> listarNegociosGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	System.out.println("entro al microservicio");
            	JsonApiBodyRequestNegocio negocio = (JsonApiBodyRequestNegocio) producerNegocio.request();
                System.out.println(negocio);
            	return new ResponseEntity<JsonApiBodyRequestNegocio>(negocio, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<JsonApiBodyRequestNegocio>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<JsonApiBodyRequestNegocio>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<JsonApiBodyRequestOferta> listarOfertasGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	JsonApiBodyRequestOferta oferta = (JsonApiBodyRequestOferta) producerOferta.request();
                return new ResponseEntity<JsonApiBodyRequestOferta>(oferta, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<JsonApiBodyRequestOferta>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<JsonApiBodyRequestOferta>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> listarPersonasPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest2 body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Object response = producerNegocio.withBody(body).request();
            JsonApiBodyResponseSuccessPersona exito = new JsonApiBodyResponseSuccessPersona();  
                    JSONObject jsonArray = new JSONObject(response.toString());
                    exito.setId(jsonArray.getString("id"));
                    exito.setEstado(jsonArray.getString("estado"));
                    exito.setNombre(jsonArray.getString("nombre"));
                    return new ResponseEntity<JsonApiBodyResponseSuccessPersona>(exito, HttpStatus.OK);

        }

        return new ResponseEntity<JsonApiBodyResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
    }
    //Listar negocios de un admin
	public ResponseEntity<?> listarIdadminIdadminGet(
			@ApiParam(value = "Id de la persona dueña del negocio", required = true) @PathVariable("idadmin") String idadmin) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
        	JsonApiBodyRequestNegocio negocioadmin = (JsonApiBodyRequestNegocio) producerNegocioByAdmin.withHeader("pathID", idadmin).request();
        	System.out.println(negocioadmin);
			return new ResponseEntity<JsonApiBodyRequestNegocio>(negocioadmin, HttpStatus.OK);
		}

		return new ResponseEntity<JsonApiBodyRequestNegocio>(HttpStatus.NOT_IMPLEMENTED);
	}

}
