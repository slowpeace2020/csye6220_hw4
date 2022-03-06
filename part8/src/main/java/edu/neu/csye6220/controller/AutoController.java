package edu.neu.csye6220.controller;

import edu.neu.csye6220.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AutoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/auto.htm")
    public String ioc(Model model){
        model.addAttribute("userService info",userService.getServiceInfo());
        model.addAttribute("userService",userService);
        return "part8";
    }
}
