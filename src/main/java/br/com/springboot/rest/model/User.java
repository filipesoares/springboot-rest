package br.com.springboot.rest.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="users")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends AbstractObject implements GenericObject, Serializable{
	
	private String name;
	
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	private String username;
	
	private String password;
	
	private String email;
	
	@ManyToOne( optional=true )
	private Profile profile;
	
	public User() {}
	
	public User(String name, boolean enabled, String username, String password, String email, Profile profile) {
		super();
		this.name = name;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
	}
	
	public User(String name, boolean enabled, String username, String password, String email, Profile profile, LocalDateTime created) {
		super();
		this.name = name;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.created = created;
		this.modified = LocalDateTime.now();
	}
	
	public String getPassword() {
		return password;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
