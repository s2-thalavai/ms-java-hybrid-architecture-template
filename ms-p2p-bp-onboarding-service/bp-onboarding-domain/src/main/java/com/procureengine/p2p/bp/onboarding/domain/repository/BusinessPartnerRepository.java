package com.procureengine.p2p.bp.onboarding.domain.repository;

import java.util.Optional;

import com.procureengine.p2p.bp.onboarding.domain.model.BusinessPartner;
import com.procureengine.p2p.bp.onboarding.domain.model.value.BusinessPartnerId;

public interface BusinessPartnerRepository {

	BusinessPartner save(BusinessPartner businessPartner);

	Optional<BusinessPartner> findById(BusinessPartnerId id);

	boolean existsByTaxId(String taxId);
}
