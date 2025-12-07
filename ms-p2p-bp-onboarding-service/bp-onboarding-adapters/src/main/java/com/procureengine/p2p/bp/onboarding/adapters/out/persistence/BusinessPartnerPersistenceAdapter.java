package com.procureengine.p2p.bp.onboarding.adapters.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.procureengine.p2p.bp.onboarding.adapters.out.persistence.entity.BusinessPartnerEntity;
import com.procureengine.p2p.bp.onboarding.domain.model.BusinessPartner;
import com.procureengine.p2p.bp.onboarding.domain.model.value.BusinessPartnerId;
import com.procureengine.p2p.bp.onboarding.domain.repository.BusinessPartnerRepository;

@Component
public class BusinessPartnerPersistenceAdapter implements BusinessPartnerRepository {

	private final JpaBusinessPartnerRepository jpaRepo;

	public BusinessPartnerPersistenceAdapter(JpaBusinessPartnerRepository jpaRepo) {
		this.jpaRepo = jpaRepo;
	}

	@Override
	public BusinessPartner save(BusinessPartner bp) {
		BusinessPartnerEntity entity = new BusinessPartnerEntity(bp.getId().value(), bp.toStartedEvent().legalName(),
				null);

		jpaRepo.save(entity);

		return bp;
	}

	@Override
	public Optional<BusinessPartner> findById(BusinessPartnerId id) {
		return jpaRepo.findById(id.value()).map(e -> BusinessPartner.start(id, e.getLegalName(), e.getTaxId()));
	}

	@Override
	public boolean existsByTaxId(String taxId) {
		return false;
	}
}
