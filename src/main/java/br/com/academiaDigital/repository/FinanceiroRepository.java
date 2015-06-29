package br.com.academiaDigital.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.academiaDigital.model.Financeiro;

public interface FinanceiroRepository extends PagingAndSortingRepository<Financeiro, Integer> {

}
