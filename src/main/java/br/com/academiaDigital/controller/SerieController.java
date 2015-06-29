package br.com.academiaDigital.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academiaDigital.model.Serie;
import br.com.academiaDigital.service.ExercicioService;
import br.com.academiaDigital.service.SerieService;
import br.com.academiaDigital.vo.SerieListVO;

@Controller
@RequestMapping(value = "/protected/serie")
public class SerieController {

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

	@Autowired
	private SerieService serieService;

	@Autowired
	private ExercicioService exercicioService;

	@Value("5")
	private int maxResults;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView view = new ModelAndView("serieList");
		view.addObject("listaExercicio", exercicioService.findAll(0, maxResults));
		view.addObject("serie", new Serie());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(
			@ModelAttribute("serie") Serie serie,
			BindingResult result,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {
		for (int i = 0; i < serie.getIdExercicios().size(); i++) {
			System.out.println(serie.getExercicios().get(i));
		}
		serieService.save(serie);
		return createListAllResponse(page, locale, "message.create.success");
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale,
			String messageKey) {
		SerieListVO serieListVO = listAll(page);

		addActionMessageToVO(serieListVO, locale, messageKey, null);

		return returnListToSerie(serieListVO);
	}

	private SerieListVO addActionMessageToVO(SerieListVO serieListVO,
			Locale locale, String actionMessageKey, Object[] args) {
		if (StringUtils.isEmpty(actionMessageKey)) {
			return serieListVO;
		}

		serieListVO.setActionMessage(messageSource.getMessage(actionMessageKey,
				args, null, locale));

		return serieListVO;
	}

	private ResponseEntity<SerieListVO> returnListToSerie(SerieListVO serieList) {
		return new ResponseEntity<SerieListVO>(serieList, HttpStatus.OK);
	}

	private SerieListVO listAll(int page) {
		return serieService.findAll(page, maxResults);
	}
}
