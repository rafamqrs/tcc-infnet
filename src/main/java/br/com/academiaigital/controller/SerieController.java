package br.com.academiaigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.academiaDigital.service.SerieService;

@Controller
@RequestMapping(value = "/protected/serie")
public class SerieController {

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";
	@Autowired
	private SerieService service;
	
	@Autowired
	private MessageSource messageSource;

	@Value("5")
	private int maxResults;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("serieList");
	}

	
	
}
