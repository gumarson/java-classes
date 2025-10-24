package com.example.academia.service;

import com.example.academia.entity.AvaliacaoFisica;
import com.example.academia.repository.AvaliacaoFisicaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvaliacaoFisicaService {
    private final AvaliacaoFisicaRepository repository;

    public AvaliacaoFisicaService(AvaliacaoFisicaRepository repository) {
        this.repository = repository;
    }

    public List<AvaliacaoFisica> listar() { return repository.findAll(); }
    public AvaliacaoFisica salvar(AvaliacaoFisica avaliacao) { return repository.save(avaliacao); }
}
