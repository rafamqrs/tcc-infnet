package br.com.academiaigital.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academiaDigital.model.Exercicio;
import br.com.academiaDigital.service.ExercicioService;
import br.com.academiaDigital.vo.ExercicioListVO;

@Controller
@RequestMapping(value = "/protected/exercicio")
public class ExercicioController {

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";
	@Autowired
	private ExercicioService service;
	@Autowired
	private MessageSource messageSource;
	@Value("5")
	private int maxResults;


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("exercicioList");
	}
	
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
        return createListAllResponse(page, locale);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("exercicio") Exercicio exercicio,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
    	service.save(exercicio);



        return createListAllResponse(page, locale, "message.create.success");
    }
    
    private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
        return createListAllResponse(page, locale, null);
    }
    
    private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
        ExercicioListVO contactListVO = listAll(page);

        addActionMessageToVO(contactListVO, locale, messageKey, null);

        return returnListToUser(contactListVO);
    }
    
    private ExercicioListVO listAll(int page) {
        return service.findAll(page, maxResults);
    }
    
    private ExercicioListVO addActionMessageToVO(ExercicioListVO exerciciosListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return exerciciosListVO;
        }

        exerciciosListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return exerciciosListVO;
    }
    
    private ResponseEntity<ExercicioListVO> returnListToUser(ExercicioListVO exercicioList) {
        return new ResponseEntity<ExercicioListVO>(exercicioList, HttpStatus.OK);
    }
    

}
