package br.com.springboot.restcrud.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import br.com.springboot.restcrud.model.User;

@Service
public interface IUserService extends IGenericService<User>{
	
	User findByUserName(String username);
	
	Collection<User> findByEmail(String email);

}
