package ua.pp.darknsoft.darkystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Validated
public class MainController {


    public String index(){
        return "index";
    }
}
