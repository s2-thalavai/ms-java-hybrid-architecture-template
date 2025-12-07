package com.procureengine.p2p.bp.onboarding.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.procureengine.p2p.bp.onboarding")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("BP Onboarding Service Started Successfully");
    }
}
