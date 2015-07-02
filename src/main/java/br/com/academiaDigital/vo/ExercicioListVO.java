package br.com.academiaDigital.vo;

import java.util.List;

import br.com.academiaDigital.model.Exercicio;

public class ExercicioListVO {

	private int pagesCount;
	private long totalUser;

	private String actionMessage;
	private String searchMessage;

	private List<Exercicio> exercicios;

	public ExercicioListVO(int totalPages, long totalElements,
			List<Exercicio> content) {
		try {
			this.pagesCount = totalPages;
			this.exercicios = content;
			this.totalUser = totalElements;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public long getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(long totalUser) {
		this.totalUser = totalUser;
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

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

}
