package com.procureengine.p2p.bp.onboarding.adapters.in.rest.dto;

public record StartOnboardingRequest(
        String legalName,
        String taxId
) {}
