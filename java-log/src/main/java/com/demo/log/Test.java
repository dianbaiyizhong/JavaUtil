package com.demo.log;

import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class Test {

    public static void main(String[] args) throws UnknownHostException {
        System.setProperty("ip", InetAddress.getLocalHost().getHostAddress());
        log.info("info----");
        log.debug("debug----");

    }
}
