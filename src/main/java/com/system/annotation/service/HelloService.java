package com.system.annotation.service;

import javax.jws.WebService;

@WebService
public interface HelloService {

    public String syHi(String name);
}