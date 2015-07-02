package br.com.academiaDigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/protected/biopendancia")
public class BiopendanciaController {

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

	@Value("5")
	private int maxResults;

	@Autowired
	private MessageSource messageSource;

	public BiopendanciaController() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView view = new ModelAndView("biopendanciaList");
		return view;
	}
}
