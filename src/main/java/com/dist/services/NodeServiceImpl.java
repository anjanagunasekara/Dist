package com.dist.services;

import com.dist.domain.Neighbour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * Created by Sehan Rathnayake on 17/01/21.
 */

@Service
public class NodeServiceImpl implements NodeService {
    private String bootstrapServerIp = "127.0.0.1";
    private int bootstrapServerPort = 55555;
    int initialTtl=2;
    List<Neighbour> neighboursList =
            Collections.synchronizedList(new ArrayList<Neighbour>());
    private int port=8080;
    private String ip="127.0.0.1";
    private String username;

    DatagramSocket socket = null;
    List<String> dataList = null;
    @Autowired
    HTTPrequest httPrequest;
    private boolean success;
    private String[] response = new String[5];

    public String[] register() {

        try {
            this.socket = new DatagramSocket(port);

            String str = "REG " + ip + " " + port + " " + username;
            int len = str.length() + 5;
            str = String.format("%04d", len) + " " + str;
            InetAddress ip = InetAddress.getByName(bootstrapServerIp);
            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, bootstrapServerPort);
            socket.send(dp);

            byte[] buf = new byte[1024];
            DatagramPacket reply = new DatagramPacket(buf, 1024);
            socket.receive(reply);
            String rep = new String(reply.getData(), 0, reply.getLength());
            System.out.println("Node :" + username + " Reply from Bootstrap server  :" + rep);
socket.close();

            rep = rep.substring(5);
            if (rep.startsWith("REGOK")) {
                String[] parts = rep.split(" ");
                int noOfNodes = Integer.parseInt(parts[1]);

                for (int i = 0; i < noOfNodes; i++) {

                    Neighbour neighbour = new Neighbour(parts[2 * i + 2], Integer.parseInt(parts[2 * i + 3]));
                    neighboursList.add(neighbour);
                }
                join();
                success =true;
            } else {
                success=false;
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
            success = false;
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        }
        response[0] = String.valueOf(success);
        response[1] = ip;
        response[2] = String.valueOf(port);
        return response;
    }


    private void join() {
        String str = "JOIN " + ip + " " + port;

        for (Neighbour n : neighboursList) {

            httPrequest.sendHTTPrequests(this.ip,this.port,n.getIp(), n.getPort(), str);
        }
    }

    public String[] leave() {
//        boolean success = httPrequest.sendHTTPrequests(this.ip,this.port,ip, 55555, "UNREG " + ip + " " + port + " " + username);
        boolean success=false;
        try {
            this.socket = new DatagramSocket(port);
            String str = "UNREG " + ip + " " + port + " " + username;
            int len = str.length() + 5;
            str = String.format("%04d", len) + " " + str;
            InetAddress ip = InetAddress.getByName(bootstrapServerIp);
            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, bootstrapServerPort);
            socket.send(dp);

            byte[] buf = new byte[1024];
            DatagramPacket reply = new DatagramPacket(buf, 1024);
            socket.receive(reply);
            String rep = new String(reply.getData(), 0, reply.getLength());
            System.out.println("Node :" + username + " Reply from Bootstrap server  :" + rep);

            rep = rep.substring(5);
            if(rep.startsWith("UNROK"))
                success=true;
            else
                success= false;
            socket.close();
        } catch (SocketException e) {
            success=false;
            e.printStackTrace();
        } catch (UnknownHostException e) {
            success=false;
            e.printStackTrace();
        } catch (IOException e) {
            success=false;
            e.printStackTrace();
        }
        for (Iterator<Neighbour> iterator = neighboursList.iterator(); iterator.hasNext(); ) {

            Neighbour n = iterator.next();
            httPrequest.sendHTTPrequests(this.ip,this.port,n.getIp(), n.getPort(), "LEAVE " + ip + " " + port);
            iterator.remove();

        }
        response[0] = String.valueOf(success);
        response[1] = ip;
        response[2] = String.valueOf(port);
        return response;

//        return success;

    }
