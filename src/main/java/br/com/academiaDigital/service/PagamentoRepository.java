package br.com.academiaDigital.service;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.academiaDigital.model.Pagamento;

public interface PagamentoRepository extends PagingAndSortingRepository<Pagamento, Integer> {
	Pagamento findByPagamento(String pagamento);
}
