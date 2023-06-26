package br.org.mnf.dao;

import java.util.HashMap;
import java.util.Map;

public final class DaoParams {
	
	private Map<String, Object> parameters = new HashMap<>();
	
	public static DaoParams of(String key, Object value) {
		DaoParams instance = new DaoParams();
		instance.parameters.put(key, value);
		return instance;
	}
	
	public DaoParams and(String key, Object value) {
		parameters.put(key, value);
		return this;
	}
	
	public Map<String, Object> build() {
		return parameters;
	}

}
