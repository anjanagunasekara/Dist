package com.dist.dto;

import java.io.Serializable;

/**
 * @author MaN
 *         on 1/28/2017.
 */
public class SearchResults implements Serializable {
    private String name;
    private String ip;
    private int port;
    private int hops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }
}
