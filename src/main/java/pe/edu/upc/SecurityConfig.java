package pe.edu.upc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select * from (select user_usuario as username, password_usuario as password, "
						+ "estado_usuario as enabled from persona) as users where username = ? ")
				.authoritiesByUsernameQuery(
						"select * from (select user_usuario as username, tipo_usuario as AUTHORITY from persona)"
								+ " as authorities where username = ? ")
				.passwordEncoder(passwordEncoder);
	}

	public void configure(HttpSecurity http) throws Exception {

		try {
			http.authorizeRequests()

			//Area
			.antMatchers("/areas/nuevo/**").access("hasRole('ROLE_ADMINISTRADOR') ")
			.antMatchers("/areas/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE') ")
			//Informe
			
			.antMatchers("/informes/nuevo/**").access("hasRole('ROLE_GERENTE') ")
			.antMatchers("/informes/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE') ")
			
			.antMatchers("/gerentes/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/auditores/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/administradores/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/tareas/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/detalles/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/procesos/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/auditorias/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/bienvenido/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			//equipo
			
			.antMatchers("/equipos/nuevo/**").access("hasRole('ROLE_AUDITOR') ")
			.antMatchers("/equipos/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')").and()

					.formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check")
					.defaultSuccessUrl("/bienvenido/").failureUrl("/login?error").usernameParameter("usuario")
					.passwordParameter("clave").and().logout().logoutSuccessUrl("/login?logout")
					.logoutUrl("/j_spring_security_logout").and().exceptionHandling().accessDeniedPage("/403").and()
					.csrf().disable();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
