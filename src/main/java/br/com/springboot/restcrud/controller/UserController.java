package br.com.springboot.restcrud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.springboot.restcrud.model.User;
import br.com.springboot.restcrud.service.IUserService;

@RestController
public class UserController {

	@Autowired
	@Qualifier("userService")
	IUserService service;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> list() {
		List<User> users = (List<User>) service.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> fetch(@PathVariable("id") int id){
		try{
			User user = service.find(Integer.valueOf(id));
			if(user!=null){
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}else{
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_GATEWAY);
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		try {
			if ( !user.getEmail().equals("") && !service.findByEmail(user.getEmail()).isEmpty()) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			service.save(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_GATEWAY);
		}
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody User user, HttpServletRequest request) {
		try {
			User actual = service.find(Integer.valueOf(user.getId())); 
			if ( actual == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			User updated = service.update(user);
			return new ResponseEntity<User>(updated, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(user, HttpStatus.BAD_GATEWAY);
		}
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable("id") int id) {
		try{
			if (service.find(Integer.valueOf(id)) == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			service.delete(Integer.valueOf(id));
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_GATEWAY);
		}
	}
}
