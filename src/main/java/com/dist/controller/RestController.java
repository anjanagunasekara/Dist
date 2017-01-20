package com.dist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author MaN
 *         on 1/20/2017.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    @RequestMapping(value = "/node", method = RequestMethod.GET)
    public String printWelcome(String model) {
        return "customer";
    }
}
