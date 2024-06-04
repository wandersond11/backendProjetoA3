package com.una.a3.service;

import com.una.a3.dto.DadosCalculoDto;
import com.una.a3.dto.ResultadoCalculoDto;
import com.una.a3.models.PainelSolar;

import java.util.List;
import java.util.Objects;

public class CalularPainelSolar {


    private static double calcularGeracaoDiariaPainel(double horasSol, PainelSolar modulo, int tensao) {
        // Fator de desempenho (entre 0,75 e 0,85)
        double fatorDesempenho = 0.80;

        // Cálculo da geração diária por painel
        double geracaoDiariaPainel = (modulo.getPotencia() * horasSol * fatorDesempenho) / 1000;

        // Ajuste para a tensão do sistema (110V ou 220V)
        if (tensao == 110) {
            geracaoDiariaPainel *= 0.85; // Fator de conversão para 110V (aproximado)
        }

        return geracaoDiariaPainel;
    }

    private static int calcularNumeroPaineis(double mediaKwh, double geracaoDiariaPainel) {
        // Considerando 30 dias por mês
        int diasMes = 30;

        // Cálculo do número de painéis necessários
        return (int) Math.ceil(mediaKwh / (geracaoDiariaPainel * diasMes)); // Arredondar para cima
    }

    private static double calcularCustoTotalSistema(int numeroPaineis, PainelSolar modulo) {
        // Cálculo do custo total do sistema
        return numeroPaineis * modulo.getValor();
    }

    private static double calcularAreaPainel(int numeroPaineis, PainelSolar modulo) {
        // Cálculo da área total dos painéis
        return numeroPaineis * modulo.getArea();
    }

    private static int calcularMelhorPainel(double horas, int tensao, List<PainelSolar> modulos, DadosCalculoDto dadosCalculoDto) {
        int melhorIndice = -1; // Inicializa o índice do melhor painel como -1
        double melhorRelacao = 0; // Inicializa a melhor relação como 0

        for (int i = 0; i < modulos.size(); i++) {
            PainelSolar modulo = modulos.get(i);

            // Verifica se o ID do painel foi especificado e se é o mesmo do painel atual
            if (dadosCalculoDto.getIdPainel() != null && !Objects.equals(modulo.getId(), dadosCalculoDto.getIdPainel())) {
                continue; // Pula para a próxima iteração se o ID não corresponder
            }

            // Calcula a geração diária de energia do módulo
            double geracaoDiaria = calcularGeracaoDiariaPainel(horas, modulo, tensao);

            // Calcula a geração mensal de energia do módulo
            double geracaoMensal = geracaoDiaria * 30; // Assumindo 30 dias no mês

            // Calcula a relação entre a energia gerada e o custo do módulo
            double relacao = geracaoMensal / modulo.getValor();

            // Verifica se essa relação é melhor que a atual
            if (relacao > melhorRelacao) {
                melhorRelacao = relacao; // Atualiza a melhor relação
                melhorIndice = i; // Atualiza o índice do melhor painel
            }
        }

        return melhorIndice; // Retorna o índice do melhor painel encontrado
    }


    public static ResultadoCalculoDto calcularConsumoSolar(DadosCalculoDto dadosCalculoDto, List<PainelSolar> modulos) {
        // Validação dos dados de entrada
        if (dadosCalculoDto.getMediaKwh() <= 0 || dadosCalculoDto.getValorKwh() <= 0 || dadosCalculoDto.getHorasSol() <= 0 || (dadosCalculoDto.getTensao() != 110 && dadosCalculoDto.getTensao() != 220)) {
            throw new IllegalArgumentException("Dados inválidos. Preencha todos os campos corretamente.");
        }

        int indiceMelhorPainel = calcularMelhorPainel(dadosCalculoDto.getHorasSol(), dadosCalculoDto.getTensao(), modulos,dadosCalculoDto);
        PainelSolar melhorModulo = modulos.get(indiceMelhorPainel);
        System.out.println(melhorModulo.getDescricao());
        // Cálculo da geração diária por painel
        double geracaoDiariaPainel = calcularGeracaoDiariaPainel(dadosCalculoDto.getHorasSol(), melhorModulo, dadosCalculoDto.getTensao());
        System.out.println(geracaoDiariaPainel);
        // Cálculo do número de painéis necessários
        int numeroPaineis = calcularNumeroPaineis(dadosCalculoDto.getMediaKwh(), geracaoDiariaPainel);
        System.out.println(numeroPaineis);
        // Cálculo do custo total do sistema
        double custoTotalSistema = calcularCustoTotalSistema(numeroPaineis, melhorModulo);
        System.out.println(numeroPaineis);
        // Cálculo da área total dos painéis
        double areaTotal = calcularAreaPainel(numeroPaineis, melhorModulo);
        System.out.println(areaTotal);
        // Cálculo da energia gerada pelo sistema solar por mês
        double energiaGeradaPorMes = geracaoDiariaPainel * 30; // Assumindo 30 dias no mês
        System.out.println(energiaGeradaPorMes);
        // Cálculo da economia de energia mensal
        double economiaEnergiaMensal = dadosCalculoDto.getMediaKwh() - energiaGeradaPorMes;
        System.out.println(economiaEnergiaMensal);
        // Cálculo da economia financeira mensal
        double economiaFinanceiraMensal = economiaEnergiaMensal * dadosCalculoDto.getValorKwh();

        // Retorno do resultado
        return new ResultadoCalculoDto(numeroPaineis, custoTotalSistema, areaTotal, economiaEnergiaMensal, economiaFinanceiraMensal,melhorModulo.getDescricao());
    }
}
