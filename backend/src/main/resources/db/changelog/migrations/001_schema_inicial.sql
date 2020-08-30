--liquibase formatted sql

--changeset David Chaves:1
CREATE TABLE IF NOT EXISTS pokemons (
    id VARCHAR(255) NOT NULL CONSTRAINT pokemon_pk PRIMARY KEY,
    name VARCHAR(255) NOT NULL CONSTRAINT name_uk UNIQUE,
    pokemon_number INTEGER NOT NULL,
    primary_type VARCHAR(50) NOT NULL,
    secondary_type VARCHAR(50) NOT NULL
);

--rollback DROP TABLE pokemon