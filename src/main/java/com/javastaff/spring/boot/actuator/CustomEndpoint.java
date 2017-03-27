package com.javastaff.spring.boot.actuator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

@Component
public class CustomEndpoint implements Endpoint<HashMap<String, List<String>>> {
    
    public String getId() {
        return "customEndpoint";
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isSensitive() {
        return true;
    }

    public HashMap<String, List<String>> invoke() {
    	HashMap<String, List<String>> map = new HashMap<>();
        map.put("enabled", Arrays.asList("feature1","feature2"));
        map.put("notAllowed", Arrays.asList("command1","command2"));
        return map;
    }
}
