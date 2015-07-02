package br.com.academiaDigital.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academiaDigital.model.Serie;
import br.com.academiaDigital.repository.SerieRepository;
import br.com.academiaDigital.vo.SerieListVO;

@Service
@Transactional
public class SerieService {
	@Autowired
	private SerieRepository serieRepository;
	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public SerieListVO findAll(int page, int maxResults) {

		Page<Serie> result = executeQueryFindAll(page, maxResults);

		if (shouldExecuteSameQueryInLastPage(page, result)) {
			int lastPage = result.getTotalPages() - 1;
			result = executeQueryFindAll(lastPage, maxResults);
		}
		return buildResult(result);
	}

	public void save(Serie serie) {
		serieRepository.save(serie);
	}

	public void delete(int serieId) {
		serieRepository.delete(serieId);
	}

	private SerieListVO buildResult(Page<Serie> result) {
		return new SerieListVO(result.getTotalPages(),
				result.getTotalElements(), result.getContent());
	}

	private boolean shouldExecuteSameQueryInLastPage(int page,
			Page<Serie> result) {
		return isUserAfterOrOnLastPage(page, result)
				&& hasDataInDataBase(result);
	}

	private boolean isUserAfterOrOnLastPage(int page, Page<Serie> result) {
		return page >= result.getTotalPages() - 1;
	}

	private boolean hasDataInDataBase(Page<Serie> result) {
		return result.getTotalElements() > 0;
	}

	private Page<Serie> executeQueryFindAll(int page, int maxResults) {
		final PageRequest pageRequest = new PageRequest(page, maxResults,
				sortByNameASC());

		return serieRepository.findAll(pageRequest);
	}

	private Sort sortByNameASC() {
		return new Sort(Sort.Direction.ASC, "nome");
	}

	public List<Serie> findAll() {
		Query query = entityManager.createQuery("SELECT e FROM Serie e");
		List<Serie> serie = (List<Serie>) query.getResultList();
		return serie;
	}

	public Serie findById(int id) {
		return serieRepository.findOne(id);
	}
}
