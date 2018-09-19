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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-25T19:32:17.596Z")

@Controller
public class EditarApiController implements EditarApi {

	private static final Logger log = LoggerFactory.getLogger(EditarApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	UserRepository userRepository;

	FlagsInformation flags = new FlagsInformation();

	@org.springframework.beans.factory.annotation.Autowired
	public EditarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<?> editarPut(
			@ApiParam(value = "body", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
		JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
		JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
		JsonApiBodyRequest request = new JsonApiBodyRequest();
		request = body;
		System.out.println("Entro");
		String nombre = body.getPersona().get(0).getNombre();
		String apellido = body.getPersona().get(0).getApellidos();
		String estado = body.getPersona().get(0).getEstado();
		String id = body.getPersona().get(0).getId();
		String correo = body.getPersona().get(0).getCorreo();
		String rol = body.getPersona().get(0).getRol();
		String contrasena = body.getPersona().get(0).getContrasena();
		System.out.println(body);
		// Lista para validacion registro por correo existente
		List<RegistrarRequest> lista = (List<RegistrarRequest>) userRepository
				.findByCorreo(request.getPersona().get(0).getCorreo());
		// traer ID de la persona para verificar que no esté registrado
		RegistrarRequest persona_registro = userRepository.findOne(body.getPersona().get(0).getId());

		/*
		 * if(!request.getPersona().get(0).getEstado().equalsIgnoreCase("Activo")||
		 * !request.getPersona().get(0).getEstado().equalsIgnoreCase("Pendiente")
		 * ||!request.getPersona().get(0).getEstado().equalsIgnoreCase("Inactivo")) {
		 * System.out.println(request.getPersona().get(0).getEstado());
		 * responseError.setCodigo(flags.CODE_3002);
		 * responseError.setDetalle(flags.MSN_CODE_3002); return new
		 * ResponseEntity<JsonApiBodyResponseErrors>(responseError,
		 * HttpStatus.FAILED_DEPENDENCY);
		 * 
		 * }
		 */
		RegistrarRequest personas = userRepository.findOne(body.getPersona().get(0).getId());
		if (personas == null) {
			System.out.println("error en linea 90");
			responseError.setCodigo(flags.CODE_4003);
			responseError.setDetalle(flags.MSN_CODE_4003);
			return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.FAILED_DEPENDENCY);
		}
		// Evitar que se edite el ROOT
		if (request.getPersona().get(0).getRol().equalsIgnoreCase("SuperAdmin")
				|| request.getPersona().get(0).getId().equalsIgnoreCase("0")) {
			responseError.setCodigo(flags.SUPERADMINMASTER_ERROR_CODE);
			responseError.setDetalle(flags.SUPERADMINMASTER_ERROR_MSN);
			return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);
		}
		if (body.getPersona().get(0).getId() == "" || body.getPersona().get(0).getNombre().equals(" ")
				|| body.getPersona().get(0).getApellidos().equals("")
				|| body.getPersona().get(0).getContrasena().equals("") || body.getPersona().get(0).getRol().equals("")
				|| body.getPersona().get(0).getEstado().equals("")) {
			responseError.setCodigo(flags.CODE_4001);
			responseError.setDetalle(flags.MSN_CODE_4001);
			return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);

		} else if (!Validar.validarCorreo(correo)) {
			responseError.setCodigo(flags.CODE_0003);
			responseError.setDetalle(flags.MSN_CODE_0003);
			return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);
		} else if (!Validar.validarContrasena(contrasena)) {
			System.out.println(contrasena);
			System.out.println(Validar.validarContrasena(contrasena));
			responseError.setCodigo(flags.CODE_0004);
			responseError.setDetalle(flags.MSN_CODE_0004);
			return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);
		} else if (nombre.isEmpty() || !Validar.validarLetras(nombre)) {
			responseError.setCodigo(flags.CODE_0001);
			responseError.setDetalle(flags.MSN_CODE_0001);
			return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);
		} else if (apellido.isEmpty() || !Validar.validarLetras(apellido)) {
			responseError.setCodigo(flags.CODE_0002);
			responseError.setDetalle(flags.MSN_CODE_0002);
			System.out.println("error en linea 127");

			return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.FAILED_DEPENDENCY);
		}

