package com.whatfucksoftware.pokedex.config;

import com.google.gson.Gson;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;
import com.whatfucksoftware.pokedex.repository.PokemonRepository;
import com.whatfucksoftware.pokedex.service.PokemonService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {

    @Autowired
    PokemonService pokemonService;

    @Autowired
    PokemonRepository pokemonRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            pokemonRepository.deleteAll();

            PokemonResponse pokemonResponse = get("https://pokeapi.co/api/v2/pokemon?limit=50", PokemonResponse.class);

            List<Map<String, Object>> pokemons = pokemonResponse.getResults()
                    .parallelStream()
                    .map(map -> get(map.get("url"), new TypeToken<Map<String, Object>>() {
                    }.getRawType()))
                    .collect(Collectors.toList());

            List<PokemonDTO> pokemonAList = pokemons.parallelStream().map(map -> PokemonA.builder()
                    .name(map.get("name").toString())
                    .number((Double) map.get("order"))
                    .types((List<Map<String, Map<String, String>>>) map.get("types"))
                    .weight((Double) map.get("weight"))
                    .build()).map(pokemonA -> PokemonDTO.builder()
                    .name(pokemonA.getName())
                    .number(pokemonA.getNumber().intValue())
                    .images(Set.of("https://pokeres.bastionbot.org/images/pokemon/" + pokemonA.getNumber().intValue() + ".png"))
                    .primaryType(PokemonTypeEnum.of(pokemonA.getTypes(), 0))
                    .secondaryType(PokemonTypeEnum.of(pokemonA.getTypes(), 1))
                    .build())
                    .map(pokemonDTO -> pokemonService.create(pokemonDTO))
                    .collect(Collectors.toList());

            System.out.println(pokemonAList);
        };
    }

    private <T> T get(String url, Class<T> clazz) {
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            return new Gson().fromJson(body.string(), clazz);
        } catch (Exception e) {
            return null;
        }
    }
}