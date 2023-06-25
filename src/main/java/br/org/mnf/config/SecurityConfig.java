package br.org.mnf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.authorizeRequests()
				.antMatchers("/resources/**", "/public/**", "/javax.faces.resource/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin(form -> form.loginPage("/login.xhtml")
						.defaultSuccessUrl("/dashboard.xhtml", true)
						.failureUrl("/login.xhtml?error=true")
						.permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/login.xhtml"))
				.csrf()
				.disable()
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.withUsername("user1")
				.password(passwordEncoder().encode("123"))
				.roles("USER")
				.build();
		UserDetails user2 = User.withUsername("user2")
				.password(passwordEncoder().encode("123"))
				.roles("USER")
				.build();
		UserDetails admin = User.withUsername("admin")
				.password(passwordEncoder().encode("123"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user1, user2, admin);
	}
	
}
