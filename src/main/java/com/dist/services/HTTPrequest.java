package com.dist.services;

/**
 * Created by Sehan Rathnayake on 17/01/20.
 */
public interface HTTPrequest {

        public void sendHTTPrequests(String Ip,int port,String msg);
        public void downloadFile(String ip,int port,String msg);

}
