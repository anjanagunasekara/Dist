package com.dist.controller;

import com.dist.services.NodeService;
import com.dist.services.NodeServiceImpl;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Controller
@RequestMapping("/")
public class HomeController {
    NodeServiceImpl nodeService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcomeHome(ModelMap model){

        return "home";
    }
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String register() {
        System.out.printf("::::::::");

        nodeService.register();
        return "new";
    }

}
