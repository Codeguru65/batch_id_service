package com.fbc.batchidservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient
public class IdClient {

    public void checkId(){

    }
}
