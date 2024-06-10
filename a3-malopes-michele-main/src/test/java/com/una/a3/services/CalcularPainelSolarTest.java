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
        PainelSolar painel1 = new PainelSolar(1L, "Painel A", 250.0, 2.5, 160.0 ); // ID, descrição, potência, valor, área, custo
        PainelSolar painel2 = new PainelSolar(2L, "Painel B", 300.0, 2.8, 180.0 );
        PainelSolar painel3 = new PainelSolar(3L, "Painel C", 350.0, 3.0, 200.0);

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
