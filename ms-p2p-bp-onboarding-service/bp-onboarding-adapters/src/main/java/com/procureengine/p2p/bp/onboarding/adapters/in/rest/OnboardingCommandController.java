package com.procureengine.p2p.bp.onboarding.adapters.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procureengine.p2p.bp.onboarding.adapters.in.rest.dto.StartOnboardingRequest;
import com.procureengine.p2p.bp.onboarding.adapters.in.rest.dto.StartOnboardingResponse;
import com.procureengine.p2p.bp.onboarding.application.command.StartOnboardingCommand;
import com.procureengine.p2p.bp.onboarding.application.port.in.OnboardBusinessPartnerUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/onboarding/bp")
@Tag(name = "Business Partner Onboarding", description = "API for BP lifecycle onboarding")
public class OnboardingCommandController {

	private final OnboardBusinessPartnerUseCase onboardUseCase;

	public OnboardingCommandController(OnboardBusinessPartnerUseCase onboardUseCase) {
		this.onboardUseCase = onboardUseCase;
	}

	@Operation(summary = "Start onboarding", description = "Registers a new Business Partner onboarding request")
	@ApiResponses({ @ApiResponse(responseCode = "202", description = "Onboarding accepted"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PostMapping
	public ResponseEntity<StartOnboardingResponse> start(@RequestBody StartOnboardingRequest request) {

		StartOnboardingCommand command = new StartOnboardingCommand(request.legalName(), request.taxId());

		String bpId = onboardUseCase.startOnboarding(command);

		return ResponseEntity.accepted().body(new StartOnboardingResponse(bpId, "INITIATED"));
	}
}
