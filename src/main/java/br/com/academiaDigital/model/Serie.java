package br.com.academiaDigital.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "serie")
public class Serie {

	@Id
	@GeneratedValue
	private int idSerie;
	private String nome;
	private String tipoSerie;
	private boolean enable;
	@ManyToMany
	@JoinTable(name = "exercicio_serie", joinColumns = @JoinColumn(name = "idSerie"), inverseJoinColumns = @JoinColumn(name = "idExercicio"))
	private List<Exercicio> exercicios;
	@OneToMany(mappedBy = "serie")
	private List<Aluno> alunos;
	@javax.persistence.Transient
	private List<String> idExercicios;

	public List<String> getIdExercicios() {
		return idExercicios;
	}

	public void setIdExercicios(List<String> idExercicios) {
		this.idExercicios = idExercicios;
	}

	public Serie() {
	}

	public int getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public String getTipoSerie() {
		return tipoSerie;
	}

	public void setTipoSerie(String tipoSerie) {
		this.tipoSerie = tipoSerie;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
