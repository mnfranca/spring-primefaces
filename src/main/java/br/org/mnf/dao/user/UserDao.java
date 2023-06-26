package br.org.mnf.dao.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.org.mnf.dao.DaoImpl;
import br.org.mnf.dao.DaoParams;
import br.org.mnf.model.user.User;

@Repository
public class UserDao extends DaoImpl<User, Long> {

	public UserDao() {
		super(User.class);
	}

	public Optional<User> getByUsername(String username) {
		return findByNamedQuery("User.byUsername", DaoParams.of("username", username).build()).stream()
				.findFirst();
	}

}
