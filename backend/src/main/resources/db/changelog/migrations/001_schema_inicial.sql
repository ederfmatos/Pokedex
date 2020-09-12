--liquibase formatted sql

--changeset David Chaves:1
CREATE TABLE IF NOT EXISTS pokemons (
    id VARCHAR(255) NOT NULL CONSTRAINT pokemon_pk PRIMARY KEY,
    name VARCHAR(255) NOT NULL CONSTRAINT name_uk UNIQUE,
    pokemon_number INTEGER UNIQUE NOT NULL,
    primary_type INTEGER NOT NULL,
    secondary_type INTEGER
);

--rollback DROP TABLE pokemon