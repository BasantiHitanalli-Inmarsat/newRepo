package controller;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping ("/basanti")
public class HelloWorldController {

	@RequestMapping(value="/say", method = RequestMethod.POST)
	public void sayHello(){
		System.out.println("Hello world !!");
	}
}
