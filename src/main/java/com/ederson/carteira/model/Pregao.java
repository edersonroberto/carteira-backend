package com.ederson.carteira.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Pregao {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Ativo ativo; 
	
	@Column
	private LocalDate dataPregao;
	
	@Column(name = "vl_abertura")
	private BigDecimal valorAbertura;
	
	@Column(name = "vl_fechamento")
	private BigDecimal valorFechamento;
	
	@Column(name = "vl_minimo")
	private BigDecimal valorMinimo;
	
	@Column(name = "vl_maximo")
	private BigDecimal valorMaximo;

}
