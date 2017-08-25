package net.isetjb.adminindex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminIndexController
{
    @RequestMapping(value =
    {
        "/admin", "/admin/index"
    })
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("admin/index");
        return modelAndView;
    }
}
