package pl.sdacademy.spring.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secured")
public class SecuredController {
	@GetMapping
	public String secured() {
		return "secured";
	}
}
