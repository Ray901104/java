package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomController implements Controller
{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return "home.jsp";
    }
}
