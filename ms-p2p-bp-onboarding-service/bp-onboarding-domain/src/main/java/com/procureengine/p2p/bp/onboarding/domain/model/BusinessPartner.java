package com.procureengine.p2p.bp.onboarding.domain.model;

import java.time.Instant;

import com.procureengine.p2p.bp.onboarding.domain.event.BusinessPartnerOnboardingStarted;
import com.procureengine.p2p.bp.onboarding.domain.model.value.BusinessPartnerId;

public class BusinessPartner {

	private final BusinessPartnerId id;
	private String legalName;
	private String taxId;
	private OnboardingStatus status;
	private Instant createdAt;

	private BusinessPartner(BusinessPartnerId id, String legalName, String taxId) {
		this.id = id;
		this.legalName = legalName;
		this.taxId = taxId;
		this.status = OnboardingStatus.INITIATED;
		this.createdAt = Instant.now();
	}

	public static BusinessPartner start(BusinessPartnerId id, String legalName, String taxId) {
		return new BusinessPartner(id, legalName, taxId);
	}

	public BusinessPartnerOnboardingStarted toStartedEvent() {
		return BusinessPartnerOnboardingStarted.create(id.value(), legalName);
	}

	public BusinessPartnerId getId() {
		return id;
	}

	public String getLegalName() {
		return legalName;
	}

	public String getTaxId() {
		return taxId;
	}

	public OnboardingStatus getStatus() {
		return status;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
}
