package br.com.academiaDigital.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;

public class EnvioEmail {
	
	public boolean enviarEmail(String email, String senha) {
		MandrillApi mandrillApi = new MandrillApi("DWdyrbjsFLNoFKzMSIA1fg");

		// create your message
		MandrillMessage message = new MandrillMessage();
		// add recipients
		List<Recipient> recipients = new ArrayList<Recipient>();
		Recipient recipient;
		recipient = new Recipient();
		recipient.setEmail(email);
		recipients.add(recipient);
		message.setTo(recipients);
		message.setPreserveRecipients(true);
		if(LocaleContextHolder.getLocale().ENGLISH != null){
			message.setHtml("<html><body><h3 style='color:red'>Your NewPassword: </h3>"+ senha + "</body></html>");
		}else{
			message.setHtml("<html><body><h3 style='color:red'>Sua NovaSenha: </h3>"+ senha + "</body></html>");
		}
		message.setFromEmail("suporteacademiadigital@academiadigital.com");
		try {
			MandrillMessageStatus[] messageStatusReports = mandrillApi
					.messages().send(message, false);
			for (int i = 0; i < messageStatusReports.length; i++) {
				String status = messageStatusReports[i].getStatus();
				String id = messageStatusReports[i].getId();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao enviar email" + e.getMessage());
			return false;
		}
	}
}
