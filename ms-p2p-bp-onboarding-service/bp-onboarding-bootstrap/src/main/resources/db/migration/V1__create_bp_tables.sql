CREATE TABLE business_partner (
    id               VARCHAR(36) PRIMARY KEY,
    legal_name       VARCHAR(255) NOT NULL,
    tax_id           VARCHAR(50),
    status           VARCHAR(50),
    created_at       TIMESTAMP NOT NULL DEFAULT now(),
    updated_at       TIMESTAMP
);

CREATE TABLE onboarding_case (
    id               VARCHAR(36) PRIMARY KEY,
    bp_id            VARCHAR(36) REFERENCES business_partner(id),
    status           VARCHAR(50),
    created_at       TIMESTAMP NOT NULL DEFAULT now(),
    updated_at       TIMESTAMP
);
