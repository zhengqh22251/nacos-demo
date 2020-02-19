package com.example.nacos.nacosdemo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @Author：zhengqh
 * @date 2020/1/29 19:54
 **/
public class NacosSDKDemo {
    public static void main(String[] args) throws IOException {
        String serverAddr= "localhost:8848";
        String dataId = "demo";
        String groupId = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr",serverAddr);

        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId,groupId,3000);
            System.out.println(content);

            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {
                    System.out.println("listener:s=="+s);
                }
            });

        } catch (NacosException e) {
            e.printStackTrace();
        }

        System.in.read();
    }
}
