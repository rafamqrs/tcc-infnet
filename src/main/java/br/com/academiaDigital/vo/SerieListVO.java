package br.com.academiaDigital.vo;

import java.util.List;

import br.com.academiaDigital.model.Serie;

public class SerieListVO {

	private int pagesCount;
	private long totalUser;

	private String actionMessage;
	private String searchMessage;

	private List<Serie> series;

	public SerieListVO(int pagesCount, long totalUser, List<Serie> series) {
		super();
		this.pagesCount = pagesCount;
		this.totalUser = totalUser;
		this.series = series;
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

	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}

}
