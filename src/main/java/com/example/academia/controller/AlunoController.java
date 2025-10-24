package com.example.academia.controller;

import com.example.academia.entity.Aluno;
import com.example.academia.service.AlunoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Aluno> listar() { return service.listar(); }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) { return service.salvar(aluno); }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) { return service.buscar(id); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { service.deletar(id); }
}
