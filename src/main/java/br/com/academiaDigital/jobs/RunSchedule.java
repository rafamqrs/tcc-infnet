package br.com.academiaDigital.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.academiaDigital.service.FinanceiroService;

@Service
public class RunSchedule {
	@Autowired
	private FinanceiroService financeiroService;
	
	@Scheduled(fixedDelay = 100000)
	public void scheduledJob() {
		System.out.println("Verificando os usuários em atraso");
	}
}
