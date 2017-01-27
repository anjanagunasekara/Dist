package com.dist.controller;

import com.dist.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    NodeService nodeService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcomeHome(ModelMap model){

        return "home";
    }
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public
    @ResponseBody
    String register() {
        boolean regok=nodeService.register();
        if(regok){
            return "Registered successfully";
        }
        else
            return "Registration fails";
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    public
    @ResponseBody
    String leave() {
        boolean leaveok=nodeService.leave();
        if(leaveok){
            return "Leaving successfully";
        }
        else
            return "Leaving fails";
    }

}
