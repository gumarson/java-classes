package com.example.academia.controller;

import com.example.academia.entity.Matricula;
import com.example.academia.service.MatriculaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaService service;
    public MatriculaController(MatriculaService service) { this.service = service; }

    @GetMapping
    public List<Matricula> listar() { return service.listar(); }

    @PostMapping
    public Matricula criar(@RequestBody Matricula matricula) {
        return service.salvar(matricula);
    }
}
