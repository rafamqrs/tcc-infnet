package br.com.academiaDigital.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academiaDigital.model.User;
import br.com.academiaDigital.service.UserService;
import br.com.academiaDigital.vo.UserListVO;

@Controller
@RequestMapping(value = "/protected/user")
public class UserController {

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@Value("5")
	private int maxResults;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("userList");
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contactUser() {
		return new ModelAndView("user/contact");
	}

	@RequestMapping(value = "/alterarSenha", method = RequestMethod.GET)
	public ModelAndView senhaUser(@ModelAttribute("user") User user) {

		return new ModelAndView("user/senha");
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(
			@ModelAttribute("user") User user,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {
		System.out.println(user.getDataCadastro());
		user.setPassword(gerarSenha());
		userService.save(user);

		return createListAllResponse(page, locale, "message.create.success");
	}

	public String gerarSenha() {
		String senha = RandomStringUtils.randomAlphanumeric(6);
		System.out.println(senha);
		return senha;
	}

	@RequestMapping(value = "/alterarSenha/{newpassword}", method = RequestMethod.PUT)
	public ModelAndView alterarsenhaUser(
			@PathVariable("newpassword") String novaSenha,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String nome = auth.getName();
		User user1 = userService.findByEmail(nome);
		user1.setPassword(novaSenha);
		userService.atualizarSenha(user1);
		return new ModelAndView("user/senha");
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> delete(
			@PathVariable("userId") int contactId,
			@RequestParam(required = false) String searchFor,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
			Locale locale) {

		try {
			userService.delete(contactId);
		} catch (AccessDeniedException e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		return createListAllResponse(page, locale, "message.delete.success");
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
		return createListAllResponse(page, locale);
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
		return createListAllResponse(page, locale, null);
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale,
			String messageKey) {
		UserListVO userListVO = listAll(page);

		addActionMessageToVO(userListVO, locale, messageKey, null);

		return returnListToUser(userListVO);
	}

	private UserListVO listAll(int page) {
		return userService.findAll(page, maxResults);
	}

	private ResponseEntity<UserListVO> returnListToUser(UserListVO userList) {
		return new ResponseEntity<UserListVO>(userList, HttpStatus.OK);
	}

	private UserListVO addActionMessageToVO(UserListVO userListVO,
			Locale locale, String actionMessageKey, Object[] args) {
		if (StringUtils.isEmpty(actionMessageKey)) {
			return userListVO;
		}

		userListVO.setActionMessage(messageSource.getMessage(actionMessageKey,
				args, null, locale));

		return userListVO;
	}
}
