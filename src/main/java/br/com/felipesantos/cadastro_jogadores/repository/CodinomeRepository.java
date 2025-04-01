package br.com.felipesantos.cadastro_jogadores.repository;

import br.com.felipesantos.cadastro_jogadores.web.CodinomeDTO;

public interface CodinomeRepository {
    CodinomeDTO buscarCodinomes() throws Exception;
}
