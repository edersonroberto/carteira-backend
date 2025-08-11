package com.ederson.stockportfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.stockportfolio.model.Access;
import com.ederson.stockportfolio.repository.AcessoRepository;
import com.ederson.stockportfolio.util.CriptografiaUtil;

@Service
public class AcessoService {

	@Autowired
	AcessoRepository acessoRepository;
	
	public List<Access> listar() {
		return acessoRepository.findAll(); 
	}

	public Access incluir(Access acesso) {
					
		String criptografar = CriptografiaUtil.criptografar(acesso.getSenha());
	
		acesso.setSenha(criptografar);
		return acessoRepository.save(acesso);
	}

}
