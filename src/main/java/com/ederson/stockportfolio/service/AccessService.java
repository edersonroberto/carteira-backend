package com.ederson.stockportfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.stockportfolio.model.Access;
import com.ederson.stockportfolio.repository.AccessRepository;
import com.ederson.stockportfolio.util.EncryptionUtil;

@Service
public class AccessService {

	@Autowired
	AccessRepository acessoRepository;
	
	public List<Access> list() {
		return acessoRepository.findAll(); 
	}

	public Access insert(Access access) {
					
		String criptografar = EncryptionUtil.criptografar(access.getPassword());
	
		access.setPassword(criptografar);
		return acessoRepository.save(access);
	}

}
