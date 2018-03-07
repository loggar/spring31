package com.loggar.controller.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loggar.util.common.StringUtil;

@Controller
@RequestMapping("/test/formdata")
public class FormDataController {
	@RequestMapping("/test1")
	@ResponseBody
	public String test1(@RequestParam String[] breaks) {
		String s = StringUtil.toString(breaks);

		System.out.println(s);

		return "ok";
	}
}
