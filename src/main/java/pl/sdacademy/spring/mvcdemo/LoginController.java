package pl.sdacademy.spring.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller simply renders the login view. The actual authentication
 * is performed by
 * {@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter}.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping
	public String login() {
		return "login";
	}
}
