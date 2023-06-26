package org.example.mvc.view;

import static org.example.mvc.view.RedirectView.DEFALUT_REDIRECT_PREFIX;

public class JspViewResolver implements ViewResolver
{
    @Override
    public View resolveView(String viewName)
    {
        if (viewName.startsWith(DEFALUT_REDIRECT_PREFIX))
        {
            return new RedirectView(viewName);
        }
        else
        {
            return new JspView(viewName + ".jsp");
        }
    }
}
