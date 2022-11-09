package com.ederson.carteira.dto;

import java.math.BigDecimal;
import java.util.List;

import com.ederson.carteira.model.Nota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalheAtivoDto {

	private String ticket;
	private Long quantidade;
	private BigDecimal menorPreco;
	private BigDecimal maiorPreco;
	private BigDecimal custoTotal;
	private BigDecimal precoMedio;
	private BigDecimal valorCota;
	private BigDecimal ganhoRealizado;
	private BigDecimal ganhoComDividendo;
	private String tipoAtivo;
	private List<Nota> notas;
}
