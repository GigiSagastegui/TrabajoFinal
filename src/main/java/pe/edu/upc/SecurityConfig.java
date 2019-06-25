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

			//Gerente			
			.antMatchers("/gerentes/nuevo/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/gerentes/listar/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/gerentes/detalle/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/gerentes/ver/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/gerentes/buscar/**").access("hasRole('ROLE_ADMINISTRADOR')")
			//Administrador
			.antMatchers("/administradores/listar/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/administradores/detalle/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/administradores/ver/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/administradores/buscar/**").access("hasRole('ROLE_ADMINISTRADOR')")
			//Auditor
			.antMatchers("/auditores/nuevo/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/auditores/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/auditores/detalle/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/auditores/ver/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/auditores/buscar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_GERENTE')")
			
			//Area 
			.antMatchers("/areas/nuevo/**").access("hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/areas/listar/**").access("hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') ")
			.antMatchers("/areas/buscar/**").access("hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') ")
			.antMatchers("/areas/modificar/**").access("hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR')")
			
			//Informe			
			.antMatchers("/informes/nuevo/**").access("hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR') ")
			.antMatchers("/informes/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE') ")
			.antMatchers("/informes/buscar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE') ")
			.antMatchers("/informes/modificar/**").access("hasRole('ROLE_GERENTE')or hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/informes/ver/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			
			//Procesos			
			.antMatchers("/procesos/nuevo/**").access("hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/procesos/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/procesos/buscar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/procesos/modificar/**").access("hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/procesos/ver/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			
			//programa			
			.antMatchers("/programas/nuevo/**").access("hasRole('ROLE_AUDITOR')or hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/programas/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/programas/modificar/**").access("hasRole('ROLE_AUDITOR')or hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/programas/ver/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			
			//Tarea
			
			.antMatchers("/tareas/nuevo/**").access("hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR')")
			.antMatchers("/tareas/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/tareas/modificar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_GERENTE')")
			
			//Auditoria
			
			.antMatchers("/auditorias/nuevo/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/auditorias/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/auditorias/buscar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/auditorias/modificar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/auditorias/ver/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			
			//Integrantes
			
			.antMatchers("/equipos/nuevo/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/equipos/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/equipos/buscar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/equipos/modificar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			
			//Detalle
			
			.antMatchers("/detalles/nuevo/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/detalles/listar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/detalles/buscar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			.antMatchers("/detalles/modificar/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR')")
			.antMatchers("/detslles/ver/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')")
			
			//otros
			
			.antMatchers("/bienvenido/**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GERENTE')").and()

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
