package com.in28minutes.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*Like in servlet we need to extend httpServlet, in spring we can use
@Controller annotation to handle the request from the web browser.
*/

@Controller
//@SessionAttributes("name") // to make anything avialable in the session, just give the model key, or model
							// name.
public class WelcomeController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
//	@ResponseBody
	public String showLoginPage(ModelMap model) {
	model.put("name","in28Minutes");
		
		return "welcome";
	}


}
