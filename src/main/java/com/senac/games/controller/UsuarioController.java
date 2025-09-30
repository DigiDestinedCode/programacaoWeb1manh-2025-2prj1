package com.senac.games.controller;

import com.senac.games.dto.request.LoginUsuarioDTO;
import com.senac.games.dto.request.UsuarioDTOLoginRequest;
import com.senac.games.dto.request.UsuarioDTORequest;
import com.senac.games.dto.response.UsuarioDTOLoginResponse;
import com.senac.games.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/login")
    public ResponseEntity<UsuarioDTOLoginResponse>  login(@RequestBody UsuarioDTOLoginRequest usuarioDTOLoginRequest) {
        return ResponseEntity.ok(usuarioService.login(usuarioDTOLoginRequest));
    }
    @PostMapping("/criar")
    public ResponseEntity<UsuarioDTORequest> criar(@RequestBody UsuarioDTORequest usuarioDTORequest) {
        return ResponseEntity.ok(usuarioService.criar());
    }

//
//    @PostMapping("/login")
//    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUsuarioDto loginUserDto) {
//        RecoveryJwtTokenDto token = usuarioService.authenticateUser(loginUserDto);
//        return new ResponseEntity<>(token, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> createUser(@RequestBody CreateUsuarioDto createUsuarioDto) {
//        usuarioService.createUsuario(createUsuarioDto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping("/test")
//    public ResponseEntity<String> getAuthenticationTest() {
//        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
//    }
//
//    @GetMapping("/test/customer")
//    public ResponseEntity<String> getCustomerAuthenticationTest() {
//        return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
//    }
//
//    @GetMapping("/test/administrator")
//    public ResponseEntity<String> getAdminAuthenticationTest() {
//        return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
//    }

}
