package br.com.academiaDigital.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.academiaDigital.model.Exercicio;

public interface ExercicioRepository extends PagingAndSortingRepository<Exercicio, Integer> {
	
}