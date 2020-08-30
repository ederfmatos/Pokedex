--liquibase formatted sql

--changeset EderMatos:1
CREATE TABLE IF NOT EXISTS pokemon_skills (
    type VARCHAR(255),
    pokemon_id VARCHAR(255) CONSTRAINT skills_pokemon_fk REFERENCES pokemon(id)
);
--rollback ALTER TABLE pokemon_skills DROP CONSTRAINT skills_pokemon_fk;
--rollback DROP TABLE pokemon_skills;
