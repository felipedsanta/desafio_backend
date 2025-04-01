package br.com.felipesantos.cadastro_jogadores.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.felipesantos.cadastro_jogadores.model.GrupoCodinome;
import br.com.felipesantos.cadastro_jogadores.web.CodinomeDTO;
import br.com.felipesantos.cadastro_jogadores.web.VingadoresDTO;

@Repository
public class VingadoresRepository implements CodinomeRepository {

    @Override
    public CodinomeDTO buscarCodinomes() throws Exception {
        var codinomes = RestClient
                .builder()
                .baseUrl(GrupoCodinome.VINGADORES.getUri())
                .build()
                .get()
                .retrieve()
                .body(String.class);

        var objectMapper = new ObjectMapper();
        var vingadores = objectMapper.readValue(codinomes, VingadoresDTO.class);

        return vingadores;
    }

}
