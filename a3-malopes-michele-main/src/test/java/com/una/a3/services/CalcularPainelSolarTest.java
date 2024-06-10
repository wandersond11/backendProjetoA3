package com.una.a3.services;

import com.una.a3.controller.UsuarioController;
import com.una.a3.dto.DadosCalculoDto;
import com.una.a3.dto.ResultadoCalculoDto;
import com.una.a3.models.PainelSolar;
import com.una.a3.repository.UsuarioRepository;
import com.una.a3.service.CalularPainelSolar;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static com.una.a3.service.CalularPainelSolar.calcularConsumoSolar;
import static org.junit.jupiter.api.Assertions.*;

class CalcularPainelSolarTest {

    @InjectMocks
    private CalularPainelSolar calcularConsumoSolar;

    @Test
    void testCalcularConsumoSolar() {
        // Dados de entrada para o teste
        DadosCalculoDto dadosCalculoDto = new DadosCalculoDto(900,0.91,5,220,null);

        // Lista de painéis solares disponíveis
        PainelSolar painel1 = new PainelSolar(); // ID, descrição, potência, valor, área, custo
        painel1.setId(1l);
        painel1.setDescricao("Painel A");
        painel1.setPotencia(250.0);
        painel1.setArea(2.5);
        painel1.setValor(220.0);
        PainelSolar painel2 = new PainelSolar();
        painel2.setId(2l);
        painel2.setDescricao("Painel B");
        painel2.setPotencia(300.0);
        painel2.setArea(2.8);
        painel2.setValor(180.0);
        PainelSolar painel3 = new PainelSolar();
        painel3.setId(3l);
        painel3.setDescricao("Painel C");
        painel3.setPotencia(350.0);
        painel3.setArea(3.0);
        painel3.setValor(200.0);

        List<PainelSolar> modulos = Arrays.asList(painel1, painel2, painel3);

        // Executar o cálculo do consumo solar
        ResultadoCalculoDto resultado = calcularConsumoSolar(dadosCalculoDto, modulos);

        // Verificar os resultados
        assertNotNull(resultado);
        assertEquals(22, resultado.getNumeroPaineis()); // Exemplo de número esperado de painéis
        assertEquals(4400.0, resultado.getCustoTotalSistema(), 0.01); // Custo total esperado do sistema
        assertEquals(66.0, resultado.getAreaTotal(), 0.01); // Área total esperada dos painéis
        assertEquals(858.0, resultado.getEconomiaEnergiaMensal(), 0.01); // Economia de energia mensal esperada
        assertEquals(780.78, resultado.getEconomiaFinanceiraMensal(), 0.01); // Economia financeira mensal esperada
        assertEquals("Painel C 350.0W", resultado.getDescricao()); // Descrição do melhor painel
    }
}
