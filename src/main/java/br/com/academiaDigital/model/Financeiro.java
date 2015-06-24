package br.com.academiaDigital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="financeiro")
public class Financeiro {

	@Id
	@GeneratedValue
	private int idFinanceiro;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoPagamento")
	private TipoPagamento tipoPagamento;
	private String situacaoFinanceira;
	private Date dataPagamento;
	private Date dataVencimento;
	private int quantidadeDiasEmAtraso;
	@OneToOne
	@JoinColumn(name="idUsuario")
	private User usuario;
	
	public int getIdFinanceiro() {
		return idFinanceiro;
	}
	public void setIdFinanceiro(int idFinanceiro) {
		this.idFinanceiro = idFinanceiro;
	}
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public String getSituacaoFinanceira() {
		return situacaoFinanceira;
	}
	public void setSituacaoFinanceira(String situacaoFinanceira) {
		this.situacaoFinanceira = situacaoFinanceira;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public int getQuantidadeDiasEmAtraso() {
		return quantidadeDiasEmAtraso;
	}
	public void setQuantidadeDiasEmAtraso(int quantidadeDiasEmAtraso) {
		this.quantidadeDiasEmAtraso = quantidadeDiasEmAtraso;
	}
}
