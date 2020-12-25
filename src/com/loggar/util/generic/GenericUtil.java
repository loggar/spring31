package com.loggar.util.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * extract implemented class type from clazz with Generic Card.
 *
 */
public class GenericUtil {
	public static Class<?> getClassOfGenericTypeIn(Class<?> clazz, int index) {
		ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
		Type wantedClassType = genericSuperclass.getActualTypeArguments()[index];
		if (wantedClassType instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) wantedClassType).getRawType();
		}

		return (Class<?>) wantedClassType;
	}
}
