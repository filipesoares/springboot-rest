package br.com.springboot.restcrud.model;

import java.util.Calendar;

public interface GenericObject {
	
	int getId();
    
	String getName();
	
	void setCreated(Calendar created);
	
	Calendar getModified();
	
	void setModified(Calendar modified);

}
