package br.org.mnf.utils;

import java.util.ResourceBundle;

public class Messages {
	
	public static String get(String key) {
		try {
			return ResourceBundle.getBundle("messages").getString(key);
		} catch (Exception e) {
			return key;
		}
	}

}
