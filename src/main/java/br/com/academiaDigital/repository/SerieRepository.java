package br.com.academiaDigital.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.academiaDigital.model.Serie;

public interface SerieRepository extends
		PagingAndSortingRepository<Serie, Integer> {
}
