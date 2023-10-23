package org.serratec.projetofinal.ApiRestful.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ EmailException.class, SenhaException.class })
	protected ResponseEntity<?> handleEmailException(Exception ex) {
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}

}
