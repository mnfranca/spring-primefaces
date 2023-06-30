package br.org.mnf.dao.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.org.mnf.dao.DaoImpl;
import br.org.mnf.dao.DaoParams;
import br.org.mnf.model.usuario.Usuario;

@Repository
public class UsuarioDao extends DaoImpl<Usuario, Long> {

	public UsuarioDao() {
		super(Usuario.class);
	}

	public Optional<Usuario> getByLogin(String login) {
		return findByNamedQuery("Usuario.byLogin", DaoParams.of("login", login).build()).stream()
				.findFirst();
	}

}
