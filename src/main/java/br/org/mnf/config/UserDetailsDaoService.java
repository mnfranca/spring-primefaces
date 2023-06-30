package br.org.mnf.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.org.mnf.dao.user.UsuarioDao;
import br.org.mnf.model.usuario.Usuario;
import br.org.mnf.utils.Messages;

public class UserDetailsDaoService implements UserDetailsService {

	@Autowired
	private UsuarioDao userDao;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = userDao.getByLogin(username);
		if (!usuario.isPresent())
			throw new UsernameNotFoundException(Messages.get("error.invalid.user"));
		
		UserDetailsImpl userDetails = new UserDetailsImpl(usuario.get());
		userDetails.getAuthorities().addAll(authoritiesService.getAdditionalAuthorities(usuario.get()));
		return userDetails;

	}

}
