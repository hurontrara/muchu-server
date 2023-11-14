package com.server.muchu.cicdTest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class CicdController {


    @GetMapping
    public String cicdCheckPage() {

        return "test/test.html";


    }

}
