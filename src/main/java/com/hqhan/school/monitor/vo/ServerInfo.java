package com.hqhan.school.monitor.vo;

import java.util.Date;

/**
 * 〈description〉<br>
 * 〈各服务工作情况〉
 *
 * @author hqhan8080@Gmail.com
 * @version V1.0
 * @date 2019-10-27 20:31
 */
public class ServerInfo {

    private String serverName;
    private String ip;
    private String port;
    private String status;
    private String disk;
    private String memory;
    private long timeout;
    private Date registerTime = new Date();

    //TODO other


    public ServerInfo() {
    }

    public ServerInfo(String ip, String port, String status) {

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "serverName='" + serverName + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", status='" + status + '\'' +
                ", disk='" + disk + '\'' +
                ", memory='" + memory + '\'' +
                ", timeout=" + timeout +
                ", registerTime=" + registerTime +
                '}';
    }
}
