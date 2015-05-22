package com.now.cloudphoto.utilities;

public class Util {
	@SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>)aClass;
    }
	
	public static boolean isStringNullOrEmpty(String s){
		return s == null || s.equals("");
	}
}
