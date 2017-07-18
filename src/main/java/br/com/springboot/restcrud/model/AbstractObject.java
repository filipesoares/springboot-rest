package br.com.springboot.restcrud.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class AbstractObject implements GenericObject{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition="DATETIME", updatable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="America/Sao_Paulo", locale="pt-BR")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar created;
	
	@Column(columnDefinition="DATETIME", insertable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="America/Sao_Paulo", locale="pt-BR")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modified;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Calendar getCreated() {
		return created;
	}
	
	public void setCreated(Calendar created) {
		this.created = created;
	}
	
	public Calendar getModified() {
		return modified;
	}
	
	public void setModified(Calendar modified) {
		this.modified = modified;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractObject other = (AbstractObject) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
