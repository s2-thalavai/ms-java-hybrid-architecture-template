# **DDD + Hexagonal Architecture + Event-Driven + CQRS + Modularity**

A starter template implementing **DDD + Hexagonal (Ports & Adapters) + Event-Driven + CQRS (selectively) + Modularity** for a P2P Business Partner Onboarding service.

## Goals

-   Clean domain boundaries
    
-   Infrastructure adaptability
    
-   Scalability
    
-   Loose coupling
    
-   Testability
    
-   Clear separation of concerns

### Why?

-   DDD → solves domain complexity
    
-   Hexagonal → clean boundaries
    
-   Event-Driven → loose coupling and scalability
    
-   CQRS → high write throughput and reporting flexibility
    
-   Modularity → independent development + future microservice split
    

> **Example Scenario:** **P2P Business Partner Onboarding Service**.

-------

Let’s design this as a **real production-grade microservice skeleton**:

> **ms-p2p-bp-onboarding-service**  
> Java 17 + Spring Boot 3 + Kafka + DDD + Hexagonal + Event-Driven + selective CQRS + modular structure.

----------

## 1. Domain & responsibilities

This microservice is about **getting a Business Partner (BP) onboarded** into P2P:

**Core responsibilities**

-   Accept BP onboarding requests (supplier / customer / both)
    
-   Capture legal, tax, bank & contact info
    
-   Orchestrate checks (KYC, tax, bank validation, blacklist, etc.)
    
-   Persist onboarding state & history
    
-   Emit events for downstream systems (BP master, finance, procurement)
    
-   Provide query APIs for UI / other services
    

**Out-of-scope (other microservices)**

-   Global BP Master / MDG
    
-   Risk & Compliance engine
    
-   Notification service (email/SMS)
    
-   Auth / Identity
    

This keeps onboarding clean and focused.

----------

## 2. High-level architecture (how all patterns fit)

**Stacking patterns:**

-   **DDD** → domain model, aggregates, domain events, Ubiquitous Language
    
-   **Hexagonal (Ports & Adapters)** → ports: use cases / repos / event bus; adapters: REST, DB, Kafka
    
-   **Event-driven** → Kafka topics for onboarding lifecycle
    
-   **CQRS (selective)** → split command/write use cases from query/read API (but same DB is fine initially)
    
-   **Modularity** → separate Java modules or clear package modules
    

### 2.1 Layered hexagonal view

**Inside the service:**

-   **Domain (core)**
    
    -   Aggregates: `BusinessPartner`, `OnboardingCase`
        
    -   Value objects: `TaxId`, `BankAccount`, `Address`, `ContactPerson`
        
    -   Domain events: `BusinessPartnerOnboardingStarted`, `BusinessPartnerVerified`, `BusinessPartnerOnboarded`
        
-   **Application layer (use cases)**
    
    -   Command-side:
        
        -   `StartOnboardingHandler`
            
        -   `SubmitDocumentsHandler`
            
        -   `CompleteComplianceCheckHandler`
            
        -   `ApproveOnboardingHandler`
            
    -   Query-side:
        
        -   `GetOnboardingStatusHandler`
            
        -   `SearchBusinessPartnersHandler`
            
-   **Ports**
    
    -   Primary ports (driving):
        
        -   `OnboardBusinessPartnerUseCase`
            
        -   `ManageOnboardingDocumentsUseCase`
            
        -   `BusinessPartnerQueryUseCase`
            
    -   Secondary ports (driven):
        
        -   `BusinessPartnerRepository`
            
        -   `OnboardingCaseRepository`
            
        -   `DomainEventPublisher`
            
        -   `ComplianceCheckPort` (if synchronous)
            
        -   `NotificationPort` (if needed)
            
-   **Adapters**
    
    -   REST controllers (HTTP in)
        
    -   Kafka listeners (events in)
        
    -   Kafka publishers (events out)
        
    -   JPA/Mongo repositories
        
    -   Mappers (DTO ↔ domain)
        

----------

## 3. Modularity structure (packages / modules)

### 3.1 Maven / Gradle modules (optional but recommended)

You can do **one repo with 4 modules**:

-   `bp-onboarding-domain`
    
-   `bp-onboarding-application`
    
-   `bp-onboarding-adapters`
    
-   `bp-onboarding-bootstrap` (Spring Boot main)
    

If you want it as a single module, mimic this structure via packages.

### 3.2 Package structure (inside service)

