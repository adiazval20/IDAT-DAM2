package edu.idat.eventosvirtuales.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/evento-virtual")
public class EventoVirtualController {
    @GetMapping
    public String sayHello() {
        return "Hola!";
    }

}
