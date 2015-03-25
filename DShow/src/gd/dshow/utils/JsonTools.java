package gd.dshow.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class JsonTools {

	private static String tag="JsonTools";

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	public static String getString(String key, String jsonString) {
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			String json_value = jsonObject.getString(key);
			return json_value;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public static List<String> getList(String key, String jsonString) {
		List<String> list = new ArrayList<String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				String msg = jsonArray.getString(i);
				list.add(msg);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static List<Map<String, Object>> getListMap(String key,
			String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator iterator = jsonObject2.keys();
				while (iterator.hasNext()) {
					String json_key = (String) iterator.next();
					Object json_value = jsonObject2.get(json_key);
					if (json_value == null) {
						json_value = "";
					}
					map.put(json_key, json_value);
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
