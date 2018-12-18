package com.krishna.beans;

import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@Component
public class Response {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String dep;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String desc;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<Document> data;

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Document> getData() {
		return data;
	}

	public void setData(List<Document> data) {
		this.data = data;
	}

}
