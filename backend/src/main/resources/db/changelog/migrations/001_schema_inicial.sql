--liquibase formatted sql

--changeset David Chaves:1
CREATE TABLE IF NOT EXISTS pokemon (
    id VARCHAR(255) NOT NULL CONSTRAINT pokemon_pk PRIMARY KEY,
    name VARCHAR(255) NOT NULL CONSTRAINT name_uk UNIQUE,
    number BIGINT NOT NULL,
    primaryType VARCHAR(50) NOT NULL,
    secondaryType VARCHAR(50) NOT NULL
);

--rollback DROP TABLE pokemon