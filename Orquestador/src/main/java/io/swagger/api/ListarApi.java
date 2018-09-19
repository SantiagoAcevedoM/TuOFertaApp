/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.JsonApiBodyRequest;
import io.swagger.model.JsonApiBodyRequest2;
import io.swagger.model.JsonApiBodyRequestNegocio;
import io.swagger.model.JsonApiBodyRequestOferta;
import io.swagger.model.JsonApiBodyResponseErrors;
import io.swagger.model.JsonApiBodyResponseErrors2;
import io.swagger.model.JsonApiBodyResponseSuccess;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-24T20:51:44.531Z")

@Api(value = "listar", description = "the listar API")
public interface ListarApi {

    @ApiOperation(value = "Listado de negocios", nickname = "listarNegociosGet", notes = "Listado de negocios en la tabla personas de la base de datos.", response = JsonApiBodyRequest.class, tags={ "negocios", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = JsonApiBodyRequestNegocio.class),
        @ApiResponse(code = 404, message = "Negocios no encontrado", response = JsonApiBodyResponseErrors.class) })
    @RequestMapping(value = "/listar/negocios",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<JsonApiBodyRequestNegocio> listarNegociosGet();


    @ApiOperation(value = "Listado de ofertas", nickname = "listarOfertasGet", notes = "Listado de ofertas en la tabla ofertas de la base de datos.", response = JsonApiBodyRequest.class, tags={ "ofertas", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = JsonApiBodyRequestOferta.class),
        @ApiResponse(code = 404, message = "Oferta no encontrada(s)", response = JsonApiBodyResponseErrors.class) })
    @RequestMapping(value = "/listar/ofertas",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<JsonApiBodyRequestOferta> listarOfertasGet();


    @ApiOperation(value = "Listado de personas", nickname = "listarPersonasPost", notes = "Listado de personas en la tabla personas de la base de datos", response = JsonApiBodyResponseSuccess.class, tags={ "personas", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = JsonApiBodyResponseSuccess.class),
        @ApiResponse(code = 404, message = "Persona(s) no encontrada(s)", response = JsonApiBodyResponseErrors2.class) })
    @RequestMapping(value = "/listar/personas",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<?> listarPersonasPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest2 body);


    @ApiOperation(value = "Listado de negocios", nickname = "listarIdadminIdadminGet", notes = "Listado de negocios por idadmin en la tabla negociso de la base de datos.", response = JsonApiBodyRequest.class, tags={ "negocios", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = JsonApiBodyRequest.class),
        @ApiResponse(code = 404, message = "Negocios no encontrado", response = JsonApiBodyResponseErrors.class) })
    @RequestMapping(value = "/listar/idadmin/{idadmin}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<?> listarIdadminIdadminGet(@ApiParam(value = "Id de la persona dueña del negocio",required=true) @PathVariable("idadmin") String idadmin);

}