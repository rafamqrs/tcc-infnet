package br.com.academiaDigital.vo;

import java.util.List;

import br.com.academiaDigital.model.User;

public class UserListVO {

	private int pagesCount;
	private long totalUser;

	private String actionMessage;
	private String searchMessage;

	private List<User> users;

	public UserListVO(int totalPages, long totalElements, List<User> content) {
        this.pagesCount = totalPages;
        this.users = content;
        this.totalUser = totalElements;
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public long getTotalContacts() {
		return totalUser;
	}

	public void setTotalContacts(long totalContacts) {
		this.totalUser = totalContacts;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
