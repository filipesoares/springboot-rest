package br.com.springboot.restcrud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="profile")
@Component
public class Profile extends AbstractObject implements GenericObject, Serializable{

	@JsonIgnore
	private static final long serialVersionUID = 1L;	

	@Column
	private String name;
	
	@Column(columnDefinition="TINYINT(1)")
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
	
}
