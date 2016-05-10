package controller;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class InitializeBusInfoAction extends Action {
	// key stopId, value list of routes
	public HashMap<String, ArrayList<String>> stopAndRouteMap = new HashMap<>();

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "init.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if (stopAndRouteMap.size() == 0) {
			addStopandRouteInfo();
		}
		return "homepage.jsp";
	}
	
	private void addStopandRouteInfo() {
		JsonArray allAvaiRoutes = getAllAvaiRoutes();
		
		for (int i = 0; i < allAvaiRoutes.size(); i++) {
			JsonElement currRoute = allAvaiRoutes.get(i);
		}
	}
	private JsonArray getAllAvaiRoutes() {
		return null;
	}

}
