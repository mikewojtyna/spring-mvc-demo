package pl.sdacademy.spring.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {
	@GetMapping
	public String messageForm(@ModelAttribute Message message) {
		return "message-form";
	}

	@PostMapping
	public String createMessage(@ModelAttribute Message message) {
		return "show-message";
	}
}
