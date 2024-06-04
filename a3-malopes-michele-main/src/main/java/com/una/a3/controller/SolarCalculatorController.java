package com.una.a3.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/painel")
public class SolarCalculatorController {

    @GetMapping("/calculate")
    public String calculate(@RequestParam(name = "consumoMensal") Double consumoMensal,
                            @RequestParam(name = "valorKwh") Double valorKwh,
                            @RequestParam(name = "horasSolDia") Double horasSolDia) {

        // Cálculo do consumo de energia em kWh por dia
        Double consumoDiario = consumoMensal * valorKwh / 30;

        // Cálculo da produção diária de energia por painel solar
        Double producaoPainelDia = 150.0 * horasSolDia / 1000.0;

        // Número de painéis solares
        Double numeroPaineis = Math.ceil(consumoDiario / producaoPainelDia);

        return "Número de painéis solares de 150W: " + numeroPaineis;
    }
}
