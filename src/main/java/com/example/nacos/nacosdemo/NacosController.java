package com.example.nacos.nacosdemo;

import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：zhengqh
 * @date 2020/1/29 19:42
 **/
@NacosPropertySource(dataId = "demo",groupId = "DEFAULT_GROUP",autoRefreshed = true)
@RestController
public class NacosController {

     @NacosValue(value="${info: hello nacos}",autoRefreshed = true)
     private String info;

     @GetMapping("/get")
     public String get(){
         return  info;
     }
}
