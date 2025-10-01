package com.senac.games.service;


import com.senac.games.config.SecurityConfiguration;
import com.senac.games.dto.request.UsuarioDTOLoginRequest;
import com.senac.games.dto.request.UsuarioDTORequest;
import com.senac.games.dto.response.UsuarioDTO;
import com.senac.games.dto.response.UsuarioDTOLoginResponse;
import com.senac.games.entity.Role;
import com.senac.games.entity.RoleName;
import com.senac.games.entity.Usuario;
import com.senac.games.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private SecurityConfiguration securityConfiguration;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    // Método responsável por autenticar um usuário e retornar um token JWT
    public UsuarioDTOLoginResponse login(UsuarioDTOLoginRequest usuarioDTOLoginRequest) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuarioDTOLoginRequest.getLogin(), usuarioDTOLoginRequest.getSenha());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        UsuarioDTOLoginResponse usuarioDTOLoginResponse = new UsuarioDTOLoginResponse();
        usuarioDTOLoginResponse.setId(userDetails.getIdUsuario());
        usuarioDTOLoginResponse.setNome(userDetails.getNomeUsuario());
        usuarioDTOLoginResponse.setToken(jwtTokenService.generateToken(userDetails));
        return usuarioDTOLoginResponse;
//        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public UsuarioDTO criar(UsuarioDTORequest usuarioDTORequest) {

        List<Role> roles = new ArrayList<Role>();
        for (int i=0; i<usuarioDTORequest.getRoleList().size(); i++){
            Role role = new Role();
            role.setName(RoleName.valueOf(usuarioDTORequest.getRoleList().get(i)));
            roles.add(role);
        }
//        // Atribui ao usuário uma permissão específica
        Role role = new Role();
//        role.setName(usuarioDTORequest.getRoles());
//        newUser.setRoles(List.of(role));
//
//        // Cria um novo usuário com os dados fornecidos


        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTORequest.getNome());
        usuario.setCpf(usuarioDTORequest.getCpf());
        usuario.setDataNascimento(usuarioDTORequest.getDataNascimento());
        usuario.setLogin(usuarioDTORequest.getLogin());
        usuario.setSenha(securityConfiguration.passwordEncoder().encode(usuarioDTORequest.getSenha()));
        usuario.setStatus(1);
        usuario.setRoles(roles);
        Usuario usuariosave = usuarioRepository.save(usuario);
//        newUser.setLogin(usuarioDTORequest.getLogin());
//        // Codifica a senha do usuário com o algoritmo bcrypt
//        newUser.setSenha(securityConfiguration.passwordEncoder().encode(usuarioDTORequest.getSenha()));
//
//
//        // Salva o novo usuário no banco de dados
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuario.setId(usuariosave.getId());
        usuario.setNome(usuariosave.getNome());
        usuario.setDataNascimento(usuariosave.getDataNascimento());
        usuario.setCpf(usuariosave.getCpf());
        usuario.setLogin(usuariosave.getLogin());
        return usuarioDTO;
    }
}