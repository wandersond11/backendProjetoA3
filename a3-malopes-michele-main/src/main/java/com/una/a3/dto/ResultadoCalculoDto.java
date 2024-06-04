package com.una.a3.dto;

public class ResultadoCalculoDto {

    private final int numeroPaineis;
    private final double custoTotalSistema;
    private final double areaTotal;
    private final double economiaEnergiaMensal;

    private final double economiaFinanceiraMensal;

    private final String  descricao;



    public ResultadoCalculoDto(final int numeroPaineis,
                               final double custoTotalSistema,
                               final double areaTotal,
                               final double economiaEnergiaMensal,
                               final double economiaFinanceiraMensal,
                               final String descricao) {
        this.numeroPaineis = numeroPaineis;
        this.custoTotalSistema = custoTotalSistema;
        this.areaTotal = areaTotal;
        this.economiaEnergiaMensal = economiaEnergiaMensal;
        this.economiaFinanceiraMensal = economiaFinanceiraMensal;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNumeroPaineis() {
        return numeroPaineis;
    }

    public double getCustoTotalSistema() {
        return custoTotalSistema;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public double getEconomiaEnergiaMensal() {
        return economiaEnergiaMensal;
    }

    public double getEconomiaFinanceiraMensal() {
        return economiaFinanceiraMensal;
    }
}
