package com.ederson.stockportfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.stockportfolio.model.Access;
import com.ederson.stockportfolio.service.AccessService;

@RestController
@RequestMapping("/access")
public class AccessController {
	
	@Autowired
	private AccessService accessService;
	
	public List<Access> list() {
		return accessService.list();
	}
	
	public Access incluir(@RequestBody Access access) {
		return accessService.insert(access);
	}

}
