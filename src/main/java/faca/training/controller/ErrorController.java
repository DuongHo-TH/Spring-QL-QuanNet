package faca.training.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {
	@ExceptionHandler(Exception.class)
	public String exception(Exception exception) {
		System.err.println(exception);
		return "/error/MathError";
	}
	
	@ExceptionHandler({IOException.class, java.sql.SQLException.class}) public ModelAndView handleIOException(Exception ex) {
		ModelAndView model = new ModelAndView("IOError");

		model.addObject("/error/MathError", ex.getMessage());

		return model;
		}

}
