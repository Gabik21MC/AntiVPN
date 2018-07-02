package me.egg82.avpn.apis;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import me.egg82.avpn.utils.WebUtil;
import ninja.egg82.bukkit.services.ConfigRegistry;
import ninja.egg82.patterns.ServiceLocator;

public class IPHubAPI {
	//vars
	
	//constructor
	public IPHubAPI() {
		
	}
	
	//public
	public static Boolean isVPN(String ip) {
		String key = ServiceLocator.getService(ConfigRegistry.class).getRegister("sources.iphub.key", String.class);
		if (key == null || key.isEmpty()) {
			return null;
		}
		
		int blockType = ServiceLocator.getService(ConfigRegistry.class).getRegister("sources.iphub.block", Number.class).intValue();
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Key", key);
		
		JSONObject json = WebUtil.getJson("https://v2.api.iphub.info/ip/" + ip, headers);
		if (json == null) {
			return null;
		}
		
		int block = ((Number) json.get("block")).intValue();
		return (block == blockType) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	//private
	
}
