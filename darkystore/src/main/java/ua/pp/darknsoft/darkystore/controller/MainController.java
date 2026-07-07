package ua.pp.darknsoft.darkystore.controller;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Validated
public class MainController {

    @GetMapping
     public String welcome(@Size(min = 4) @RequestParam(defaultValue = "Worm") String name){
         return "Hello " + name + "!";
     }
}
