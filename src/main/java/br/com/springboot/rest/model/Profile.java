package br.com.springboot.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="profile")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile extends AbstractObject implements GenericObject, GrantedAuthority, Serializable{

	private String name;
	
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean enabled;

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
	public String getAuthority() {
		return this.name;
	}
	
}
