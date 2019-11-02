package com.hqhan.school.register;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * 〈description〉<br>
 * 〈待各服务启动成功后，注册各自节点相关信息至监控服务中〉
 *
 * @author hqhan8080@Gmail.com
 * @version V1.0
 * @date 2019-11-02 01:39
 */
@Component
public class ServerHeartbeatDetector implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(ServerHeartbeatDetector.class);

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        register();
    }

    public void register() throws InterruptedException, URISyntaxException {
        while (true) {
            String registerServerUrl = env.getProperty("register.server.request-url");
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setIp("127.0.0.1");
            serverInfo.setServerName("demo");
            serverInfo.setPort("8090");
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(new URI(registerServerUrl), serverInfo, String.class);
            String body = responseEntity.getBody();
            ServerInfo server = JSON.parseObject(body, ServerInfo.class);
            logger.info("Register server successfully in {}", server.getRegisterTime());
            Thread.sleep(22000);
        }
    }


    static class ServerInfo {

        private String serverName;
        private String ip;
        private String port;
        private String status;
        private String disk;
        private String memory;
        private long timeout;
        private Date registerTime = new Date();

        //TODO other

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


}
