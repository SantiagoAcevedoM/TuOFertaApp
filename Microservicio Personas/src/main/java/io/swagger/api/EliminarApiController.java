package io.swagger.api;

import io.swagger.model.JsonApiBodyRequest;
import io.swagger.model.JsonApiBodyRequest2;
import io.swagger.model.JsonApiBodyResponseErrors;
import io.swagger.model.JsonApiBodyResponseSuccess;
import io.swagger.model.RegistrarRequest;
import io.swagger.utils.FlagsInformation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.api.Repository.UserRepository;

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
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-25T19:32:17.596Z")

@Controller
public class EliminarApiController implements EliminarApi {

	private static final Logger log = LoggerFactory.getLogger(EliminarApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	@Autowired
	UserRepository userRepository;

	FlagsInformation flags = new FlagsInformation();

	@org.springframework.beans.factory.annotation.Autowired
	public EliminarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<?> eliminarIdDelete(
			@ApiParam(value = "body", required = true) @Valid @RequestBody JsonApiBodyRequest2 body) {
		JsonApiBodyResponseErrors responseErrors = new JsonApiBodyResponseErrors();
		JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
		String accept = request.getHeader("Accept");
		RegistrarRequest persona = userRepository.findOne(body.getPersona().get(0).getId());
		List<RegistrarRequest> miToken = userRepository.findByToken(body.getPersona().get(0).getToken());

		if (accept != null && accept.contains("application/json")) {
			if (persona == null) {
				responseErrors.setCodigo(flags.CODE_4002);
				responseErrors.setDetalle(flags.MSN_CODE_4002);
				return new ResponseEntity<JsonApiBodyResponseErrors>(responseErrors, HttpStatus.CONFLICT);
			} else if (body.getPersona().get(0).getId().equals("0")) {
				responseErrors.setCodigo(flags.SUPERADMINMASTER_ELIMINAR_ERROR_CODE);
				responseErrors.setDetalle(flags.SUPERADMINMASTER_ELIMINAR_MSN);
				return new ResponseEntity<JsonApiBodyResponseErrors>(responseErrors, HttpStatus.CONFLICT);
			}

			if (miToken.get(0).getRol().equalsIgnoreCase("superadmin")) {
				responseSuccess.setId(body.getPersona().get(0).getId());
				responseSuccess.setNombre(persona.getNombre());
				responseSuccess.setEstado(persona.getEstado());
				userRepository.delete(body.getPersona().get(0).getId());
				return new ResponseEntity<JsonApiBodyResponseSuccess>(responseSuccess, HttpStatus.OK);

			}else {
				responseErrors.setCodigo(flags.SUPERADMINMASTER_ELIMINAR_ERROR_CODE);
				responseErrors.setDetalle(flags.SUPERADMINMASTER_ELIMINAR_MSN);
				return new ResponseEntity<JsonApiBodyResponseErrors>(responseErrors, HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<JsonApiBodyResponseErrors>(responseErrors, HttpStatus.FAILED_DEPENDENCY);

		}
	}
}
