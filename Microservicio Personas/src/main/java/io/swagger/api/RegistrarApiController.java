package io.swagger.api;

import io.swagger.model.JsonApiBodyRequest;
import io.swagger.model.JsonApiBodyResponseErrors;
import io.swagger.model.JsonApiBodyResponseSuccess;
import io.swagger.model.RegistrarRequest;
import io.swagger.utils.FlagsInformation;
import io.swagger.utils.Validar;

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
import org.springframework.web.bind.annotation.RequestMapping;
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
public class RegistrarApiController implements RegistrarApi {

	@Autowired
	UserRepository userRepository;

	FlagsInformation flags = new FlagsInformation();

	private static final Logger log = LoggerFactory.getLogger(RegistrarApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public RegistrarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<?> registrarPost(
			@ApiParam(value = "body", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
		JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
		JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
		JsonApiBodyRequest request = new JsonApiBodyRequest();
		request = body;

		String nombre = body.getPersona().get(0).getNombre();
		String apellido = body.getPersona().get(0).getApellidos();
		String estado = body.getPersona().get(0).getEstado();
		String id = body.getPersona().get(0).getId();
		String correo = body.getPersona().get(0).getCorreo();
		String rol = body.getPersona().get(0).getRol();
		String contrasena = body.getPersona().get(0).getContrasena();
		String telefono = body.getPersona().get(0).getTelefono();
		String genero = body.getPersona().get(0).getGenero();

		// Lista para validacion registro por correo existente
		List<RegistrarRequest> lista = (List<RegistrarRequest>) userRepository
				.findByCorreo(request.getPersona().get(0).getCorreo());
		// traer ID de la persona para verificar que no esté registrado
		//RegistrarRequest persona_registro = userRepository.findOne(body.getPersona().get(0).getId());
		
			if (lista.size() == 0) {
				//if (request.getPersona().get(0).getRol().equalsIgnoreCase("superadmin")
				//		|| request.getPersona().get(0).getId().equalsIgnoreCase("1")) {
				if (request.getPersona().get(0).getRol().equalsIgnoreCase("superadmin")) {
					responseError.setCodigo(flags.SUPERADMINMASTER_ERROR_CODE);
					responseError.setDetalle(flags.SUPERADMINMASTER_ERROR_MSN);
					return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);
				} else if (body.getPersona().get(0).getNombre().equals(" ")
						|| body.getPersona().get(0).getApellidos().equals("")
						|| body.getPersona().get(0).getContrasena().equals("")
						|| body.getPersona().get(0).getRol().equals("")
						|| body.getPersona().get(0).getEstado().equals("")) {
					responseError.setCodigo(flags.CODE_4001);
					responseError.setDetalle(flags.MSN_CODE_4001);
					return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);

				} else if (!Validar.validarCorreo(correo)) {
					responseError.setCodigo("123");
					responseError.setDetalle("El correo no cumple con los requisitos de aceptacion");
					return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);

				} else if (nombre.startsWith(" ", 0) || !Validar.validarLetras(nombre)) {
					responseError.setCodigo(flags.CODE_0001);
					responseError.setDetalle(flags.MSN_CODE_0001);
					return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);
				} else if (apellido.startsWith(" ", 0) || !Validar.validarLetras(apellido)) {
					responseError.setCodigo(flags.CODE_0002);
					responseError.setDetalle(flags.MSN_CODE_0002);
					return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);
				}

				else {
					// Verificar que el rol sea valido
					if (body.getPersona().get(0).getRol().equalsIgnoreCase("Administrador")
							|| body.getPersona().get(0).getRol().equalsIgnoreCase("Usuario")
							|| body.getPersona().get(0).getRol().equalsIgnoreCase("SuperAdmin")) {
						if (body.getPersona().get(0).getRol().equalsIgnoreCase("Administrador")) {
							// REGISTRO DE USUARIOS ADMIN
							body.getPersona().get(0).setEstado("Activo");
							userRepository.save(body.getPersona().get(0));
							responseSuccess.setId(request.getPersona().get(0).getId());
							responseSuccess.setNombre(request.getPersona().get(0).getNombre());
							responseSuccess.setEstado(request.getPersona().get(0).getEstado());
							return new ResponseEntity<JsonApiBodyResponseSuccess>(responseSuccess, HttpStatus.OK);
						} else {
							// REGISTRO DE USUARIOS COMUNES
							//RegistrarRequest persona = userRepository.save(body.getPersona().get(0));
							userRepository.save(body.getPersona().get(0));
							responseSuccess.setId(request.getPersona().get(0).getId());
							responseSuccess.setNombre(request.getPersona().get(0).getNombre());
							responseSuccess.setEstado(request.getPersona().get(0).getEstado());
							return new ResponseEntity<JsonApiBodyResponseSuccess>(responseSuccess, HttpStatus.OK);

						}
					} else {
						responseError.setCodigo(flags.CODE_2002);
						responseError.setDetalle(flags.MSN_CODE_2002);
						return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.OK);
					}

				}
			} else {
				responseError.setCodigo(flags.MSN_CODE_4001);
				responseError.setDetalle(flags.CODE_4001);
				return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.FAILED_DEPENDENCY);
			}

		

	}
}
