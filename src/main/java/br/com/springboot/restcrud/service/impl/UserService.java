package br.com.springboot.restcrud.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.springboot.restcrud.model.User;
import br.com.springboot.restcrud.repository.IUserRepository;
import br.com.springboot.restcrud.service.IUserService;

@Service("userService")
public class UserService implements IUserService, UserDetailsService{
	
	@Autowired
	IUserRepository repository;
	
	// A default password for users without password 
	private static final String defaultPassword = "password";

	@Override
	public User save(User entity) {
		
		try{
			/*
			 * If password has informed then crypt else crypt default password 
			 */
			if(entity.getPassword()!=null){
				entity.setPassword(cryptEncoder(entity.getPassword()));
			}else{
				entity.setPassword(cryptEncoder(defaultPassword));
			}
			
			entity.setCreated(Calendar.getInstance());
			entity.setModified(Calendar.getInstance());
		} catch (Exception e) {
			//TODO handle Exception 
			e.printStackTrace();
		}
		
		return repository.save(entity);
	}

	@Override
	public User update(User entity) {
		
		try{
			if(entity.getPassword()!=null){
				if(!entity.getPassword().equals(find(entity.getId()).getPassword()))
					entity.setPassword(cryptEncoder(entity.getPassword()));
			}else{
				entity.setPassword(cryptEncoder(defaultPassword));
			}
			
			entity.setModified(Calendar.getInstance());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return repository.save(entity);
	}

	@Override
	public User find(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public Collection<User> findAll() {
		return (Collection<User>) repository.findAll();
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}
	
	protected String cryptEncoder(String parameter) throws NoSuchAlgorithmException{
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(parameter);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try{
			User user = findByUserName(username);
	        if(user==null){
	            throw new UsernameNotFoundException("Username not found");
	        }
	            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), 
	                 user.isEnabled(), true, true, true, getGrantedAuthorities(user));
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getProfile().getName()));
        return authorities;
    }

	@Override
	public User findByUserName(String username) {
		return repository.findByUserName(username).get(0);
	}

	@Override
	public Collection<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
