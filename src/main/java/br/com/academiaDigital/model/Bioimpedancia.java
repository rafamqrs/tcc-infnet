package br.com.academiaDigital.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bioimpedancia")
public class Bioimpedancia {
	@Id
	@GeneratedValue
	private int idBio;
	private Date dataAvaliacao;
	private double peso;
	private double gorduraCorporal;
	private double massaMagra;
	private double massaGorda;
	private double imc;
	private double idadeMetabolica;
	private double aguaCorporal;
	@OneToOne
	@JoinColumn(name = "bioimpedancia")
	private User usuario;

	public double getGorduraCorporal() {
		return gorduraCorporal;
	}

	public void setGorduraCorporal(double gorduraCorporal) {
		this.gorduraCorporal = gorduraCorporal;
	}

	public double getMassaMagra() {
		return massaMagra;
	}

	public void setMassaMagra(double massaMagra) {
		this.massaMagra = massaMagra;
	}

	public double getMassaGorda() {
		return massaGorda;
	}

	public void setMassaGorda(double massaGorda) {
		this.massaGorda = massaGorda;
	}

	public double getAguaCorporal() {
		return aguaCorporal;
	}

	public void setAguaCorporal(double aguaCorporal) {
		this.aguaCorporal = aguaCorporal;
	}

	public int getIdBio() {
		return idBio;
	}

	public void setIdBio(int idBio) {
		this.idBio = idBio;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

	public double getIdadeMetabolica() {
		return idadeMetabolica;
	}

	public void setIdadeMetabolica(double idadeMetabolica) {
		this.idadeMetabolica = idadeMetabolica;
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
