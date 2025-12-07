package com.procureengine.p2p.bp.onboarding.application.command;

public record StartOnboardingCommand(
        String legalName,
        String taxId
) {}
