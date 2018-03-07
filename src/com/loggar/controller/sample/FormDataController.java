package com.loggar.controller.sample;

import java.util.List;

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
		System.out.println(StringUtil.toString(breaks));
		return "test1 ok";
	}

	@RequestMapping("/test2")
	@ResponseBody
	public String test2(@RequestParam("breaks") List<String> breakList) {
		System.out.println(StringUtil.debugList(breakList));
		return "test2 ok";
	}
}
