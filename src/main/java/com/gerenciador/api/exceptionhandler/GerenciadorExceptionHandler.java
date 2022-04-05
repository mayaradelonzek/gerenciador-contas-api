package com.gerenciador.api.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GerenciadorExceptionHandler extends ResponseEntityExceptionHandler {
	
	MessageSource messageSource;
	
	public GerenciadorExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Erro {
		private String mensagemUsario;
		private String mensagemDesenvolvedor;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			super();
			this.mensagemUsario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
	}
}
