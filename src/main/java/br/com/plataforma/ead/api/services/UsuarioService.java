package br.com.plataforma.ead.api.services;


import br.com.plataforma.ead.api.colecoes.Endereco;
import br.com.plataforma.ead.api.colecoes.Telefone;
import br.com.plataforma.ead.api.colecoes.Usuario;
import br.com.plataforma.ead.api.dtos.LoginDto;
import br.com.plataforma.ead.api.repositorios.EnderecoRepository;
import br.com.plataforma.ead.api.repositorios.TelefoneRepository;
import br.com.plataforma.ead.api.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario) {
         usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        for (Endereco endereco : usuario.getEnderecos()) {
            endereco.setIdUsuario(usuarioSalvo.getId());
            enderecoRepository.save(endereco);
        }

        usuario.getTelefones();

        for (Telefone telefone : usuario.getTelefones()) {
            telefone.setIdUsuario(usuarioSalvo.getId());
            telefoneRepository.save(telefone);
        }
        return usuario;
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(String id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

/*        if (usuario != null) {
            // Carregar os telefones e endereços associados
            List<Telefone> telefones = telefoneRepository.findByUsuarioId(id);
            List<Endereco> enderecos = enderecoRepository.findByUsuarioId(id);

            usuario.setTelefones(telefones);
            usuario.setEnderecos(enderecos);
        }*/

        return usuario;
    }


    public void excluirUsuario(String id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> consultarUsuarios(String nome, String cpf, String matricula) {
        return usuarioRepository.buscarUsuariosPorNomeCpfMatricula(nome, cpf, matricula);
    }

    public Optional<Usuario> login(LoginDto usuarioDto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailIgnoreCase(usuarioDto.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(usuarioDto.getSenha(), usuario.getSenha())) {
                return Optional.of(usuario);
            }
        }

        return Optional.empty();
    }


    public LoginDto findByNomeUsuario(String nomeUsuario) {
        Optional<Usuario> usuarioDtoOptional = usuarioRepository.findByNome
                (nomeUsuario);

        if (usuarioDtoOptional.isPresent()) {
            Usuario usuario = usuarioDtoOptional.get();
            return usuarioToUsuarioDto(usuario);
        }

        return null;
    }

    public LoginDto usuarioToUsuarioDto(Usuario usuario) {
        return new LoginDto(
                usuario.getNome(),
                usuario.getSenha()
        );
    }

    public Optional<Usuario>  findByEmail(String email){
        return usuarioRepository.findByEmailIgnoreCase(email);
    }

}
