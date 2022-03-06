package edu.neu.csye6220.controller;

import edu.neu.csye6220.domain.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class IocController extends AbstractController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView model = new ModelAndView("part7");
        User user = (User) this.getApplicationContext().getBean("user");
        httpServletRequest.setAttribute("user",user);
        return model;
    }
}
