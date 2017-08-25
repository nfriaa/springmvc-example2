package net.isetjb.index;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController
{
    @RequestMapping(value =
    {
        "/", "/index", "/index.html"
    })
    public ModelAndView index(HttpSession session, HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView("index");

        // session :
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null)
        {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);

        // some request infos :
        modelAndView.addObject("locale", request.getLocale());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("user", request.getRemoteUser());
        modelAndView.addObject("ip", request.getLocalAddr());

        return modelAndView;
    }
}
