package com.loggar.component.converter;

import org.springframework.core.convert.converter.Converter;

import com.loggar.user.member.Level;


public class StringToLevelConverter implements Converter<String, Level> {

	@Override
	public Level convert(String source) {
		return Level.valueOf(Integer.parseInt(source));
	}
}
