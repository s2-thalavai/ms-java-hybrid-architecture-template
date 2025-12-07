package com.procureengine.p2p.bp.onboarding.application.port.in;

import com.procureengine.p2p.bp.onboarding.application.command.StartOnboardingCommand;

public interface OnboardBusinessPartnerUseCase {
    String startOnboarding(StartOnboardingCommand command);
}
