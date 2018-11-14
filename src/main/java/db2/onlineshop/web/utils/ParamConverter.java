package db2.onlineshop.web.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamConverter {
    // todo: add Entity
    public static HashMap<String, Object> fromList(List<?> list, String paramName) {
        HashMap<String, Object> result = new HashMap<>();
        result.put(paramName, list);

        return result;
    }

    public static HashMap<String, Object> fromObject(Object object, String paramName) {
        HashMap<String, Object> result = new HashMap<>();
        result.put(paramName, object);

        return result;
    }

    public static HashMap<String, String> fromParamMap(Map<String, String[]> params) {
        HashMap<String, String> result = new HashMap<>();
        for (String key : params.keySet()) {
            result.put(key, params.get(key)[0]);
        }

        return result;
    }
}
