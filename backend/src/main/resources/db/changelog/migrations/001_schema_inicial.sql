--liquibase formatted sql

--changeset david:1
CREATE TABLE IF NOT EXISTS pokemon (
    id VARCHAR(255) NOT NULL CONSTRAINT pokemon_pk PRIMARY KEY,
    number BIGINT NOT NULL,
    name VARCHAR(255) CONSTRAINT name_uk UNIQUE
);

--rollback DROP TABLE pokemon
--rollback DROP SEQUENCE hibernate_sequence;

CREATE TABLE IF NOT EXISTS types (
    type VARCHAR(255),
    pokemon_id VARCHAR(255) CONSTRAINT types_pokemon_fk REFERENCES pokemon
);
--rollback ALTER TABLE types DROP CONSTRAINT types_pokemon_fk;
--rollback DROP TABLE types;
