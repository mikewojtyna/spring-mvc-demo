package pl.sdacademy.spring.mvcdemo;

import org.springframework.security.config.annotation.authentication.builders
	.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration
	.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration
	.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
			// secure only /secured path
				antMatchers("/secured").authenticated()
			// enable form login page (needs a LoginController to
			// handle the view rendering
			.and().formLogin().loginPage("/login")
			// configure form POST params
			.usernameParameter("user").passwordParameter("pass")
			// disable CSRF protection (to remove extra
			// complexity) - should be enabled in production
			.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws
		Exception {
		// configure in-memory authentication manager to support only
		// one user with no-op password encoder (note the "{noop}" in
		// password)
		auth.inMemoryAuthentication().
			withUser("goobar").password("{noop}password").roles
			("USER");
	}
}