//		List<RegistrarRequest> miToken = userRepository.findByToken(body.getPersona().get(0).getToken());
//
//		if (body.getPersona().get(0).getRol().equalsIgnoreCase("Administrador")
//				&& (miToken.get(0).getToken()).equals(body.getPersona().get(0).getToken())) {
			List<RegistrarRequest> tokenB = userRepository.findByToken(body.getPersona().get(0).getToken());
			
//			if ((miToken.get(0).getId()).equals(body.getPersona().get(0).getId())) {


				// Garantizar que un Admin solo se pueda editar a si mismo
				if ((tokenB.get(0).getToken()).equals(body.getPersona().get(0).getToken())) {
					RegistrarRequest persona = userRepository.save(body.getPersona().get(0));
					responseSuccess.setId(request.getPersona().get(0).getId());
					responseSuccess.setNombre(request.getPersona().get(0).getNombre());
					responseSuccess.setEstado(request.getPersona().get(0).getEstado());
					return new ResponseEntity<JsonApiBodyResponseSuccess>(responseSuccess, HttpStatus.OK);

				}
				// Garantizar que solo el SuperAdmin pueda editar SuperAdmins y Admins
				else if (body.getPersona().get(0).getRol().equalsIgnoreCase("SuperAdmin")
						|| body.getPersona().get(0).getRol().equalsIgnoreCase("Admin")) {
					List<RegistrarRequest> tokenA = userRepository.findByToken("SuperAdmin");
					if ((tokenA.get(0).getToken()).equals(body.getPersona().get(0).getToken())) {
						RegistrarRequest persona = userRepository.save(body.getPersona().get(0));
						responseSuccess.setId(request.getPersona().get(0).getId());
						responseSuccess.setNombre(request.getPersona().get(0).getNombre());
						responseSuccess.setEstado(request.getPersona().get(0).getEstado());
						return new ResponseEntity<JsonApiBodyResponseSuccess>(responseSuccess, HttpStatus.OK);

					}

				} else if (body.getPersona().get(0).getRol().equalsIgnoreCase("Usuario")) {
					RegistrarRequest persona = userRepository.save(body.getPersona().get(0));
					responseSuccess.setId(request.getPersona().get(0).getId());
					responseSuccess.setNombre(request.getPersona().get(0).getNombre());
					responseSuccess.setEstado(request.getPersona().get(0).getEstado());
					return new ResponseEntity<JsonApiBodyResponseSuccess>(responseSuccess, HttpStatus.OK);
				} else {
					RegistrarRequest persona = userRepository.save(body.getPersona().get(0));

					System.out.println(persona);
					responseError.setCodigo(flags.CODE_2003);
					responseError.setDetalle(flags.MSN_CODE_2003);
					System.out.println("error en linea 174");

					return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.FAILED_DEPENDENCY);

				}
//			}else {
//				responseError.setCodigo(flags.CODE_4004);
//				responseError.setDetalle(flags.MSN_CODE_4004);
//				System.out.println("error en linea 181");
//				System.out.println(miToken.get(0).getId());
//				System.out.println(body.getPersona().get(0).getId());
//
//				return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.FAILED_DEPENDENCY);
//			}
//		}else {
//			responseError.setCodigo(flags.CODE_2003);
//			responseError.setDetalle(flags.MSN_CODE_2003);
//			System.out.println("error en linea 190");
//
//			return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.FAILED_DEPENDENCY);
//
//		}
		responseError.setCodigo(flags.CODE_2003);
		responseError.setDetalle(flags.MSN_CODE_2003);
		System.out.println("error en linea 196");

		return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.FAILED_DEPENDENCY);

	}

	/*
	 * responseError.setCodigo(flags.CODE_2003);
	 * responseError.setDetalle(flags.MSN_CODE_2003); return new
	 * ResponseEntity<JsonApiBodyResponseErrors>(responseError,
	 * HttpStatus.FAILED_DEPENDENCY);
	 */
}
