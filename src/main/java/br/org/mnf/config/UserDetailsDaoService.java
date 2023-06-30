package br.org.mnf.config;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.org.mnf.dao.user.UsuarioDao;
import br.org.mnf.model.usuario.Usuario;
import br.org.mnf.utils.Messages;

public class UserDetailsDaoService implements UserDetailsService {

	@Autowired
	private UsuarioDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = userDao.getByLogin(username);
		if (!usuario.isPresent())
			throw new UsernameNotFoundException(Messages.get("error.invalid.user"));

		return User.withUsername(usuario.get().getLogin())
				.password(usuario.get().getSenha())
				.authorities(getAuthorities(usuario.get()))
				.build();

	}

	private Collection<GrantedAuthority> getAuthorities(Usuario usuario) {
		return usuario.getPermissoes()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNome()))
				.collect(Collectors.toList());
	}

}
