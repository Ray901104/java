package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping
{
    // url, controller
    private final Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init()
    {
        // Request 메소드에 따른 분류를 하기 위해 HandlerKey 생성
        mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController()); // GET
        mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreatedController()); // POST
        mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey)
    {
        return mappings.get(handlerKey);
    }
}
