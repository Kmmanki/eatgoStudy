package kr.co.study.eatgo.eatgo.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomController {

    @GetMapping("/")
    public String hello(){
        return "HelloWrold!!!!!!";
    }
}
