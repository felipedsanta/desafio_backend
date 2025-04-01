package br.com.felipesantos.cadastro_jogadores.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.felipesantos.cadastro_jogadores.model.GrupoCodinome;
import br.com.felipesantos.cadastro_jogadores.model.Jogador;
import br.com.felipesantos.cadastro_jogadores.service.JogadorService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("cadastro-jogador")
public class CadastroJogadorController {
    private final JogadorService jogadorService;

    public CadastroJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String paginaCadastroJogador(Model model) {
        model.addAttribute("jogador", new Jogador(null, null, null, null, null));
        model.addAttribute("gruposCodinomes", GrupoCodinome.values());
        return "cadastro_jogador";
    }

    @PostMapping
    public String cadastrarJogador(@ModelAttribute Jogador jogador) {
        try {
            jogadorService.registrarJogador(jogador);
            return "redirect:/cadastro-jogador";
        } catch (Exception e) {
            return "redirect:/cadastro-jogador";
        }
    }
}
