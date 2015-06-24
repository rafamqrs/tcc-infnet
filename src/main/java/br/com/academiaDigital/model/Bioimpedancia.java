package br.com.academiaDigital.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="bioimpedancia")
public class Bioimpedancia {
	@Id
	@GeneratedValue
	private int idBio;
	private double massaGorda;
	private double massaMagra;
	private double aguaCorporal;
	private Date dataAvaliacao;
	@OneToOne
	@JoinColumn(name="bioimpedancia")
	private User usuario;
	public int getIdBio() {
		return idBio;
	}
	public void setIdBio(int idBio) {
		this.idBio = idBio;
	}
	public double getMassaGorda() {
		return massaGorda;
	}
	public void setMassaGorda(double massaGorda) {
		this.massaGorda = massaGorda;
	}
	public double getMassaMagra() {
		return massaMagra;
	}
	public void setMassaMagra(double massaMagra) {
		this.massaMagra = massaMagra;
	}
	public double getAguaCorporal() {
		return aguaCorporal;
	}
	public void setAguaCorporal(double aguaCorporal) {
		this.aguaCorporal = aguaCorporal;
	}
	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}
	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	
}