public void handleRequest(String req,HttpServletRequest request){
    try {

      String  str = req.substring(5);
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        String opr = tokenizer.nextToken();
        if (opr.equals("JOIN")) {
            String ip = tokenizer.nextToken();
            int port = Integer.parseInt(tokenizer.nextToken());
            Neighbour neighbour = new Neighbour(ip, port);
            try {
                neighboursList.add(neighbour);
                httPrequest.sendHTTPrequests(this.ip,this.port,ip, port, "JOINOK 0");
            } catch (Exception e) {
                httPrequest.sendHTTPrequests(this.ip,this.port,ip, port, "JOINOK 9999");
            }


        } else if (opr.equals("LEAVE")) {
            String ip = tokenizer.nextToken();
            int port = Integer.parseInt(tokenizer.nextToken());
            for (Iterator<Neighbour> iterator = neighboursList.iterator(); iterator.hasNext(); ) {
                Neighbour n = iterator.next();
                if (port == n.getPort() && ip.equals(n.getIp())) {
                    try {
                        iterator.remove();
                        httPrequest.sendHTTPrequests(this.ip,this.port,ip, port, "LEAVEOK 0");
                    } catch (Exception e) {
                        httPrequest.sendHTTPrequests(this.ip,this.port,ip, port, "LEAVEOK 9999");
                    }
                }
            }
        } else if (opr.equals("REMOVE")) {
            leave();
        } else if (opr.equals("SER")){
            String originIp = tokenizer.nextToken();
            String senderIp = request.getHeader("IP");
            int originPort = Integer.parseInt(tokenizer.nextToken());
            int senderPort = Integer.parseInt(request.getHeader("PORT"));
            String name="";

            while (tokenizer.hasMoreTokens()){
                name=name+tokenizer.nextToken()+" ";
            }
            name=name.trim();
            name=name.replace("\"","");

            String ttlstr = name.substring(name.lastIndexOf(" ") + 1);
            name= name.substring(0,name.lastIndexOf(" "));

            int ttl = Integer.parseInt(ttlstr);
            search(name,ttl,originIp,senderIp,originPort,senderPort);
        } else if(opr.equals("SEROK")){

        } else if(opr.equals("SEARCH")){
            String name="";

            while (tokenizer.hasMoreTokens()){
                name=name+tokenizer.nextToken()+" ";
            }
            name=name.trim();
            name=name.replace("\"","");
            search(name,initialTtl,ip,ip,port,port);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public String[] search(String name, int ttl, String originIp, String senderIp, int originPort, int senderPort) {
        dataList=new ArrayList<String>();
        String split[] = name.split(" ");
        URL url = this.getClass().getClassLoader().getResource("/downloads");
        File folder = new File(url.getFile());
        File[] listOfFiles = folder.listFiles();
        for(int i=0;i<listOfFiles.length;i++){
            dataList.add(listOfFiles[i].getName());
        }
        List<String> filteredList = new ArrayList<String>(dataList);
        List<String> namesToRemove = new ArrayList<String>();
        for(String s:split){
            for(String fileName:filteredList){
                if(!fileName.contains(s)){
                    namesToRemove.add(fileName);
                }
            }
            for (String i:namesToRemove){
                filteredList.remove(i);
            }
        }
        if (filteredList.size()>0) {
            String str = "SEROK " + filteredList.size() + " " + ip + " " + port + " " + (initialTtl -ttl) + " ";
            for(String s:filteredList){
                s=s.replace(" ","_");
                str+=s+" ";
            }
            httPrequest.sendHTTPrequests(this.ip,this.port,originIp, originPort, str);
        }else{
            if (ttl > 0) {
                ttl--;
                String str = "SER " + originIp + " " + originPort + " " + name + " " + ttl;
                for (Neighbour n : neighboursList) {
                    if(!(n.getIp()==senderIp && n.getPort()==senderPort)){
                        httPrequest.sendHTTPrequests(this.ip,this.port,n.getIp(), n.getPort(), str);
                    }
                }
            } else {
                String str = "SEROK " + 0 + " " + ip + " " + port + " " + ttl + " ";

                httPrequest.sendHTTPrequests(this.ip,this.port,originIp, originPort, str);
            }
        }
    return response;
    }
}