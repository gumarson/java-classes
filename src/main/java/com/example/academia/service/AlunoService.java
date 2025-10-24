package com.example.academia.service;

import com.example.academia.entity.Aluno;
import com.example.academia.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> listar() { return repository.findAll(); }
    public Aluno salvar(Aluno aluno) { return repository.save(aluno); }
    public Aluno buscar(Long id) { return repository.findById(id).orElse(null); }
    public void deletar(Long id) { repository.deleteById(id); }
}
