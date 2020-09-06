package com.whatfucksoftware.pokedex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatfucksoftware.pokedex.PokemonRoutes;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PokemonControllerTest {

    @MockBean
    PokemonService service;

    MockMvc mvc;

    @BeforeEach
    public void setUp(){
        mvc = MockMvcBuilders
                .standaloneSetup(new PokemonController(service))
                .build();
    }

    @Test
    @DisplayName("Deve criar um pokemon com sucesso")
    public void createTest() throws Exception{
        PokemonDTO pokemonDto = getPokemonDto();
        String pokemonJSON = new ObjectMapper().writeValueAsString(pokemonDto);
        given(service.create(any(PokemonDTO.class))).willReturn(pokemonDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PokemonRoutes.POKEMONS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(pokemonJSON);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("name").value( pokemonDto.getName() ))
                .andExpect(jsonPath("number").value( pokemonDto.getNumber() ))
                .andExpect(jsonPath("primaryType").value( valueOf(pokemonDto.getPrimaryType()) ))
                .andExpect(jsonPath("secondaryType").value( valueOf(pokemonDto.getSecondaryType()) ))
                .andExpect(jsonPath("images", hasSize(2)));
    }

    private PokemonDTO getPokemonDto() {
        return PokemonDTO.builder()
                .id("okjosdml668dsdSffdKPPdsg")
                .name("Charmander")
                .number(55)
                .primaryType(PokemonTypeEnum.FIRE)
                .secondaryType(PokemonTypeEnum.NORMAL)
                .images(Set.of("/charmander001.png", "/charmander002.png"))
                .build();
    }
}
