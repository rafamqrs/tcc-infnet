package br.com.academiaDigital.controller;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academiaDigital.model.Pagamento;
import br.com.academiaDigital.service.PagamentoService;
import br.com.academiaDigital.vo.PagamentoListVO;

@Controller
@RequestMapping(value = "/protected/pagamento")
public class PagamentoController {

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";
	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private MessageSource messageSource;

	@Value("5")
	private int maxResults;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("pagamentoList");
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(
			@ModelAttribute("pagamento") Pagamento pagamento,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {
		pagamento.setDataCadastro(new Date());
		pagamentoService.save(pagamento);
		return createListAllResponse(page, locale, "message.create.success");
	}

	@RequestMapping(value = "/listarPagamentos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarPagamentos() {
		Locale locale = new Locale("pt_BR");
		return createListAllResponse(0, locale, "");
	}

	@RequestMapping(value = "/{pagamentoId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> delete(
			@PathVariable("pagamentoId") int pagamentoId,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {

		try {
			pagamentoService.delete(pagamentoId);
		} catch (AccessDeniedException e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		return createListAllResponse(page, locale, "message.delete.success");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> update(
			@PathVariable("id") int pagamentoId,
			@RequestBody Pagamento pagamento,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {

		if (pagamentoId != pagamento.getIdPagamento()) {
			return new ResponseEntity<String>("Bad Request",
					HttpStatus.BAD_REQUEST);
		}

		pagamentoService.save(pagamento);

		return createListAllResponse(page, locale, "message.update.success");
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale,
			String messageKey) {
		PagamentoListVO pagamentoList = listAll(page);
		addActionMessageToVO(pagamentoList, locale, messageKey, null);
		return returnListToPagamento(pagamentoList);
	}

	private PagamentoListVO listAll(int page) {
		return pagamentoService.findAll(page, maxResults);
	}

	private ResponseEntity<PagamentoListVO> returnListToPagamento(
			PagamentoListVO pagamentoList) {
		return new ResponseEntity<PagamentoListVO>(pagamentoList, HttpStatus.OK);
	}

	private PagamentoListVO addActionMessageToVO(
			PagamentoListVO pagamentoListVO, Locale locale,
			String actionMessageKey, Object[] args) {
		if (StringUtils.isEmpty(actionMessageKey)) {
			return pagamentoListVO;
		}

		pagamentoListVO.setActionMessage(messageSource.getMessage(
				actionMessageKey, args, null, locale));

		return pagamentoListVO;
	}
}
