package com.loggar.util.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Reflection Utils
 * @author loggar
 *
 */
public class LoggarReflectionUtil {
	/**
	 * extract implemented class type from a actual field.
	 * @param field
	 * @return
	 */
	public static Class<?> getGenericType(Field field) {
		Class<?> fClass = field.getType();
		if(!Collection.class.isAssignableFrom(fClass)) return fClass;
		ParameterizedType type = (ParameterizedType) field.getGenericType();
		return (Class<?>)type.getActualTypeArguments()[0];
	}
	
	/**
	 * extract implemented class type from clazz with Generic Card.
	 * @param field
	 * @return
	 */
	public static Class<?> getClassOfGenericTypeIn(Class<?> clazz, int index) {
		ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
		Type wantedClassType = genericSuperclass.getActualTypeArguments()[index];
		
		if (wantedClassType instanceof ParameterizedType) return (Class<?>) ((ParameterizedType) wantedClassType).getRawType();
		else return (Class<?>) wantedClassType;
	}
	
	
}
