package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.dto.UsuarioPersonaDTO;
import edu.idat.eventosvirtuales.entity.DocumentoAlmacenado;
import edu.idat.eventosvirtuales.entity.Persona;
import edu.idat.eventosvirtuales.entity.Rol;
import edu.idat.eventosvirtuales.entity.Usuario;
import edu.idat.eventosvirtuales.repository.PersonaRepository;
import edu.idat.eventosvirtuales.repository.RolRepository;
import edu.idat.eventosvirtuales.repository.UsuarioRepository;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static edu.idat.eventosvirtuales.utils.Global.*;

@Service
@Transactional
public class UsuarioService implements BaseService<Usuario, Long> {
    private UsuarioRepository repo;
    private PersonaRepository perRepo;
    private RolRepository rolRepo;

    private PersonaService perServ;

    public UsuarioService(UsuarioRepository repo, PersonaRepository perRepo, RolRepository rolRepo, PersonaService perServ) {
        this.repo = repo;
        this.perRepo = perRepo;
        this.rolRepo = rolRepo;
        this.perServ = perServ;
    }

    @Override
    public GenericResponse list() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }

    @Override
    public GenericResponse find(Long id) {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.findById(id));
    }

    @Override
    public GenericResponse save(Usuario obj) {
        return null;
    }

    @Override
    public GenericResponse delete(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> validate(Usuario obj) {
        HashMap errors = new HashMap();
        if (repo.findByUsername(obj.getUsername()).isPresent()) {
            errors.put("username", String.format("El nombre de usuario '%s' ya existe", obj.getUsername()));
        }
        return errors;
    }

    public GenericResponse saveWidhPersona(UsuarioPersonaDTO dto) {
        GenericResponse response = new GenericResponse();
        HashMap<String, Object> errors, errorsPersona;

        Usuario usuario = repo.findById(dto.getUsuarioId()).orElse(new Usuario());
        Persona persona = perRepo.findById(dto.getPersonaId()).orElse(new Persona());

        if (persona.getId() == 0) {
            persona.setNroDocIdentidad(dto.getNroDocIdentidad());
            persona.setApellidoPaterno(dto.getApellidoPaterno());
            persona.setApellidoMaterno(dto.getApellidoMaterno());
            persona.setNombres(dto.getNombres());
            persona.setFechaNacimiento(dto.getFechaNacimiento());
        }

        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());

        errors = validate(usuario);
        errorsPersona = perServ.validate(persona);

        if (errors.size() == 0 && errorsPersona.size() == 0) {
            persona.setFotoPerfil(null);
            persona = perRepo.save(persona);

            usuario.setPersona(persona);
            usuario = repo.save(usuario);

            response.setRpta(RPTA_OK);
            response.setMessage(OPERACION_CORRECTA);
            response.setBody(usuario);
        } else {
            HashMap<String, Object> allErrors = new HashMap<>();
            allErrors.put("usuario", errors);
            allErrors.put("persona", errorsPersona);

            response.setRpta(RPTA_WARNING);
            response.setMessage(OPERACION_INCORRECTA);
            response.setBody(allErrors);
        }

        return response;
    }

    public GenericResponse findByCredenciales(String username, String password) {
        GenericResponse response = new GenericResponse();
        response.setType(TIPO_AUTH);

        Optional<Usuario> optUsuario = repo.findByCredenciales(username, password);
        if (optUsuario.isPresent()) {
            response.setRpta(RPTA_OK);
            response.setMessage("Credenciales correctas");

            // Genero el token
            String token = getToken(username);
            HashMap<String, Object> body = new HashMap<>();
            body.put("token", token);
            body.put("usuarioId", optUsuario.get().getId());
            body.put("personaId", optUsuario.get().getPersona().getId());
            response.setBody(body);
        } else {
            response.setRpta(RPTA_WARNING);
            response.setMessage("Datos de ingreso incorrectos");
            response.setBody(false);
        }

        return response;
    }

    private String getToken(String username) {
        List<Rol> rols = (List<Rol>) rolRepo.findByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Rol rol : rols) {
            grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return Jwts
                .builder()
                .setId("jwt")
                .setSubject(username)
                .claim("authorities", grantedAuthorities
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512, AUTH_SECRET.getBytes()).compact();
    }
}