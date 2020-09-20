package com.whatfucksoftware.pokedex.service;

import com.whatfucksoftware.pokedex.exception.InvalidArgumentsException;
import com.whatfucksoftware.pokedex.mapper.PokemonMapper;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.dto.PokemonListDTO;
import com.whatfucksoftware.pokedex.model.entity.PokemonEntity;
import com.whatfucksoftware.pokedex.repository.PokemonRepository;
import com.whatfucksoftware.pokedex.service.impl.PokemonServiceImpl;
import com.whatfucksoftware.pokedex.validator.PokemonValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ederfmatos.mockbean.MockBean.mockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PokemonServiceTest {

    @MockBean
    PokemonRepository pokemonRepository;

    PokemonService pokemonService;

    @BeforeEach
    public void setup() {
        this.pokemonService = new PokemonServiceImpl(new PokemonMapper(new ModelMapper()), pokemonRepository, new PokemonValidator(pokemonRepository));
    }

    @Test
    @DisplayName("Deve listar os pokemons")
    public void shouldBeListPokemons() {
        List<PokemonEntity> pokemons = mockBean(PokemonEntity.class)
                .build(5);

        doReturn(pokemons).when(pokemonRepository).findAll();

        List<PokemonListDTO> pokemonDTOS = pokemonService.findAll();

        assertThat(pokemonDTOS).hasSize(5);
        assertThat(pokemonDTOS.get(0).getId()).isEqualTo(pokemonDTOS.get(0).getId());
    }

    @Test
    @DisplayName("Deve buscar um pokemon pelo id")
    public void shouldBeFindPokemonById() {
        final String id = UUID.randomUUID().toString();
        PokemonEntity pokemon = mockBean(PokemonEntity.class)
                .with("id", id)
                .build();

        doReturn(Optional.of(pokemon)).when(pokemonRepository).findById(any());

        PokemonDTO pokemonDTO = pokemonService.findById(id);

        assertThat(pokemonDTO).isNotNull();
        assertThat(pokemonDTO.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar criar pokemon com nome duplicado")
    public void shouldBeThrowErrorOnCreatePokemonWithDuplicatedName() {
        PokemonDTO pokemon = mockBean(PokemonDTO.class)
                .with("name", "Pikachu")
                .build();

        doReturn(true).when(pokemonRepository).existsByName(any());

        assertThrows(InvalidArgumentsException.class, () -> pokemonService.create(pokemon));
        verify(pokemonRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar criar pokemon com numero duplicado")
    public void shouldBeThrowErrorOnCreatePokemonWithDuplicatedNumber() {
        PokemonDTO pokemon = mockBean(PokemonDTO.class)
                .with("number", 1)
                .build();

        doReturn(true).when(pokemonRepository).existsByNumber(1);

        assertThrows(InvalidArgumentsException.class, () -> pokemonService.create(pokemon));
        verify(pokemonRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve criar um pokemon")
    public void shouldBeCreatePokemon() {
        PokemonDTO pokemon = mockBean(PokemonDTO.class)
                .without("id")
                .build();

        PokemonDTO pokemonDTO = pokemonService.create(pokemon);
        assertThat(pokemonDTO).isNotNull();
        assertThat(pokemonDTO.getId()).isNotNull();
    }
}
