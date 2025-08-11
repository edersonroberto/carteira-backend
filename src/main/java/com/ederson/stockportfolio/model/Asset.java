package com.ederson.stockportfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ederson.stockportfolio.enums.AssetType;

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
public class Asset {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String ticket;
	@Column
	private String cnpj;
	
	@Enumerated(EnumType.STRING)
	private AssetType tipoAtivo;
	
	@ManyToOne
	private Administrator administrator;
	
	public Asset (Long id) {
		this.id = id;
	}

}
