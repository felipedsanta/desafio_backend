package br.com.felipesantos.cadastro_jogadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.felipesantos.cadastro_jogadores.model.GrupoCodinome;

@Service
public class CodinomeService {
    private final CodinomeRepositoryFactory codinomesRepositoryFactory;

    public CodinomeService(CodinomeRepositoryFactory codinomeRepositoryFactory) {
        this.codinomesRepositoryFactory = codinomeRepositoryFactory;
    }

    public String gerarCodinome(GrupoCodinome grupoCodinome, List<String> codinomesEmUso) throws Exception {
        var codinomesDisponiveis = listarCodinomesDisponiveis(grupoCodinome, codinomesEmUso);
        if (codinomesDisponiveis.isEmpty()) {
            throw new IllegalStateException("Não há codinomes disponíveis para o grupo " + grupoCodinome.getNome());
        }
        var codinomeSorteado = sortearCodinome(codinomesDisponiveis);
        return codinomeSorteado;
    }

    private List<String> listarCodinomesDisponiveis(GrupoCodinome grupoCodinome, List<String> codinomesEmUso)
            throws Exception {
        var codinomes = buscarCodinomes(grupoCodinome);

        var codinomesDisponiveis = codinomes.stream()
                .filter(codinome -> !codinomesEmUso.contains(codinome))
                .toList();

        return codinomesDisponiveis;
    }

    private List<String> buscarCodinomes(GrupoCodinome grupoCodinome) throws Exception {
        var codinomeRepository = codinomesRepositoryFactory.create(grupoCodinome);
        return codinomeRepository.buscarCodinomes().getCodinomes();
    }

    private String sortearCodinome(List<String> codinomesDisponiveis) {
        return codinomesDisponiveis
                .get((int) (Math.random() * codinomesDisponiveis.size()));
    }
}
