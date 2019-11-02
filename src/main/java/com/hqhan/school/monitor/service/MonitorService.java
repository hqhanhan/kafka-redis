package com.hqhan.school.monitor.service;

import com.hqhan.school.monitor.vo.ServerInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 〈description〉<br>
 * 〈被监控业务调用〉
 *
 * @author hqhan8080@Gmail.com
 * @version V1.0
 * @date 2019-11-01 23:50
 */
public interface MonitorService {

    String MONITOR_HEART_BEAT_PREFIX="MONITOR_HEART_BEAT";

    /**
     * description: <br>
     * 〈通过此接口，确认请求者当前是存活状态〉
     * @param serverInfo
     * @return com.hqhan.school.monitor.vo.ServerInfo
     * @version V1.0
     * @author hqhan8080@Gmail.com
     * @date 2019/11/02 00:09
     */
    ServerInfo iamAlive(ServerInfo serverInfo);

    /**
     * description: <br>
     * 〈获取存活的各服务节点信息〉
     * @param serverName
     * @param status
     * @return java.util.Map<java.lang.String,java.util.List<com.hqhan.school.monitor.vo.ServerInfo>>
     * @version V1.0
     * @author hqhan8080@Gmail.com
     * @date 2019/11/02 00:45
     */
    Map<String, List<ServerInfo>> getAliveNodeStatistics(String serverName, String status);


    /**
     * description: <br>
     * 〈获取存活的服务名称列表〉
     * @param
     * @return java.util.Set<java.lang.String>
     * @version V1.0
     * @author hqhan8080@Gmail.com
     * @date 2019/11/02 01:20
     */
    Set<String> getAliveServerNameList();
}
