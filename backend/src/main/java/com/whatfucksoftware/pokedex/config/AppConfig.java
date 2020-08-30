package com.whatfucksoftware.pokedex.config;

import com.whatfucksoftware.pokedex.mapper.converter.PokemonTypeEnumConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        PokemonTypeEnumConverter pokemonTypeEnumConverter = new PokemonTypeEnumConverter();

        modelMapper.addConverter(pokemonTypeEnumConverter.getEnumConverter());
        modelMapper.addConverter(pokemonTypeEnumConverter.getStringConverter());

        return modelMapper;
    }

}
