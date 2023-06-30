package br.org.mnf.config;

import java.util.Hashtable;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.org.mnf.dao.user.UsuarioDao;
import br.org.mnf.model.usuario.Usuario;
import br.org.mnf.utils.Messages;

public class UserDetailsADService implements UserDetailsService {

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

	/**
	 * Verifica se o usuario <i>user</i> com a senha <i>password</i> existe dentro
	 * do dominio.
	 * 
	 * @param activeDirectoryAddress URL/IP da máquina onde roda o Active Directory
	 * @param user                   Nome do usuário dentro do domínio
	 * @param password               Senha do usuário (String simples, sem nenhum
	 *                               tipo de criptografia/hashing)
	 */
	@SuppressWarnings("rawtypes")
	public static boolean logon(String activeDirectoryAddress, String user, String password) {
		try {
			// set up the LDAP parameters
			Hashtable<Object, Object> env = new Hashtable<Object, Object>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, activeDirectoryAddress);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.REFERRAL, "follow");
			env.put(Context.SECURITY_PRINCIPAL, user);
			env.put(Context.SECURITY_CREDENTIALS, password);

			DirContext ctx = new InitialDirContext(env);

			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "distinguishedName",
					"sn",
					"givenname",
					"mail",
					"telephonenumber", "canonicalName", "userAccountControl", "accountExpires" };
			constraints.setReturningAttributes(attrIDs);
			NamingEnumeration answer = ctx.search("dc=tre,dc=ms,dc=jus,dc=br", "sAMAccountName=" + user,
					constraints);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				System.out.println(attrs.get("distinguishedName"));
				System.out.println(attrs.get("givenname"));
				System.out.println(attrs.get("sn"));
				System.out.println(attrs.get("mail"));
				System.out.println(attrs.get("telephonenumber"));
				System.out.println(attrs.get("canonicalName"));
				System.out.println(attrs.get("userAccountControl"));
				System.out.println(attrs.get("accountExpires"));
			} else {
				throw new Exception("Invalid User");
			}

			ctx.close();

			return true;
		} catch (Exception e) {
			// if failure, tell us about errors
			e.printStackTrace();

			return false;
		}
	}

	@SuppressWarnings("unused")
	private static SearchControls getSimpleSearchControls() {
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchControls.setTimeLimit(30000);
		// String[] attrIDs = {"objectGUID"};
		// searchControls.setReturningAttributes(attrIDs);
		return searchControls;
	}

	/**
	 * Verifica se o usuario <i>user</i> com a senha <i>password</i> existe As
	 * informacoes sobre AD devem constar em um arquivo "activedirectory.properties"
	 * que deve ficar na raiz dos pacotes da aplica??o com as seguintes
	 * propriedades: ad.server = url ou nome do servidor do AD ad.domain = nome do
	 * dominio onde deve ser consultado a exist?ncia dos usu?rios
	 * 
	 * @param user     Nome do usuario dentro do dom?nio
	 * @param password Senha do usuario (String simples, sem nenhum tipo de
	 *                 criptografia/hashing)
	 */
	public static boolean logon(String user, String password) {

		if (!user.contains("@tre-ms.jus.br"))
			user = user + "@tre-ms.jus.br";

		ResourceBundle valores = ResourceBundle.getBundle("activedirectory");
		String ldapServer = valores.getString("ldap.url");

		System.out.println("Server Ldap: " + ldapServer);

		return logon(ldapServer, user, password);

	}

	public static void main(String args[]) {
		String userID = "marcelo.franca";
		String pwd = "123";

		if (logon(userID, pwd)) {
			System.out.println("RESULTADO: Autenticou com sucesso!");
		} else {
			System.out.println("RESULTADO: Falha no login!");
		}
	}

}
