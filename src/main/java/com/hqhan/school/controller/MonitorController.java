package com.hqhan.school.controller;


import com.hqhan.school.monitor.service.MonitorService;
import com.hqhan.school.monitor.vo.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @PostMapping(value = "/iamAlive", consumes = "application/json;charset=UTF-8")
    public ServerInfo iamAlive(@RequestBody ServerInfo serverInfo) {
        return monitorService.iamAlive(serverInfo);
    }


    @GetMapping( "/getAliveNodeStatistics")
    public Map<String, List<ServerInfo>> getAliveNodeStatistics(@RequestParam(required = false) String serverName, @RequestParam(required = false) String status) {
        return monitorService.getAliveNodeStatistics(serverName,status);
    }


}