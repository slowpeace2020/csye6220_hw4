package edu.neu.csye6220.controller;

import edu.neu.csye6220.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static edu.neu.csye6220.ApplicationContextHolder.getApplicationContext;


@Controller
@RequestMapping("/")
public class IocController {

    @RequestMapping("/ioc")
    public String ioc(Model model){
        User user = getUser();
        model.addAttribute("user",user);
        model.addAttribute("userAddress",user.toString());
        return "part7";
    }

    private User getUser(){
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext app = getApplicationContext();
        User user = app.getBean(User.class);
        return user;
    }
}
