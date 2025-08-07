package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String cmd) {
        String[] parts = cmd.trim().split(" ");
        actionName = parts.length > 0 ? parts[0] : "";
        
        paramsMap = new HashMap<>();
        
        // 공백으로 구분된 명령어를 파싱 (detail 1, update 2, delete 3)
        if (parts.length >= 2) {
            paramsMap.put("id", parts[1]);
        }
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        if (paramsMap.containsKey(paramName)) {
            return paramsMap.get(paramName);
        } else {
            return defaultValue;
        }
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        if (value.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean hasParam(String paramName) {
        return paramsMap.containsKey(paramName) && !paramsMap.get(paramName).isEmpty();
    }

    public boolean hasValidId() {
        return hasParam("id") && getParamAsInt("id", -1) > 0;
    }
}