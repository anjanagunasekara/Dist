package com.dist.controller;

import com.dist.services.HTTPrequest;
import com.dist.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author MaN
 *         on 1/20/2017.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {

    @Autowired
    HTTPrequest httPrequest;

    @Autowired
    NodeService nodeService;

    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public void printWelcome(@RequestBody String s, HttpServletRequest request) {

        nodeService.handleRequest(s.replace("+", " ").trim(), request);
        //   return "customer";


    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void send() {
        httPrequest.downloadFile("localhost", 8082, "abcd.mp3");
    }


    @RequestMapping("/file/{fileName:.+}")
    public void downloadPDFResource(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable("fileName") String fileName) {
        //If user is not authorized - he should be thrown out from here itself

        //Authorized user will download the file
        URL url = this.getClass().getClassLoader().getResource("/downloads/");
        String dataDirectory = url.getFile();
        String finalDir=dataDirectory.substring(1).replace("/","\\");
        Path file = Paths.get(finalDir, fileName);
        if (Files.exists(file)) {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void send(HttpServletRequest request) {
        //httPrequest.sendHTTPrequests("localhost", 8082, "seham");

    }

}
