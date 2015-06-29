package br.com.academiaDigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academiaDigital.model.Exercicio;
import br.com.academiaDigital.repository.ExercicioRepository;
import br.com.academiaDigital.vo.ExercicioListVO;

@Service
@Transactional
public class ExercicioService {
	
	@Autowired
	private ExercicioRepository exercicioRepository;

	@Transactional(readOnly = true)
	public ExercicioListVO findAll(int page, int maxResults) {
		Page<Exercicio> result = executeQueryFindAll(page, maxResults);

		if (shouldExecuteSameQueryInLastPage(page, result)) {
			int lastPage = result.getTotalPages() - 1;
			result = executeQueryFindAll(lastPage, maxResults);
		}

		return buildResult(result);
	}

	public void save(Exercicio exercicios) {
		exercicioRepository.save(exercicios);
	}
	
	@Secured("ROLE_ADMIN")
	public void delete(int exercicioId) {
		exercicioRepository.delete(exercicioId);
	}

	private boolean shouldExecuteSameQueryInLastPage(int page,
			Page<Exercicio> result) {
		return isUserAfterOrOnLastPage(page, result)
				&& hasDataInDataBase(result);
	}

	private Page<Exercicio> executeQueryFindAll(int page, int maxResults) {
		final PageRequest pageRequest = new PageRequest(page, maxResults,
				sortByNameASC());
		return exercicioRepository.findAll(pageRequest);
	}

	private Sort sortByNameASC() {
		return new Sort(Sort.Direction.ASC, "descricao");
	}

	private ExercicioListVO buildResult(Page<Exercicio> result) {
		return new ExercicioListVO(result.getTotalPages(),
				result.getTotalElements(), result.getContent());
	}


	private boolean isUserAfterOrOnLastPage(int page, Page<Exercicio> result) {
		return page >= result.getTotalPages() - 1;
	}

	private boolean hasDataInDataBase(Page<Exercicio> result) {
		return result.getTotalElements() > 0;
	}
}
