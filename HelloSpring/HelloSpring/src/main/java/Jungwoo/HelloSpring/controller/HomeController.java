package Jungwoo.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // domain에 들어오면 호출됨
    public String home(){
        return "home";
    }
}
