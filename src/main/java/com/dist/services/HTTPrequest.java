package com.dist.services;

/**
 * Created by Sehan Rathnayake on 17/01/20.
 */
public interface HTTPrequest {

        public boolean sendHTTPrequests(String senderIP,int senderPort,String recieverIp,int recieverPort,String msg);
        public void downloadFile(String ip,int port,String msg);

}
