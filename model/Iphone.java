package br.com.desafio.iphone.model;

import br.com.desafio.iphone.interfaces.AparelhoTelefonico;
import br.com.desafio.iphone.interfaces.NavegadorInternet;
import br.com.desafio.iphone.interfaces.ReprodutorMusical;

import java.util.ArrayList;
import java.util.List;

public class Iphone implements ReprodutorMusical, AparelhoTelefonico, NavegadorInternet {

    // --- Estados simples só para demonstrar o comportamento ---
    private String musicaAtual;
    private boolean tocando;

    private String numeroChamadaAtual;
    private boolean chamadaEmAndamento;

    private final List<String> abas = new ArrayList<>();
    private String paginaAtual;

    // ---------- ReprodutorMusical ----------
    @Override
    public void tocar() {
        if (musicaAtual == null) {
            System.out.println("[Música] Nenhuma música selecionada.");
            return;
        }
        tocando = true;
        System.out.println("[Música] Tocando: " + musicaAtual);
    }

    @Override
    public void pausar() {
        if (!tocando) {
            System.out.println("[Música] Já está pausado.");
            return;
        }
        tocando = false;
        System.out.println("[Música] Pausado: " + musicaAtual);
    }

    @Override
    public void selecionarMusica(String nome) {
        musicaAtual = nome;
        System.out.println("[Música] Selecionada: " + musicaAtual);
    }

    // ---------- AparelhoTelefonico ----------
    @Override
    public void ligar(String numero) {
        if (chamadaEmAndamento) {
            System.out.println("[Telefone] Finalize a chamada atual antes de ligar para outro número.");
            return;
        }
        numeroChamadaAtual = numero;
        chamadaEmAndamento = true;
        System.out.println("[Telefone] Ligando para " + numeroChamadaAtual + "...");
    }

    @Override
    public void atender() {
        chamadaEmAndamento = true;
        System.out.println("[Telefone] Chamada atendida.");
    }

    @Override
    public void iniciarCorreioVoz() {
        System.out.println("[Telefone] Correio de voz iniciado.");
    }

    // ---------- NavegadorInternet ----------
    @Override
    public void exibirPagina(String url) {
        paginaAtual = url;
        if (abas.isEmpty()) abas.add("Aba 1");
        System.out.println("[Navegador] Exibindo página: " + paginaAtual + " na " + abas.get(abas.size()-1));
    }

    @Override
    public void adicionarNovaAba() {
        String novaAba = "Aba " + (abas.size() + 1);
        abas.add(novaAba);
        System.out.println("[Navegador] Nova aba aberta: " + novaAba + " (total: " + abas.size() + ")");
    }

    @Override
    public void atualizarPagina() {
        if (paginaAtual == null) {
            System.out.println("[Navegador] Nenhuma página para atualizar.");
            return;
        }
        System.out.println("[Navegador] Página atualizada: " + paginaAtual);
    }
}
