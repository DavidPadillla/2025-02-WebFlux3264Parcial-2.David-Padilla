package com.security.spring_security.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ContactController {

    @GetMapping("/contact")
    public String contact() {
        return "Esta es mi pagina de contacto";
    }
    
}
