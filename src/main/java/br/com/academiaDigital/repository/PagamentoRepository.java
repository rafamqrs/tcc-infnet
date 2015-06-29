package br.com.academiaDigital.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.academiaDigital.model.Pagamento;

public interface PagamentoRepository extends
		PagingAndSortingRepository<Pagamento, Integer> {
}
