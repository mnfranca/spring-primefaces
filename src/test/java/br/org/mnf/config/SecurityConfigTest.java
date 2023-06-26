package br.org.mnf.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class SecurityConfigTest {

	@Test
	void testCreatePassword() {
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
