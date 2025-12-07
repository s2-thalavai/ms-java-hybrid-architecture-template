package com.procureengine.p2p.bp.onboarding.bootstrap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.p2p.bp.onboarding.application.command.handler.StartOnboardingHandler;
import com.procureengine.p2p.bp.onboarding.application.port.in.OnboardBusinessPartnerUseCase;
import com.procureengine.p2p.bp.onboarding.application.port.out.DomainEventPublisher;
import com.procureengine.p2p.bp.onboarding.domain.repository.BusinessPartnerRepository;

@Configuration
public class BeanConfig {

	@Bean
	public OnboardBusinessPartnerUseCase onboardBusinessPartnerUseCase(
			BusinessPartnerRepository businessPartnerRepository, DomainEventPublisher domainEventPublisher) {
		return new StartOnboardingHandler(businessPartnerRepository, domainEventPublisher);
	}
}
