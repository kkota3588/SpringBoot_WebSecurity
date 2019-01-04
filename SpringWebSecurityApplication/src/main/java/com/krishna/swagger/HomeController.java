package com.krishna.swagger;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * <h1>Swagger Home Controller Configuration</h1>
 * <p>
 * This class used to configure home controller in swagger file
 * </p>
 * 
 * @author GBOS Integration Technologies
 * @version 0.1
 * @since 20 May,2017
 * */
@Controller
public class HomeController {

	/**
	 * Description - This is the method to set URI for swagger-UI file
	 */
	@RequestMapping(value = "/")
	public String index() {

		return "redirect:swagger-ui.html";
	}
}
