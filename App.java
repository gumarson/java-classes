package br.com.desafio.iphone;

import br.com.desafio.iphone.model.Iphone;

public class App {
    public static void main(String[] args) {
        Iphone iphone = new Iphone();

        // Reprodutor Musical
        iphone.selecionarMusica("U2 - Beautiful Day");
        iphone.tocar();
        iphone.pausar();

        // Aparelho Telefônico
        iphone.ligar("+55 11 99999-0000");
        iphone.iniciarCorreioVoz();
        iphone.atender();

        // Navegador na Internet
        iphone.exibirPagina("https://www.apple.com");
        iphone.adicionarNovaAba();
        iphone.exibirPagina("https://www.ddccompany.com.br");
        iphone.atualizarPagina();
    }
}
