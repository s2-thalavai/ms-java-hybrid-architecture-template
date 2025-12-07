package com.procureengine.p2p.bp.onboarding.application.port.out;

public interface DomainEventPublisher {
    void publish(Object event);
}
