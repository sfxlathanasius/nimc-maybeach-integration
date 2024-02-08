package com.seamfix.nimc.maybeach.entities;

import com.seamfix.kyc.microservices.utilities.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SETTING")
public class Setting extends BaseEntity {

	private static final long serialVersionUID = 6429445441262014919L;

	@Column(name = "NAME", nullable = false, length = 256, unique = true)
	private String name;

	@Column(name = "DESCRIPTION", nullable = true, length = 512)
	private String description;

	@Column(name = "VALUE", nullable = false, length = 512)
	private String value;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
