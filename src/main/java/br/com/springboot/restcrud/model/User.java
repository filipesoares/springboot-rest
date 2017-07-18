package br.com.springboot.restcrud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
@Component
public class User extends AbstractObject implements GenericObject, Serializable{
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition="VARCHAR(60)", nullable=true)
	private String name;
	
	@Column(columnDefinition="TINYINT(1)", nullable=false)
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	@Column(columnDefinition="VARCHAR(20)", nullable=false, updatable=false, unique=true)
	private String username;
	
	@Column(columnDefinition="VARCHAR(100)", nullable=false)
	private String password;

	@Column(columnDefinition="VARCHAR(80)", nullable=true)
	private String email;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="profile_id")
	private Profile profile;
	
	// Getters and Setters
	
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
