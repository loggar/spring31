package com.loggar.controller.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test/resolver")
public class HandlerExceptionResolverController {
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView nullPointerExceptionHandler(NullPointerException ex) {
		return new ModelAndView("error/nullpointer").addObject("msg", ex.getMessage());
	}
	
	@RequestMapping("/exception/annotation")
	public void exception_annotation() {
		throw new NullPointerException("test_NullPointerException");
	}
	
	@RequestMapping("/exception/simple")
	public void exception_simple() {
		throw new ArithmeticException("ArithmeticException");
	}
}
