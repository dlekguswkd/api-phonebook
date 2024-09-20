package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
	
	// http://localhost:9000/hello
	@ResponseBody
	@RequestMapping(value="hello", method= {RequestMethod.GET, RequestMethod.POST})
	public String hello() {
		System.out.println("HelloController.hello()");
		
		return "안녕하세요";
	}
	

}
