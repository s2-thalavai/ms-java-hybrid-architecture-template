package com.company.p2p.bp.onboarding.application.command.handler;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.procureengine.p2p.bp.onboarding.application.command.StartOnboardingCommand;
import com.procureengine.p2p.bp.onboarding.application.port.in.OnboardBusinessPartnerUseCase;
import com.procureengine.p2p.bp.onboarding.application.port.out.DomainEventPublisher;
import com.procureengine.p2p.bp.onboarding.domain.event.BusinessPartnerOnboardingStarted;
import com.procureengine.p2p.bp.onboarding.domain.model.BusinessPartner;
import com.procureengine.p2p.bp.onboarding.domain.model.value.BusinessPartnerId;
import com.procureengine.p2p.bp.onboarding.domain.repository.BusinessPartnerRepository;

public class StartOnboardingHandler implements OnboardBusinessPartnerUseCase {

	private final BusinessPartnerRepository businessPartnerRepository;
	private final DomainEventPublisher eventPublisher;

	public StartOnboardingHandler(BusinessPartnerRepository businessPartnerRepository,
			DomainEventPublisher eventPublisher) {
		this.businessPartnerRepository = businessPartnerRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	@Transactional
	public String startOnboarding(StartOnboardingCommand command) {

		BusinessPartnerId bpId = new BusinessPartnerId(UUID.randomUUID().toString());

		BusinessPartner bp = BusinessPartner.start(bpId, command.legalName(), command.taxId());

		businessPartnerRepository.save(bp);

		BusinessPartnerOnboardingStarted event = bp.toStartedEvent();
		eventPublisher.publish(event);

		return bpId.value();
	}
}
