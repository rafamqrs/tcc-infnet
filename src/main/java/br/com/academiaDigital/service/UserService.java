package br.com.academiaDigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academiaDigital.model.User;
import br.com.academiaDigital.repository.UserRepository;
import br.com.academiaDigital.vo.UserListVO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public UserListVO findAll(int page, int maxResults) {

		Page<User> result = executeQueryFindAll(page, maxResults);

		if (shouldExecuteSameQueryInLastPage(page, result)) {
			int lastPage = result.getTotalPages() - 1;
			result = executeQueryFindAll(lastPage, maxResults);
		}

		return buildResult(result);
	}

	public void atualizarSenha(User user) {
		userRepository.save(user);
	}

	private boolean shouldExecuteSameQueryInLastPage(int page, Page<User> result) {
		return isUserAfterOrOnLastPage(page, result)
				&& hasDataInDataBase(result);
	}

	private boolean isUserAfterOrOnLastPage(int page, Page<User> result) {
		return page >= result.getTotalPages() - 1;
	}

	private boolean hasDataInDataBase(Page<User> result) {
		return result.getTotalElements() > 0;
	}

	private Page<User> executeQueryFindAll(int page, int maxResults) {
		final PageRequest pageRequest = new PageRequest(page, maxResults,
				sortByNameASC());

		return userRepository.findAll(pageRequest);
	}

	private Sort sortByNameASC() {
		return new Sort(Sort.Direction.ASC, "name");
	}

	private UserListVO buildResult(Page<User> result) {
		return new UserListVO(result.getTotalPages(),
				result.getTotalElements(), result.getContent());
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(int contactId) {
		userRepository.delete(contactId);
	}
}
