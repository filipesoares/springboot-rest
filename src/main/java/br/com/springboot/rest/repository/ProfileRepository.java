package br.com.springboot.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.rest.model.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long>{

}
