package br.com.springboot.rest.model;

import java.time.LocalDateTime;

public interface GenericObject {
	
	Long getId();
	
	LocalDateTime getCreated();
    
	LocalDateTime getModified();

}
