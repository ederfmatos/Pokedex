package com.whatfucksoftware.pokedex.mapper.converter;

import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;
import org.modelmapper.AbstractConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class PokemonTypeEnumConverter implements AttributeConverter<PokemonTypeEnum, Integer> {

    public AbstractConverter<Integer, PokemonTypeEnum> getStringConverter() {
        return new AbstractConverter<>() {

            @Override
            protected PokemonTypeEnum convert(Integer source) {
                return convertToEntityAttribute(source);
            }
        };
    }

    public AbstractConverter<PokemonTypeEnum, Integer> getEnumConverter() {
        return new AbstractConverter<>() {
            @Override
            protected Integer convert(PokemonTypeEnum source) {
                return convertToDatabaseColumn(source);
            }
        };
    }

    @Override
    public Integer convertToDatabaseColumn(PokemonTypeEnum attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getNumber();
    }

    @Override
    public PokemonTypeEnum convertToEntityAttribute(Integer dbData) {
        return PokemonTypeEnum.of(dbData);
    }

}
