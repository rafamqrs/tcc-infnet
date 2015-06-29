package br.com.academiaDigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academiaDigital.model.Financeiro;
import br.com.academiaDigital.repository.FinanceiroRepository;
import br.com.academiaDigital.vo.FinanceiroListVO;

@Service
@Transactional
public class FinanceiroService {

	@Autowired
	private FinanceiroRepository financeiroRepository;

	@Transactional(readOnly = true)
	public FinanceiroListVO findAll(int page, int maxResults) {
		Page<Financeiro> result = executeQueryFindAll(page, maxResults);

		if (shouldExecuteSameQueryInLastPage(page, result)) {
			int lastPage = result.getTotalPages() - 1;
			result = executeQueryFindAll(lastPage, maxResults);
		}

		return buildResult(result);

	}

	private FinanceiroListVO buildResult(Page<Financeiro> result) {
		return new FinanceiroListVO(result.getTotalPages(),
				result.getTotalElements(), result.getContent());
	}

	private Page<Financeiro> executeQueryFindAll(int page, int maxResults) {
		final PageRequest pageRequest = new PageRequest(page, maxResults,
				sortByNameASC());

		return financeiroRepository.findAll(pageRequest);
	}

	private Sort sortByNameASC() {
		return new Sort(Sort.Direction.ASC, "dataPagamento");
	}

	private boolean shouldExecuteSameQueryInLastPage(int page,
			Page<Financeiro> result) {
		return isPagamentoAfterOrOnLastPage(page, result)
				&& hasDataInDataBase(result);
	}

	private boolean hasDataInDataBase(Page<Financeiro> result) {
		return result.getTotalElements() > 0;
	}

	private boolean isPagamentoAfterOrOnLastPage(int page,
			Page<Financeiro> result) {
		return page >= result.getTotalPages() - 1;
	}

	public void save(Financeiro financeiro) {
		financeiroRepository.save(financeiro);
	}

	public void delete(int userId) {
		financeiroRepository.delete(userId);
	}
}
