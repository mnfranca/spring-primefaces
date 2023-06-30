package br.org.mnf.config;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.org.mnf.model.usuario.Usuario;

@Component
public class AuthoritiesService {

	/**
	 * Retorna lista de permissões adicionais do usuário, como:
	 * <ul>
	 * <li>Por situação funcional do usuário.</li>
	 * </ul> 
	 * 
	 * @param usuario
	 * @return
	 */
	public Set<GrantedAuthority> getAdditionalAuthorities(Usuario usuario) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.addAll(fromSituacaoFuncional(usuario));
		return authorities;
	}

	private Set<GrantedAuthority> fromSituacaoFuncional(Usuario usuario) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		if (Objects.equals(usuario.getId(), new Long(1)))
			authorities.add(new SimpleGrantedAuthority("SERVIDOR"));
		if (Objects.equals(usuario.getId(), new Long(2)))
			authorities.add(new SimpleGrantedAuthority("AUTORIDADE"));
		if (Objects.equals(usuario.getId(), new Long(3)))
			authorities.add(new SimpleGrantedAuthority("ESTAGIARIO"));
		return authorities;
	}

}
