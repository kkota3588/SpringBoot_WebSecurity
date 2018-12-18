package com.krishna.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@Component
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String dep;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}
}
