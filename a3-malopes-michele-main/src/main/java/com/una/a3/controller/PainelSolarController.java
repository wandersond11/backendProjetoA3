package com.una.a3.controller;


import com.una.a3.dto.DadosCalculoDto;
import com.una.a3.dto.ResultadoCalculoDto;
import com.una.a3.models.PainelSolar;
import com.una.a3.repository.PainelSolarRepository;
import com.una.a3.service.CalularPainelSolar;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/paineis-solares")
public class PainelSolarController {

    private final PainelSolarRepository repository;

    public PainelSolarController(PainelSolarRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<PainelSolar> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public PainelSolar getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("Painel solar não encontrado"));
    }

    @PostMapping
    public PainelSolar create(@RequestBody PainelSolar painelSolar) {
        return repository.save(painelSolar);
    }



    @PostMapping("/save-list")
    public Iterable<PainelSolar> saveList(@RequestBody Iterable<PainelSolar> paineisSolares) {
        return repository.saveAll(paineisSolares);
    }
    @PutMapping("/{id}")
    public PainelSolar update(@PathVariable Long id, @RequestBody PainelSolar painelSolar) {
        PainelSolar painelSolarExistente = repository.findById(id).orElseThrow(() -> new IllegalStateException("Painel solar não encontrado"));
        painelSolarExistente.setPotencia(painelSolar.getPotencia());
        painelSolarExistente.setMarca(painelSolar.getMarca());
        painelSolarExistente.setValor(painelSolar.getValor());
        painelSolarExistente.setPotencia(painelSolar.getPotencia());
        return repository.save(painelSolarExistente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/calcular")
    public ResultadoCalculoDto calcular(@RequestBody DadosCalculoDto dadosCalculo) {
        Iterable<PainelSolar> painelSolars = repository.findAll();
        List<PainelSolar> listaPaineis = new ArrayList<>();
        for (PainelSolar painelSolar : painelSolars) {
            listaPaineis.add(painelSolar);
        }
        return  CalularPainelSolar.calcularConsumoSolar(dadosCalculo,listaPaineis);
    }

}

