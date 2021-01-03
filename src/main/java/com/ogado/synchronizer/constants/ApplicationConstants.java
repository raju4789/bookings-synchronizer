package com.ogado.synchronizer.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {
	
	public static final String DEV_ENV = "dev";

	private static Map<String, String> map = new HashMap<String, String>();

	static {
		map.put("dev", "src/main/resources/dev-api-urls.json");
		map.put("prod", "src/main/resources/prod-api-urls.json");
		map.put(null, "src/main/resources/dev-api-urls.json");
	}

	public static final Map<String, String> ENV_API_URLS = Collections.unmodifiableMap(map);

	
	public static final int TIMER_INTERVAL = 10*60*10;
}
