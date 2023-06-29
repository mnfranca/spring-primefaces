package br.org.mnf.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.mnf.model.usuario.Usuario;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1663836995254990605L;
	
	private Usuario user;

	public MyUserDetails(Usuario user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getPermissoes()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNome()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getSenha();
	}

	@Override
	public String getUsername() {
		return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
