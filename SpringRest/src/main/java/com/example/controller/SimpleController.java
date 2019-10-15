package com.example.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/hello")
public class SimpleController {

	@ResponseBody
	@RequestMapping(method = GET, produces = "application/json")
	public String demo() {
		return "{\"hello\":\"world\"}";
	}
}
