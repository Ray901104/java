package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping
{
    // url, controller
    private final Map<String, Controller> mappings = new HashMap<>();

    void init()
    {
        mappings.put("/", new HomController());
    }

    public Controller findHandler(String urlPath)
    {
        return mappings.get(urlPath);
    }
}
