package br.com.springboot.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.rest.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByUsername(String username);
	List<User> findByEmail(String email);

}
