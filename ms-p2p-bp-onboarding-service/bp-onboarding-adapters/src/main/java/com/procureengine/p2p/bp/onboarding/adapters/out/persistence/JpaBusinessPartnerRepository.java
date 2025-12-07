package com.procureengine.p2p.bp.onboarding.adapters.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procureengine.p2p.bp.onboarding.adapters.out.persistence.entity.BusinessPartnerEntity;

public interface JpaBusinessPartnerRepository extends JpaRepository<BusinessPartnerEntity, String> {

	Optional<BusinessPartnerEntity> findByTaxId(String taxId);

	boolean existsByTaxId(String taxId);
}
