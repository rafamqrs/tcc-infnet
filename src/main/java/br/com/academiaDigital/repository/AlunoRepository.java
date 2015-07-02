package br.com.academiaDigital.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.academiaDigital.model.Aluno;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Integer> {

}
