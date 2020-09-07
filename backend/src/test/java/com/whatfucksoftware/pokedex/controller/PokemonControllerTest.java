package com.whatfucksoftware.pokedex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.whatfucksoftware.pokedex.PokemonRoutes;
import com.whatfucksoftware.pokedex.exception.PokemonNotFound;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;
import com.whatfucksoftware.pokedex.service.PokemonService;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.OutputStream;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

    @Test
    @DisplayName("Deve criar um pokemon com sucesso")
    public void createPokemonTest() throws Exception{
        PokemonDTO pokemonDto = getPokemonDto();
        String pokemonJSON =  new Gson().toJson(pokemonDto);
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
                .andExpect(jsonPath("name").value( pokemonDto.getName() ))
                .andExpect(jsonPath("number").value( pokemonDto.getNumber() ))
                .andExpect(jsonPath("primaryType").value( valueOf(pokemonDto.getPrimaryType()) ))
                .andExpect(jsonPath("secondaryType").value( valueOf(pokemonDto.getSecondaryType()) ))
                .andExpect(jsonPath("images", hasSize(2)));
    }

    @Test
    @DisplayName("Deve lançar erros de validação ao tentar criar um pokemon sem campos obrigatórios")
    public void shouldNotCreateAnInvalidPokemonTest() throws Exception{
        String pokemonJSON = new Gson().toJson(new PokemonDTO());

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
    public void returnPokemonWithIdTest() throws Exception{
        String id = "KfdfpjH1fd5220gfKlDDOSu";
        PokemonDTO pokemonDto = getPokemonDto();
        pokemonDto.setId(id);
        given(service.findById(id)).willReturn(pokemonDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(PokemonRoutes.POKEMONS.concat("/"+ id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("name").value( pokemonDto.getName() ))
                .andExpect(jsonPath("number").value( pokemonDto.getNumber() ))
                .andExpect(jsonPath("primaryType").value( valueOf(pokemonDto.getPrimaryType()) ))
                .andExpect(jsonPath("secondaryType").value( valueOf(pokemonDto.getSecondaryType()) ))
                .andExpect(jsonPath("images", hasSize(2)));
    }

    @Test
    @DisplayName("Deve lançar um erro com a mensagem de pokemon não encontrado")
    public void pokemonNotFoundTest() throws Exception{
        String id = "lkkkglfiuioldasddsg";
        given(service.findById(id)).willThrow(new PokemonNotFound());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(PokemonRoutes.POKEMONS.concat("/"+ id))
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

    private PokemonDTO getPokemonDto() {
        return PokemonDTO.builder()
                .id("okjosdml668dsdSffdKPPdsg")
                .name("Charmander")
                .number(12)
                .primaryType( PokemonTypeEnum.FIRE )
                .secondaryType(PokemonTypeEnum.NORMAL)
                .images(Set.of("/charmander001.png", "/charmander002.png"))
                .build();
    }
}
