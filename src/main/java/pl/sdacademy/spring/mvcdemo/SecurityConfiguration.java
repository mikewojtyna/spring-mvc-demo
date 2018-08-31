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
		// for now disable the http security
		http.authorizeRequests().anyRequest().permitAll().and().csrf()
			.disable();

		// uncomment to enable basic auth
		//http.authorizeRequests().anyRequest().authenticated().and()
		//	.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws
		Exception {
		auth.inMemoryAuthentication().
			withUser("goobar").password("{noop}password").roles
			("USER");
	}
}
