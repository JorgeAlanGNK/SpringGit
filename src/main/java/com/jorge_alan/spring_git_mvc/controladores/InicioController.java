package com.jorge_alan.spring_git_mvc.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

	@GetMapping
	public String Index() {
		return "index.html";
	}

}
