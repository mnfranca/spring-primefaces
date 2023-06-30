package br.org.mnf.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.mnf.model.usuario.Usuario;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -4917391303392020746L;

	private Usuario usuario;

	private Set<GrantedAuthority> authorities;

	public UserDetailsImpl(Usuario usuario) {
		super();
		this.usuario = usuario;
		this.authorities = usuario.getPermissoes()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNome()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getLogin();
	}

	@Override
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getMatricula() {
		return usuario.getMatricula();
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

	@Override
	public String toString() {
		return "UserDetailsImpl [usuario=" + usuario + "]";
	}

}
