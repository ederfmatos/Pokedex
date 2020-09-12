--liquibase formatted sql

--changeset Eder Matos:1
CREATE TABLE IF NOT EXISTS pokemon_images (
    image_url TEXT NOT NULL,
    pokemon_id VARCHAR(255) CONSTRAINT images_pokemon_fk REFERENCES pokemons(id)
);
--rollback ALTER TABLE pokemon_images DROP CONSTRAINT images_pokemon_fk;
--rollback DROP TABLE pokemon_images;
