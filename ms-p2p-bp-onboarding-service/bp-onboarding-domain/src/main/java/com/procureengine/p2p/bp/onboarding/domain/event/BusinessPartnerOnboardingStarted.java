package com.procureengine.p2p.bp.onboarding.domain.event;

import java.time.Instant;

public record BusinessPartnerOnboardingStarted(
        String bpId,
        String legalName,
        Instant occurredOn,
        String eventType,
        Integer version,
        String source
) {

    public static final String EVENT_TYPE = "BusinessPartnerOnboardingStarted";
    public static final int EVENT_VERSION = 1;
    public static final String SOURCE = "bp-onboarding-service";

    public static BusinessPartnerOnboardingStarted create(String bpId, String legalName) {
        return new BusinessPartnerOnboardingStarted(
                bpId,
                legalName,
                Instant.now(),
                EVENT_TYPE,
                EVENT_VERSION,
                SOURCE
        );
    }
}
