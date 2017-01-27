package com.dist.services;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sehan Rathnayake on 17/01/21.
 */
public interface NodeService{
    public String[] register();
    public void handleRequest(String req,HttpServletRequest request);
    public String[] leave();
    public String[] search(String name, int ttl, String originIp, String senderIp, int originPort, int senderPort);
}
