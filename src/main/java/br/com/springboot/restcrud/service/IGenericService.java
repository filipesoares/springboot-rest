package br.com.springboot.restcrud.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public interface IGenericService<T> {

	T save(T entity);
	
	T update(T entity);
	
	T find(Integer id);
	
	Collection<T> findAll();
	
	void delete(Integer id);
	
}
