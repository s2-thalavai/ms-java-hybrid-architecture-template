package com.procureengine.p2p.bp.onboarding.bootstrap.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.procureengine.p2p.bp.onboarding.adapters.out.persistence")
@EntityScan(basePackages = "com.procureengine.p2p.bp.onboarding.adapters.out.persistence.entity")
public class JpaConfig {
}
