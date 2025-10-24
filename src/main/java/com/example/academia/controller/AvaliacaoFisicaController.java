package com.example.academia.controller;

import com.example.academia.entity.AvaliacaoFisica;
import com.example.academia.service.AvaliacaoFisicaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {
    private final AvaliacaoFisicaService service;
    public AvaliacaoFisicaController(AvaliacaoFisicaService service) { this.service = service; }

    @GetMapping
    public List<AvaliacaoFisica> listar() { return service.listar(); }

    @PostMapping
    public AvaliacaoFisica criar(@RequestBody AvaliacaoFisica avaliacao) {
        return service.salvar(avaliacao);
    }
}
