package br.com.academiaDigital.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academiaDigital.model.Aluno;
import br.com.academiaDigital.model.Serie;
import br.com.academiaDigital.service.ExercicioService;
import br.com.academiaDigital.service.SerieService;
import br.com.academiaDigital.service.UserService;
import br.com.academiaDigital.vo.SerieListVO;

@Controller
@RequestMapping(value = "/protected/serie")
public class SerieController {

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

	@Autowired
	private SerieService serieService;

	@Autowired
	private ExercicioService exercicioService;

	@Autowired
	private UserService userService;

	@Value("5")
	private int maxResults;

	@Autowired
	private MessageSource messageSource;

	public SerieController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView view = new ModelAndView("serieList");
		return view;
	}

	@RequestMapping(value = "/listarSerie", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarPagamentos() {
		Locale locale = Locale.getDefault();
		return createListAllResponse(0, locale, "message.create.success");
	}

	@RequestMapping(value = "/criar", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(
			@RequestBody String serie,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) throws JsonParseException, JsonMappingException,
			IOException {
		ObjectMapper mapper = new ObjectMapper();
		Serie serie1 = mapper.readValue(serie, Serie.class);
		Aluno aluno = new Aluno();
		serie1.setDataCadastroSerie(new Date());
		aluno.setSerie(serie1);
		aluno.setUser(userService.findById(serie1.getIdUsuario()));
		userService.criarAluno(aluno);
		serieService.save(serie1);
		return createListAllResponse(0, locale, "message.create.success");
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale,
			String messageKey) {
		SerieListVO serieListVO = listAll(page);

		addActionMessageToVO(serieListVO, locale, messageKey, null);

		return returnListToSerie(serieListVO);
	}

	@RequestMapping(value = "/listarSerie/{idSerie}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarSerie(
			@PathVariable("idSerie") int contactId,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {
		Serie serie = new Serie();
		try {
			serie = serieService.findById(contactId);
		} catch (AccessDeniedException e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Serie>(serie, HttpStatus.OK);
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
