package edu.neu.csye6220.controller;

import edu.neu.csye6220.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AutoController {

    @Autowired
    private UserService userService;


    @RequestMapping("/auto.htm")
    public String ioc(HttpServletRequest request){
        request.setAttribute("userService info",userService.getServiceInfo());
        request.setAttribute("userService",userService);
        return "part9";
    }
}
