package com.procureengine.p2p.bp.onboarding.adapters.out.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.procureengine.p2p.bp.onboarding.application.port.out.DomainEventPublisher;

@Component
public class KafkaDomainEventPublisher implements DomainEventPublisher {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public KafkaDomainEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void publish(Object event) {
		kafkaTemplate.send("bp-events", event);
	}
}
