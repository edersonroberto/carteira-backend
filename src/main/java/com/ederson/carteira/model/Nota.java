package com.ederson.carteira.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ederson.carteira.enums.TipoOperacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "nota")
@Entity
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)
public class Nota implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Ativo ativo;
	
	@ManyToOne
	private Corretora corretora;
	
	@Column
	private LocalDate data;
	
	@Column(name = "operacao")
	@Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	private TipoOperacao operacao;
	@Column
	private Integer quantidade;
	
	@Column
	private BigDecimal valor;
	
	@Column
	private BigDecimal taxa;
	
	@Column
	private BigDecimal emolumento;
	@Column
	private BigDecimal corretagem;
	@Column
	private BigDecimal iss;
	
	@Formula("(valor * quantidade) + taxa + emolumento")
	private BigDecimal custoTotal;
	
}
