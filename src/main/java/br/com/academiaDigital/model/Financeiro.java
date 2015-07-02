package br.com.academiaDigital.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "financeiro")
public class Financeiro {

	@Id
	@GeneratedValue
	private int idFinanceiro;
	private String situacaoFinanceira;
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private long quantidadeDiasEmAtraso;
	@OneToOne
	@JoinColumn(name = "idUsuario")
	private User usuario;
	@Transient
	private String idUsuario;
	@Transient
	private String idPagamento;

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	@OneToOne
	@JoinColumn(name = "idPagamento")
	private Pagamento pagamento;

	public int getIdFinanceiro() {
		return idFinanceiro;
	}

	public void setIdFinanceiro(int idFinanceiro) {
		this.idFinanceiro = idFinanceiro;
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

	public long getQuantidadeDiasEmAtraso() {
		return quantidadeDiasEmAtraso;
	}

	public void setQuantidadeDiasEmAtraso(long quantidadeDiasEmAtraso) {
		this.quantidadeDiasEmAtraso = quantidadeDiasEmAtraso;
	}

	public long calcularDiferencaDatas(String dataInicio, String dataFim) {
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = myFormat.parse(dataInicio);
			Date date2 = myFormat.parse(dataFim);
			long diff = date2.getTime() - date1.getTime();
			return diff;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

}
