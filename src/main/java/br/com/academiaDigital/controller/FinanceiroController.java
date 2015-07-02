package br.com.academiaDigital.controller;

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

import br.com.academiaDigital.model.Financeiro;
import br.com.academiaDigital.model.Pagamento;
import br.com.academiaDigital.model.User;
import br.com.academiaDigital.service.FinanceiroService;
import br.com.academiaDigital.service.PagamentoService;
import br.com.academiaDigital.service.UserService;
import br.com.academiaDigital.vo.FinanceiroListVO;

@Controller
@RequestMapping(value = "/protected/financeiro")
public class FinanceiroController {

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

	@Autowired
	private FinanceiroService financeiroService;
	@Autowired
	private UserService userService;
	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private MessageSource messageSource;

	@Value("5")
	private int maxResults;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("financeiroList");
	}

	@RequestMapping(value = "/listarFinanceiros", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarPagamentos() {
		Locale locale = new Locale("pt_BR");
		return createListAllResponse(0, locale, "");
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(
			@ModelAttribute("financeiro") Financeiro financeiro,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {
		User usuario = userService.findById(Integer.parseInt(financeiro
				.getIdUsuario()));
		Pagamento pagamento = pagamentoService.findByid(Integer
				.parseInt(financeiro.getIdPagamento()));
		financeiro.setPagamento(pagamento);
		financeiro.setUsuario(usuario);
		financeiroService.save(financeiro);
		return createListAllResponse(page, locale, "message.create.success");
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale,
			String messageKey) {
		FinanceiroListVO financeiroList = listAll(page);
		addActionMessageToVO(financeiroList, locale, messageKey, null);
		return returnListToPagamento(financeiroList);
	}

	private FinanceiroListVO listAll(int page) {
		return financeiroService.findAll(page, maxResults);
	}

	private ResponseEntity<FinanceiroListVO> returnListToPagamento(
			FinanceiroListVO financeiroList) {
		return new ResponseEntity<FinanceiroListVO>(financeiroList,
				HttpStatus.OK);
	}

	private FinanceiroListVO addActionMessageToVO(
			FinanceiroListVO financeiroListVO, Locale locale,
			String actionMessageKey, Object[] args) {
		if (StringUtils.isEmpty(actionMessageKey)) {
			return financeiroListVO;
		}

		financeiroListVO.setActionMessage(messageSource.getMessage(
				actionMessageKey, args, null, locale));

		return financeiroListVO;
	}

}
