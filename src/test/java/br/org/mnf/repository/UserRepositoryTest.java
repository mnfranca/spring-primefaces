package br.org.mnf.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.org.mnf.Application;
import br.org.mnf.model.User;

@SpringBootTest(classes = Application.class)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void givenCustomRepository_whenInvokeCustomFindMethod_thenEntityIsFound() {
		User user = new User();
		user.setUsername("userName");

		User persistedUser = userRepository.save(user);

		assertEquals(persistedUser, userRepository.customFindMethod(user.getId()));
	}

}
