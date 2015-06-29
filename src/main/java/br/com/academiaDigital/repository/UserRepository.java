package br.com.academiaDigital.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.academiaDigital.model.User;

public interface UserRepository extends
		PagingAndSortingRepository<User, Integer> {
	User findByEmail(String email);
}
