package com.dist.services;

import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by Sehan Rathnayake on 17/01/20.
 */

@Service
public class HTTPrequestsImpl implements HTTPrequest {

    public void sendHTTPrequests(String ip,int port,String msg){



        String urlstr = "http://"+ip+":"+port+"/Dist/rest/node";
        try {
            URL url = new URL(urlstr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(msg);
            wr.flush();
            wr.close();
            int x = conn.getResponseCode();
            if (x == 200) {
                System.out.println("successful");
            } else {
                System.out.println("not successful");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

       }
}
