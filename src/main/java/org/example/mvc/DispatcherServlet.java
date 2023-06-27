package org.example.mvc;

import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

// 어떠한 경로로 요청이 들어오든 DispatcherServlet 이 실행된다.
@WebServlet("/")
public class DispatcherServlet extends HttpServlet
{
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> handlerMappings;

    private List<ViewResolver> viewResolvers;

    private List<HandlerAdaptor> handlerAdaptors;

    @Override
    public void init() throws ServletException
    {
        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();

        AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("org.example.mvc");
        annotationHandlerMapping.initialize();

        handlerMappings = List.of(requestMappingHandlerMapping, annotationHandlerMapping);
        handlerAdaptors = List.of(new SimpleControllerHandlerAdaptor(), new AnnotationHandlerAdaptor());
        viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.info("[DispatcherServlet] service started!");
        String requestURI = request.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

        try
        {
            Object handler = handlerMappings.stream()
                    .filter(handlerMapping1 -> handlerMapping1.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
                    .map(handlerMapping1 -> handlerMapping1.findHandler(new HandlerKey(requestMethod, requestURI)))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No handler for [" + requestMethod + ", " + requestURI + "]"));

            HandlerAdaptor handlerAdaptor = handlerAdaptors.stream()
                    .filter(ha -> ha.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adaptor for handler [" + handler + "]"));

            ModelAndView modelAndView = handlerAdaptor.handle(request, response, handler);

            for (ViewResolver viewResolver : viewResolvers)
            {
                View view = viewResolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), request, response);
            }
        }
        catch (Exception e)
        {
            logger.error("exception occurred : [{}]", e.getMessage(), e);
        }
    }
}
