package br.com.academiaDigital.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Exercicio {
	@Id
	@GeneratedValue
	private int idExercicio;
	private String nome;
	private String descricao;
	private int qtdRepeticao;
	private String tipoMuscular;
	private float peso;

	public Exercicio() {
		// TODO Auto-generated constructor stub
	}

	public int getIdExercicios() {
		return idExercicio;
	}

	public void setIdExercicios(int idExercicios) {
		this.idExercicio = idExercicios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQtdRepeticao() {
		return qtdRepeticao;
	}

	public void setQtdRepeticao(int qtdRepeticao) {
		this.qtdRepeticao = qtdRepeticao;
	}

	public String getTipoMuscular() {
		return tipoMuscular;
	}

	public void setTipoMuscular(String tipoMuscular) {
		this.tipoMuscular = tipoMuscular;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}
}
