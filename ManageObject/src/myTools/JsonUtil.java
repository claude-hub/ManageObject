package myTools;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

	private static Gson json = new Gson();

	public static String parseJson(Object value) {
		try {
			String str = json.toJson(value);
			return str;
		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}
	}

	public static Object JsonToList(String content, Type type) {

		try {
			return json.fromJson(content, type);
		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}

	}
}
