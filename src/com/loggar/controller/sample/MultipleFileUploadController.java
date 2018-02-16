package com.loggar.controller.sample;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.loggar.util.common.StringUtil;

@Controller
@RequestMapping("/test/multiple_file_upload")
public class MultipleFileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(MultipleFileUploadController.class);

	@RequestMapping("/upload_1")
	@ResponseBody
	public ModelMap upload_1(ModelMap model, MultipartRequest multipartRequest) {
		logger.debug("{}", StringUtil.debugMap(multipartRequest.getFileMap()));

		List<MultipartFile> files = multipartRequest.getFiles("files_01");

		int i = 1;
		for (MultipartFile f : files) {
			logger.debug("{}, {}", f.getOriginalFilename(), f);
			model.addAttribute(String.valueOf(i++), f.getOriginalFilename());
		}

		return model;
	}
}
