package com.whatfucksoftware.pokedex.controller;

import com.google.gson.Gson;
import com.whatfucksoftware.pokedex.PokemonRoutes;
import com.whatfucksoftware.pokedex.exception.PokemonNotFound;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.service.PokemonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.ederfmatos.mockbean.MockBean.mock;
import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ControllerExceptionHandler.class, PokemonController.class})
@WebMvcTest
@AutoConfigureMockMvc
public class PokemonControllerTest {

    @MockBean
    PokemonService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    Gson gson;

    @Test
    @DisplayName("Deve criar um pokemon com sucesso")
    public void createPokemonTest() throws Exception {
        com.ederfmatos.mockbean.MockBean<PokemonDTO> mockBeanPokemon = mock(PokemonDTO.class)
                .with("images", Set.of("001", "002"));

        PokemonDTO pokemonDto = mockBeanPokemon.build();
        String pokemonJSON = mockBeanPokemon.json();

        given(service.create(any(PokemonDTO.class))).willReturn(pokemonDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PokemonRoutes.POKEMONS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(pokemonJSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("name").value(pokemonDto.getName()))
                .andExpect(jsonPath("number").value(pokemonDto.getNumber()))
                .andExpect(jsonPath("primaryType").value(valueOf(pokemonDto.getPrimaryType())))
                .andExpect(jsonPath("secondaryType").value(valueOf(pokemonDto.getSecondaryType())))
                .andExpect(jsonPath("images", hasSize(2)));
    }

    @Test
    @DisplayName("Deve lançar erros de validação ao tentar criar um pokemon sem campos obrigatórios")
    public void shouldNotCreateAnInvalidPokemonTest() throws Exception {
        String pokemonJSON = gson.toJson(new PokemonDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PokemonRoutes.POKEMONS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(pokemonJSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(5)))
                .andExpect(jsonPath("timestamp").isNumber())
                .andExpect(jsonPath("statusCode").value(400));
    }

    @Test
    @DisplayName("Deve retornar um pokemon com o id informado")
    public void returnPokemonWithIdTest() throws Exception {
        String id = UUID.randomUUID().toString();
        PokemonDTO pokemonDto = mock(PokemonDTO.class)
                .with("images", Set.of("001", "002"))
                .with("id", id)
                .build();

        given(service.findById(id)).willReturn(pokemonDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(PokemonRoutes.POKEMONS.concat("/" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("name").value(pokemonDto.getName()))
                .andExpect(jsonPath("number").value(pokemonDto.getNumber()))
                .andExpect(jsonPath("primaryType").value(valueOf(pokemonDto.getPrimaryType())))
                .andExpect(jsonPath("secondaryType").value(valueOf(pokemonDto.getSecondaryType())))
                .andExpect(jsonPath("images", hasSize(2)));
    }

    @Test
    @DisplayName("Deve lançar um erro com a mensagem de pokemon não encontrado")
    public void pokemonNotFoundTest() throws Exception {
        String id = UUID.randomUUID().toString();
        given(service.findById(id)).willThrow(new PokemonNotFound());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(PokemonRoutes.POKEMONS.concat("/" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("statusCode").value(404))
                .andExpect(jsonPath("timestamp").isNumber())
                .andExpect(jsonPath("errors[0]").value("Pokemon não encontrado!"));
    }

    @Test
    @DisplayName("Deve listar pokemons")
    public void shouldBeListPokemons() throws Exception {
        List<PokemonDTO> pokemons = mock(PokemonDTO.class)
                .build(5);

        doReturn(pokemons).when(service).findAll();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(PokemonRoutes.POKEMONS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @DisplayName("Deve alterar um pokemon")
    public void shouldBeUpdatePokemon() throws Exception {
        com.ederfmatos.mockbean.MockBean<PokemonDTO> mock = mock(PokemonDTO.class);

        String pokemonJson = mock.json();
        PokemonDTO pokemonDTO = mock.build();
        final String id = UUID.randomUUID().toString();

        doReturn(pokemonDTO).when(service).update(any(), any());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(PokemonRoutes.POKEMONS.concat("/" + id))
                .content(pokemonJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve deletar um pokemon")
    public void shouldBeDeletePokemon() throws Exception {
        final String id = UUID.randomUUID().toString();

        doNothing().when(service).delete(any());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(PokemonRoutes.POKEMONS.concat("/" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar um erro com a mensagem de pokemon não encontrado")
    public void shouldBeThrowErrorOnPokemonNotFound() throws Exception {
        String id = UUID.randomUUID().toString();
        doThrow(new PokemonNotFound()).when(service).delete(any());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(PokemonRoutes.POKEMONS.concat("/" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("statusCode").value(404))
                .andExpect(jsonPath("timestamp").isNumber())
                .andExpect(jsonPath("errors[0]").value("Pokemon não encontrado!"));
    }

}
