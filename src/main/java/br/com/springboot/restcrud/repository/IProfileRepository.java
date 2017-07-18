package br.com.springboot.restcrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.restcrud.model.Profile;

@Repository
public interface IProfileRepository extends CrudRepository<Profile, Integer>{

}
