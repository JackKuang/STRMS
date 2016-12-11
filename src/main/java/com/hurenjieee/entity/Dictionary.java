package com.hurenjieee.entity;
// Generated 2016-12-11 16:59:51 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dictionary generated by hbm2java
 */
@Entity
@Table(name = "dictionary", catalog = "strms")
public class Dictionary implements java.io.Serializable {

	private String uuid;
	private String key;
	private String valueType;
	private String value;
	private String description;
	private Boolean status;

	public Dictionary() {
	}

	public Dictionary(String uuid) {
		this.uuid = uuid;
	}

	public Dictionary(String uuid, String key, String valueType, String value, String description, Boolean status) {
		this.uuid = uuid;
		this.key = key;
		this.valueType = valueType;
		this.value = value;
		this.description = description;
		this.status = status;
	}

	@Id

	@Column(name = "uuid", unique = true, nullable = false, length = 32)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "key")
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "value_type")
	public String getValueType() {
		return this.valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	@Column(name = "value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
