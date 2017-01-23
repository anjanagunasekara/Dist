package com.dist.controller;

import com.dist.services.HTTPrequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.*;
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

    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public String printWelcome(@RequestBody String s) {


        return "customer";


    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void send() {
        httPrequest.downloadFile("localhost", 8082, "abcd.mp3");
    }



    @RequestMapping("/file/{fileName:.+}")
    public void downloadPDFResource( HttpServletRequest request,
                                     HttpServletResponse response,
                                     @PathVariable("fileName") String fileName)
    {
        //If user is not authorized - he should be thrown out from here itself

        //Authorized user will download the file
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file))
        {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
