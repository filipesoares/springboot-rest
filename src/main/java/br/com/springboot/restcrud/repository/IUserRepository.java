package br.com.springboot.restcrud.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.springboot.restcrud.model.User;


@Repository
public interface IUserRepository extends CrudRepository<User, Integer>{
	
	@Query("FROM User u WHERE u.username=:username")
	List<User> findByUserName(@Param("username") String username);
	
	@Query("FROM User u WHERE u.email=:email")
	Collection<User> findByEmail(@Param("email") String email);

}
