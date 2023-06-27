package br.org.mnf.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.org.mnf.dao.user.UserDao;
import br.org.mnf.model.user.User;
import br.org.mnf.utils.Messages;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userDao.getByUsername(username);
		if (!user.isPresent()) 
			throw new UsernameNotFoundException(Messages.get("error.invalid.user"));
		return new MyUserDetails(user.get());
	}

}
