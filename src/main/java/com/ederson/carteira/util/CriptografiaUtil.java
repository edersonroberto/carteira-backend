package com.ederson.carteira.util;

import java.util.Base64;

public class CriptografiaUtil {

	public static String criptografar(String senha) {
		return new String(Base64.getEncoder().encode(senha.getBytes()));
	}

	public static String descriptografar(String senha) {
		return new String(Base64.getDecoder().decode(senha.getBytes()));
	}

}
