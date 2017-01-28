package com.dist.controller;

import com.dist.services.HTTPrequest;
import com.dist.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    NodeService nodeService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcomeHome(ModelMap model, ServletRequest servletRequest) {
        int port=servletRequest.getLocalPort();
        String ip=nodeService.configureIPandPort(port);
        model.addAttribute("ip",ip);
         model.addAttribute("port",port);

        return "home";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public
    @ResponseBody
    String[] register() {
        String[] regResponse = nodeService.register();
        return regResponse;
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    public
    @ResponseBody
    String[] leave() {
        String[] leaveResponse = nodeService.leave();
        return leaveResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> search() {
        List<String> searchResponse = nodeService.search("abc", 10, "127.0.0.1", "127.0.0.1", 8082, 8082);
        return searchResponse;
    }

    @RequestMapping(value = "/loadFiles", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> loadFiles() {
        List<String> dataList=new ArrayList<String>();
        URL url = this.getClass().getClassLoader().getResource("/downloads");
        File folder = new File(url.getFile());
        File[] listOfFiles = folder.listFiles();
        for(int i=0;i<listOfFiles.length;i++){
            dataList.add(listOfFiles[i].getName());
        }
        return dataList;
    }

}
