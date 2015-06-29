package br.com.academiaDigital.vo;

import java.util.List;

import br.com.academiaDigital.model.Pagamento;
import br.com.academiaDigital.model.User;

public class PagamentoListVO {

	private int pagesCount;
	private long totalPagamento;

	private String actionMessage;
	private String searchMessage;

	private List<Pagamento> pagamentos;

	public PagamentoListVO(int totalPages, long totalElements,
			List<Pagamento> content) {
		this.pagesCount = totalPages;
		this.pagamentos = content;
		this.totalPagamento = totalElements;
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public long getTotalUser() {
		return totalPagamento;
	}

	public void setTotalUser(long totalUser) {
		this.totalPagamento = totalUser;
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

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

}
