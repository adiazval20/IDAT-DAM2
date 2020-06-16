package edu.idat.eventosvirtuales.controller;

import edu.idat.eventosvirtuales.dto.UsuarioPersonaDTO;
import edu.idat.eventosvirtuales.service.PersonaService;
import edu.idat.eventosvirtuales.service.UsuarioService;
import edu.idat.eventosvirtuales.utils.CustomResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

import static edu.idat.eventosvirtuales.utils.Global.OPERACION_CORRECTA;
import static edu.idat.eventosvirtuales.utils.Global.OPERACION_INCORRECTA;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private UsuarioService service;
    private PersonaService personaService;

    public UsuarioController(UsuarioService service, PersonaService personaService) {
        this.service = service;
        this.personaService = personaService;
    }

    @GetMapping
    public CustomResponse list() {
        return new CustomResponse(service.list());
    }

    @PostMapping("/auth")
    public CustomResponse auth(@RequestParam String username, @RequestParam String password) {
        if (service.findByCredenciales(username, password).isPresent()) {
            return new CustomResponse("auth", "Credenciales correctas", true);
        } else {
            return new CustomResponse("auth", "Datos de ingreso incorrectos", false);
        }
    }

    @PostMapping("/persona")
    public CustomResponse createWithPersona(@RequestBody @Valid UsuarioPersonaDTO dto) {
        HashMap<String, Object> result = service.saveWidhPersona(dto);
        String msg = Integer.parseInt(result.get("rpta").toString()) == 1
                ? OPERACION_CORRECTA
                : OPERACION_INCORRECTA;
        return new CustomResponse("result", msg, result.get("data"));
    }
}