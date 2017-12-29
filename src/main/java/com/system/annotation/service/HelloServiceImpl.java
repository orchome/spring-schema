package com.system.annotation.service;

import com.system.annotation.custom.Endpoint;

import javax.jws.WebService;

@Endpoint(address="HelloService", id = "HelloServiceEndpoint")
@WebService(endpointInterface= "com.system.annotation.service.HelloService")
public class HelloServiceImpl implements HelloService{

    @Override
    public String syHi(String name) {
        return "Hello "+name;
    }

}