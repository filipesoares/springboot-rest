package br.com.springboot.rest.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="users")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends AbstractObject implements GenericObject, UserDetails, Serializable{
	
	private String name;
	
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	private String username;
	
	private String password;
	
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Profile> profiles = new ArrayList<>();
	
	public User() {}
	
	public User(String name, boolean enabled, String username, String password, String email, List<Profile> profiles) {
		super();
		this.name = name;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profiles = profiles;
	}
	
	public User(String name, boolean enabled, String username, String password, String email, List<Profile> profiles, LocalDateTime created) {
		this.name = name;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profiles = profiles;
		this.created = created;
		this.modified = LocalDateTime.now();
	}
	
	public String getPassword() {
		return password;
	}
	
	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
}
