package com.mkyong.core.services;

import com.mkyong.core.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class HelloServiceImpl implements HelloService {

    @Value("${greeting}")
    private String greeting;

    @Autowired
    HelloRepository helloRepository;

    @Override
    public String get() {
        System.out.println("Greeting: " + greeting);
        return greeting + " " + helloRepository.get();
    }

}
