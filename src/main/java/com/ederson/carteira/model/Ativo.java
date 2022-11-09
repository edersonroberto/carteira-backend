package com.ederson.carteira.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ederson.carteira.enums.TipoAtivo;

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
@Table(name = "ativo")
@Entity
public class Ativo {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private String ticket;
	@Column
	private String cnpj;
	
	@Enumerated(EnumType.STRING)
	private TipoAtivo tipoAtivo;
	
	@ManyToOne
	private Administradora administradora;
	
	public Ativo (Long id) {
		this.id = id;
	}

}
