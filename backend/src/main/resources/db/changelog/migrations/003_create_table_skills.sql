--liquibase formatted sql

--changeset EderMatos:1
CREATE TABLE IF NOT EXISTS pokemon_skills (
    skill TEXT NOT NULL,
    pokemon_id VARCHAR(255) CONSTRAINT skills_pokemon_fk REFERENCES pokemons(id)
);
--rollback ALTER TABLE pokemon_skills DROP CONSTRAINT skills_pokemon_fk;
--rollback DROP TABLE pokemon_skills;
