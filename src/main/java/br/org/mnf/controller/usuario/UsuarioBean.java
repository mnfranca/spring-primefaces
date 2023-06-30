package br.org.mnf.controller.usuario;

import java.io.Serializable;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.org.mnf.config.UserDetailsImpl;

@Named
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -4613056343642689563L;
	
	public UserDetailsImpl getUserDetails() {
		return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public String getUsername() {
		return getUserDetails().getUsername();
	}
	
	public Set<GrantedAuthority> getAuthorities() {
		return getUserDetails().getAuthorities();
	}

}
