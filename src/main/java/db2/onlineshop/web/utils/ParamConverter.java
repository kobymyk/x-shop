package db2.onlineshop.web.utils;

import java.util.HashMap;
import java.util.List;

public class ParamConverter {
    public static HashMap<String, Object> fromList(List<?> list, String paramName) {
        HashMap<String, Object> result = new HashMap<>();
        result.put(paramName, list);

        return result;
    }
}
