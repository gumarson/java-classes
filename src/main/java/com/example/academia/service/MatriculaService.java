package com.example.academia.service;

import com.example.academia.entity.Matricula;
import com.example.academia.repository.MatriculaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatriculaService {
    private final MatriculaRepository repository;

    public MatriculaService(MatriculaRepository repository) {
        this.repository = repository;
    }

    public List<Matricula> listar() { return repository.findAll(); }
    public Matricula salvar(Matricula matricula) { return repository.save(matricula); }
}