```css
com.yourcompany.p2p.bp.onboarding
 ├─ domain
 │   ├─ model
 │   │   ├─ BusinessPartner.java
 │   │   ├─ OnboardingCase.java
 │   │   ├─ value
 │   │   │   ├─ BusinessPartnerId.java
 │   │   │   ├─ TaxId.java
 │   │   │   ├─ BankAccount.java
 │   │   │   ├─ Address.java
 │   │   │   └─ ContactPerson.java
 │   ├─ event
 │   │   ├─ BusinessPartnerOnboardingStarted.java
 │   │   ├─ BusinessPartnerVerified.java
 │   │   └─ BusinessPartnerOnboarded.java
 │   ├─ repository
 │   │   ├─ BusinessPartnerRepository.java
 │   │   └─ OnboardingCaseRepository.java
 │   └─ service (optional domain services)
 │       └─ RiskAssessmentDomainService.java
 │
 ├─ application
 │   ├─ command
 │   │   ├─ StartOnboardingCommand.java
 │   │   ├─ SubmitDocumentsCommand.java
 │   │   ├─ CompleteComplianceCheckCommand.java
 │   │   ├─ ApproveOnboardingCommand.java
 │   │   └─ handler
 │   │       ├─ StartOnboardingHandler.java
 │   │       ├─ SubmitDocumentsHandler.java
 │   │       ├─ CompleteComplianceCheckHandler.java
 │   │       └─ ApproveOnboardingHandler.java
 │   ├─ query
 │   │   ├─ GetOnboardingStatusQuery.java
 │   │   ├─ SearchBusinessPartnersQuery.java
 │   │   └─ handler
 │   │       ├─ GetOnboardingStatusHandler.java
 │   │       └─ SearchBusinessPartnersHandler.java
 │   └─ port
 │       ├─ in
 │       │   ├─ OnboardBusinessPartnerUseCase.java
 │       │   └─ BusinessPartnerQueryUseCase.java
 │       └─ out
 │           ├─ DomainEventPublisher.java
 │           ├─ ComplianceCheckPort.java
 │           └─ NotificationPort.java
 │
 ├─ adapters
 │   ├─ in
 │   │   ├─ rest
 │   │   │   ├─ OnboardingCommandController.java
 │   │   │   └─ OnboardingQueryController.java
 │   │   └─ kafka
 │   │       └─ ComplianceResultListener.java
 │   └─ out
 │       ├─ persistence
 │       │   ├─ JpaBusinessPartnerRepository.java
 │       │   ├─ JpaOnboardingCaseRepository.java
 │       │   └─ entity
 │       │       ├─ BusinessPartnerEntity.java
 │       │       └─ OnboardingCaseEntity.java
 │       ├─ kafka
 │       │   ├─ KafkaDomainEventPublisher.java
 │       │   └─ eventpayload
 │       │       └─ BusinessPartnerOnboardedPayload.java
 │       └─ notification
 │           └─ NotificationAdapter.java
 │
 └─ config
     ├─ OnboardingServiceConfig.java
     ├─ KafkaConfig.java
     └─ SwaggerConfig.java
``` 

----------

## 4. CQRS – what is command vs query here?

**Use CQRS selectively:**

-   **Commands (write side)**  
    Anything that changes state or triggers onboarding workflow:
    
    -   `POST /onboarding/bp` → Start onboarding
        
    -   `POST /onboarding/{id}/documents` → upload/submit docs
        
    -   `POST /onboarding/{id}/approve` → approve onboarding
        
    -   `POST /onboarding/{id}/reject` → reject onboarding
        
-   **Queries (read side)**  
    Anything meant for UI, dashboards, search:
    
    -   `GET /onboarding/{id}`
        
    -   `GET /onboarding?status=PENDING&legalName=...`
        
    -   `GET /business-partners/{bpId}`
        

**Implementation strategy (simple & practical):**

-   Same database, separate **application services & DTOs**
    
-   Query handlers can read from:
    
    -   Same JPA entities (simple)
        
    -   Or dedicated read tables / projections later if you add ES/ElasticSearch
        

----------

## Event flow for key use case

**Use case: Supplier onboarding**

1.  UI → `POST /api/onboarding/bp`
    
2.  `OnboardingCommandController` → `StartOnboardingHandler`
    
3.  Domain creates `BusinessPartner` aggregate, raises `BusinessPartnerOnboardingStarted`
    
4.  `StartOnboardingHandler` saves via `BusinessPartnerRepository`
    
5.  `KafkaDomainEventPublisher` publishes to `bp.onboarding.started`
    
6.  **Downstream services**:
    
    -   Compliance service consumes → runs KYC → publishes `bp.compliance.results`
        
7.  `ComplianceResultListener` in this service consumes → `CompleteComplianceCheckHandler`
    
8.  Domain updates status to `VERIFIED` or `REJECTED`, raises `BusinessPartnerVerified`
    
9.  `DomainEventPublisher` → topic `bp.onboarding.verified`
    
10.  Once approvals done → `ApproveOnboardingHandler` → domain `markOnboarded()`  
    → `BusinessPartnerOnboarded` event → topic `bp.onboarding.completed`
    
11.  BP Master, Finance, Procurement microservices consume & update their own models.

----------
