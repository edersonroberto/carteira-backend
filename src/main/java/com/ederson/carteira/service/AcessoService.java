package com.ederson.carteira.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.carteira.model.Acesso;
import com.ederson.carteira.repository.AcessoRepository;
import com.ederson.carteira.util.CriptografiaUtil;

@Service
public class AcessoService {

	@Autowired
	AcessoRepository acessoRepository;
	
	public List<Acesso> listar() {
		return acessoRepository.findAll(); 
	}

	public Acesso incluir(Acesso acesso) {
					
		String criptografar = CriptografiaUtil.criptografar(acesso.getSenha());
	
		acesso.setSenha(criptografar);
		return acessoRepository.save(acesso);
	}

}
