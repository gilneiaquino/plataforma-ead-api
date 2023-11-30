package br.com.plataforma.ead.api.controllers;

import br.com.plataforma.ead.api.colecoes.InvalidToken;
import br.com.plataforma.ead.api.colecoes.Usuario;
import br.com.plataforma.ead.api.config.EmailService;
import br.com.plataforma.ead.api.config.JwtResponse;
import br.com.plataforma.ead.api.config.JwtTokenUtil;
import br.com.plataforma.ead.api.config.MessageUtil;
import br.com.plataforma.ead.api.dtos.LoginDto;
import br.com.plataforma.ead.api.dtos.SenhaDto;
import br.com.plataforma.ead.api.repositorios.InvalidTokenRepository;
import br.com.plataforma.ead.api.servicos.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/logins", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LoginController {


    @Value("${app.base-url-servidor-react}")
    private String baseUrlServidorReact;
    private final UsuarioService usuarioService;
    private final JwtTokenUtil jwtTokenUtil;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final InvalidTokenRepository invalidTokenRepository;

    private static final Logger logger = LogManager.getLogger(LoginController.class);


    @Autowired
    public LoginController(UsuarioService usuarioService, JwtTokenUtil jwtTokenUtil, EmailService emailService, PasswordEncoder passwordEncoder, InvalidTokenRepository invalidTokenRepository) {
        this.usuarioService = usuarioService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.invalidTokenRepository = invalidTokenRepository;
    }

    @PostMapping("/autenticacao")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Optional<Usuario> usuarioOptional = usuarioService.login(loginDto);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            String token = jwtTokenUtil.generateToken(usuario.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.badRequest().body(MessageUtil.getMessage("credencial.invalida"));
        }
    }

    @GetMapping("/esqueci-senha")
    public ResponseEntity<String> esqueciMinhaSenha(@RequestParam String email) {
        String tokenDefinicaoSenha = jwtTokenUtil.generateToken(email);
        emailService.enviarEmailComToken(email, tokenDefinicaoSenha);
        return ResponseEntity.ok("Email de redefinição de senha enviado com sucesso.");
    }

    @GetMapping("/redefinir-senha")
    public ResponseEntity<String> redefinirSenha(@RequestParam("token") String token) {
        if (token == null || !jwtTokenUtil.validateToken(token, jwtTokenUtil.extractUsername(token))) {
            String invalidTokenUrl = baseUrlServidorReact + "/token-invalido";
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", invalidTokenUrl)
                    .body("Redirecionando para a página de token inválido...");
        }

        String email = jwtTokenUtil.extractUsername(token);
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            String redirectUrl = baseUrlServidorReact + "/recuperar-minha-senha?email=" + email + "&token=" + token;
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", redirectUrl)
                    .body("Redirecionando para a página de alteração de senha...");
        }

        String invalidTokenUrl = baseUrlServidorReact + "/token-invalido";
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", invalidTokenUrl)
                .body("Redirecionando para a página de token inválido...");
    }



    @PutMapping("/alterar-senha")
    public ResponseEntity<String> alterarSenha(@RequestBody SenhaDto senhaDto) {
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(senhaDto.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (!passwordEncoder.matches(senhaDto.getSenhaAtual(), usuario.getSenha())) {
                return ResponseEntity.badRequest().body(MessageUtil.getMessage("senha.incorreta"));
            }

            if (!senhaDto.getNovaSenha().equals(senhaDto.getConfirmarSenha())) {
                return ResponseEntity.badRequest().body(MessageUtil.getMessage("nova.senha.confirmacao"));
            }

            usuario.setSenha(senhaDto.getNovaSenha());
            usuarioService.salvarUsuario(usuario);
            return ResponseEntity.ok(MessageUtil.getMessage("senha.alterada.sucesso"));
        }

        return ResponseEntity.badRequest().body(MessageUtil.getMessage("falha.alterar.senha"));
    }

    @PutMapping("/alterar-senha-recuperada")
    public ResponseEntity<String> alterarSenhaRecuperada(@RequestBody SenhaDto senhaDto, @RequestHeader("Authorization") String token) {
        if (token == null || !jwtTokenUtil.validateToken(token, jwtTokenUtil.extractUsername(token))) {
            String invalidTokenUrl = baseUrlServidorReact + "/token-invalido";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Location", invalidTokenUrl)
                    .body("Redirecionando para a página de token inválido...");
        }

        InvalidToken foundInvalidToken = invalidTokenRepository.findByToken(token);
        if (foundInvalidToken != null) {
            String invalidTokenUrl = baseUrlServidorReact + "/token-invalido";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Location", invalidTokenUrl)
                    .body("Redirecionando para a página de token inválido...");
        }

        String email = jwtTokenUtil.extractUsername(token);
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            usuario.setSenha(senhaDto.getNovaSenha());
            usuarioService.salvarUsuario(usuario);

            salvarTokenInvalido(token);

            return ResponseEntity.ok(MessageUtil.getMessage("senha.alterada.sucesso"));
        }

        return ResponseEntity.badRequest().body(MessageUtil.getMessage("falha.alterar.senha"));
    }

    private void salvarTokenInvalido(String token) {
        try {
            InvalidToken invalidToken = new InvalidToken();
            invalidToken.setToken(token);
            invalidTokenRepository.save(invalidToken);
        } catch (DataIntegrityViolationException e) {
            logger.error("Erro ao inserir token inválido: {}", e.getMessage());
        }
    }


}
