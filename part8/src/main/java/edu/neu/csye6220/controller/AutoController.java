package edu.neu.csye6220.controller;

import edu.neu.csye6220.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class AutoController {
    private User user;

    public AutoController(@Qualifier("userTest") User user) {
        this.user = user;
    }

    @RequestMapping("/auto")
    public String ioc(Model model){
        model.addAttribute("user",user);
        model.addAttribute("userAddress",user.toString());
        return "part8";
    }
}
