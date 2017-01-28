package com.dist.services;

import com.dist.dto.SearchResults;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 17/01/21.
 */
public interface NodeService{
    public String[] register();
    public void handleRequest(String req,HttpServletRequest request);
    public String[] leave();
    public List<String> search(String name, int ttl, String originIp, String senderIp, int originPort, int senderPort);
    public String configureIPandPort(int port);
    public List<SearchResults> getSearchResultList();
}
