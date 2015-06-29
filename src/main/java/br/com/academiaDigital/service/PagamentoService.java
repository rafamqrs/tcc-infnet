package br.com.academiaDigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academiaDigital.model.Pagamento;
import br.com.academiaDigital.repository.PagamentoRepository;
import br.com.academiaDigital.vo.PagamentoListVO;

@Service
@Transactional
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Transactional(readOnly = true)
	public PagamentoListVO findAll(int page, int maxResults) {

		Page<Pagamento> result = executeQueryFindAll(page, maxResults);

		if (shouldExecuteSameQueryInLastPage(page, result)) {
			int lastPage = result.getTotalPages() - 1;
			result = executeQueryFindAll(lastPage, maxResults);
		}

		return buildResult(result);
	}
	
	public Pagamento findByid(int id){
		Pagamento pagamento = pagamentoRepository.findOne(id);
		return pagamento;
	}

	private PagamentoListVO buildResult(Page<Pagamento> result) {
		return new PagamentoListVO(result.getTotalPages(),
				result.getTotalElements(), result.getContent());
	}

	private Page<Pagamento> executeQueryFindAll(int page, int maxResults) {
		final PageRequest pageRequest = new PageRequest(page, maxResults,
				sortByNameASC());

		return pagamentoRepository.findAll(pageRequest);
	}

	private Sort sortByNameASC() {
		return new Sort(Sort.Direction.ASC, "formaPagamento");
	}

	private boolean shouldExecuteSameQueryInLastPage(int page,
			Page<Pagamento> result) {
		return isPagamentoAfterOrOnLastPage(page, result)
				&& hasDataInDataBase(result);
	}

	private boolean hasDataInDataBase(Page<Pagamento> result) {
		return result.getTotalElements() > 0;
	}

	private boolean isPagamentoAfterOrOnLastPage(int page,
			Page<Pagamento> result) {
		return page >= result.getTotalPages() - 1;
	}

	public void save(Pagamento pagamento) {
		pagamentoRepository.save(pagamento);
	}

	public void delete(int userId) {
		pagamentoRepository.delete(userId);
	}
}
