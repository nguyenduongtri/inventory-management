package inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
//	@RequestMapping(value="/index", method = RequestMethod.GET) Spring version 4.3 trở xuống
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	

}
