package com.ederson.stockportfolio.util;

import java.util.Base64;

public class EncryptionUtil {

	public static String criptografar(String password) {
		return new String(Base64.getEncoder().encode(password.getBytes()));
	}

	public static String descriptografar(String password) {
		return new String(Base64.getDecoder().decode(password.getBytes()));
	}

}
