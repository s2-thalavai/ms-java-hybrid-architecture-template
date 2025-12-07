package com.procureengine.p2p.bp.onboarding.adapters.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "business_partner")
public class BusinessPartnerEntity {

	@Id
	private String id;

	private String legalName;
	private String taxId;

	protected BusinessPartnerEntity() {
	}

	public BusinessPartnerEntity(String id, String legalName, String taxId) {
		this.id = id;
		this.legalName = legalName;
		this.taxId = taxId;
	}

	public String getId() {
		return id;
	}

	public String getLegalName() {
		return legalName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
}
