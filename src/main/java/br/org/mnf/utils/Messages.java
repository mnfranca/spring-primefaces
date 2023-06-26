package br.org.mnf.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
	
	public static String get(String text) {
		return ResourceBundle.getBundle("messages", Locale.getDefault()).getString(text);
	}

}
