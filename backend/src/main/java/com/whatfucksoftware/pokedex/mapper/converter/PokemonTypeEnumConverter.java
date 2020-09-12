package com.whatfucksoftware.pokedex.mapper.converter;

import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class PokemonTypeEnumConverter implements AttributeConverter<PokemonTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PokemonTypeEnum attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getNumber();
    }

    @Override
    public PokemonTypeEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return PokemonTypeEnum.of(dbData);
    }

}
