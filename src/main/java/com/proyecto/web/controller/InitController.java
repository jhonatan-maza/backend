package com.proyecto.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jonat on 18/08/2019.
 */
@Controller
public class InitController {

    @GetMapping("/")
    public String inicio(){
        return "index";
    }

}
