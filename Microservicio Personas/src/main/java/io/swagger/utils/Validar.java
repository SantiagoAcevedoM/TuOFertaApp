package io.swagger.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

	public static boolean validarCorreo(String correo) {
		// Patrón para validar el email
		boolean valido = false;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        // El email a validar
        String email = correo;
 
        Matcher matcher = pattern.matcher(email);
 
        if (matcher.find() == true) {
        	valido = true;
        } else {
            valido = false;
        }
        return valido;
	}
	
	public static boolean validarNumerico(String value) {
		System.out.println(value);

		return (value.matches("[+-]?\\d*(\\.\\d+)?") && value.equals("")==false);
	}
	
	public static boolean validarLetras(String value) {
		//return (value.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]?") && value.equals("")==false);
		return (value.matches("[a-zA-Z-áéíóúÁÉÍÓÚñÑ]*") && value.equals("")==false);
	}

	public static boolean validarContrasena(String value) {
		return (value.matches("^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$") && value.equals("")==false);
	}  

}


