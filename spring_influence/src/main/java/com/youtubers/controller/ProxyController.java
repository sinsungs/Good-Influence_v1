package com.youtubers.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {
    @GetMapping("hello")
    public List<String> Hello(){
        return Arrays.asList("서버 포트는 8080", "리액트 포트는 3000");
    }
}
