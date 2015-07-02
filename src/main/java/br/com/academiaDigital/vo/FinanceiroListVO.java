package br.com.academiaDigital.vo;

import java.util.List;

import br.com.academiaDigital.model.Exercicio;
import br.com.academiaDigital.model.Financeiro;

public class FinanceiroListVO {

	private int pagesCount;
	private long totalFinanceiro;

	private String actionMessage;
	private String searchMessage;

	private List<Financeiro> financeiros;

	public FinanceiroListVO(int totalPages, long totalElements,
			List<Financeiro> content) {
		this.pagesCount = totalPages;
		this.financeiros = content;
		this.totalFinanceiro = totalElements;
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public long getTotalFinanceiro() {
		return totalFinanceiro;
	}

	public void setTotalFinanceiro(long totalUser) {
		this.totalFinanceiro = totalUser;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public String getSearchMessage() {
		return searchMessage;
	}

	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}

	public List<Financeiro> getFinanceiros() {
		return financeiros;
	}

	public void setFinanceiros(List<Financeiro> exercicios) {
		this.financeiros = exercicios;
	}

}
