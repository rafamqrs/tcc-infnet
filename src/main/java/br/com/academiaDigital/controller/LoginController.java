package br.com.academiaDigital.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.academiaDigital.model.User;
import br.com.academiaDigital.service.UserService;
import br.com.academiaDigital.util.EnvioEmail;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public ModelAndView doGet() {
        return new ModelAndView("login");
    }
    @RequestMapping(value="/esqueciSenha/{email}", method = RequestMethod.POST)
    public ModelAndView alterarSenha(@PathVariable("email") String email) {
		User user1 = userService.findByEmail(email + ".com");
		if(user1 != null){
			user1.setPassword(gerarSenhaAleatoria());
			userService.atualizarSenha(user1);
			new EnvioEmail().enviarEmail(email, user1.getPassword());
		}
    	return new ModelAndView("login");
    }

	private String gerarSenhaAleatoria() {
		String senha = RandomStringUtils.randomAlphanumeric(6);
		System.out.println(senha);
		return senha;
	}
}
