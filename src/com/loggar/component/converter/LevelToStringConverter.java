package com.loggar.component.converter;

import org.springframework.core.convert.converter.Converter;

import com.loggar.user.member.Level;


public class LevelToStringConverter implements Converter<Level, String> {

	@Override
	public String convert(Level source) {
		return Integer.toString(source.intValue());
	}
}
