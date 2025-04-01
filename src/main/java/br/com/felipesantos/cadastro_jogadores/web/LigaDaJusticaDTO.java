package br.com.felipesantos.cadastro_jogadores.web;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Liga_da_justica")
public record LigaDaJusticaDTO(
        @JacksonXmlProperty(localName = "codinomes") CodinomesDTO codinomes) implements CodinomeDTO {

    @Override
    public List<String> getCodinomes() {
        return codinomes.codinomes();
    }
}

record CodinomesDTO(
        @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "codinome") List<String> codinomes) {
}
