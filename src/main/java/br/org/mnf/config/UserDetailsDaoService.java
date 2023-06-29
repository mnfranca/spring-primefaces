package br.org.mnf.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.org.mnf.dao.user.UserDao;
import br.org.mnf.model.usuario.Usuario;
import br.org.mnf.utils.Messages;

public class UserDetailsDaoService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = userDao.getByLogin(username);
		if (!user.isPresent()) 
			throw new UsernameNotFoundException(Messages.get("error.invalid.user"));
		return new MyUserDetails(user.get());
	}

}
