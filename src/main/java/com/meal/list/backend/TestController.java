package com.meal.list.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping("/")
    public String init(){
        return "dziala";
    }

    @GetMapping("/gretting")
    public List<Greeting> getAllGretting(){
        return greetingRepository.findAll();
    }}
