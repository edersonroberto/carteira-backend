package com.ederson.stockportfolio.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EncryptionUtilTest {

	@Test
	void testCriptografar() {
		String encrypted = EncryptionUtil.encrypt("plain");
		assertNotNull(encrypted);
		assertEquals("cGxhaW4=", encrypted);
		System.out.println("Encrypted: " + encrypted);
	}

	@Test
	void testDescriptografar() {
		String decrypt = EncryptionUtil.descriptografar("cGxhaW4=");
		assertNotNull(decrypt);
		assertEquals("plain", decrypt);
	}

}
