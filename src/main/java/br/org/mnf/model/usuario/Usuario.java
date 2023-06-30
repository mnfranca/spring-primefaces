package br.org.mnf.model.usuario;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario", schema = "imo")
@NamedQueries({ @NamedQuery(name = "Usuario.byLogin", query = "FROM Usuario u WHERE u.login = :login") })
public class Usuario implements Serializable {

	private static final long serialVersionUID = 319143293251491084L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login", unique = true)
	@NotBlank(message = "O nome de usuário deve ser informado")
	private String login;

	@Column(name = "senha")
	@NotBlank(message = "A senha deve ser informada")
	private String senha;

	@ManyToMany
	@JoinTable(name = "usuario_permissao",
			schema = "imo",
			joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_permissao", referencedColumnName = "id"))
	private Set<Permissao> permissoes;

	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + "]";
	}

}
