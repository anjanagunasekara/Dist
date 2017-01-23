package com.dist.services;

import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

//import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by Sehan Rathnayake on 17/01/20.
 */

@Service
public class HTTPrequestsImpl implements HTTPrequest {

    public void sendHTTPrequests(String senderIP,int senderPort,String recieverIp,int recieverPort,String msg){

        int len = msg.length() + 5;
        msg = String.format("%04d", len) + " " + msg;

        String urlstr = "http://"+recieverIp+":"+recieverPort+"/Dist/rest/node";
        try {
            URL url = new URL(urlstr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("IP",senderIP);
            conn.setRequestProperty("PORT",String.valueOf(senderPort));
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
    public void downloadFile(String ip,int port,String fileName){
        String urlstr = "http://"+ip+":"+port+"/Dist/rest/file/"+fileName;
        try {
            URL url = new URL(urlstr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.flush();
            wr.close();
            InputStream is = conn.getInputStream();
            int x = conn.getResponseCode();
            FileOutputStream fos = new FileOutputStream(new File("E:/"+fileName));
            int read = 0;
            byte[] buffer = new byte[32768];
            while( (read = is.read(buffer)) > 0) {
                fos.write(buffer, 0, read);
            }

            fos.close();
            is.close();
            System.out.println("E:/"+fileName);
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
