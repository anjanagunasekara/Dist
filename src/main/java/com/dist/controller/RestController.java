package com.dist.controller;

import com.dist.services.HTTPrequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author MaN
 *         on 1/20/2017.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {

    @Autowired
HTTPrequest httPrequest;

    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public String printWelcome(@RequestBody String s) {


        return "customer";


    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void send() {
        httPrequest.sendHTTPrequests("localhost",8082,"seham");
          }

}
