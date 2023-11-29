package br.com.plataforma.ead.api.config;


import br.com.plataforma.ead.api.colecoes.Usuario;
import br.com.plataforma.ead.api.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailIgnoreCase(email);

        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        String perfil = usuarioOptional.get().getPerfil().getNome();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(perfil));

        return User.builder()
                .username(usuarioOptional.get().getEmail())
                .password(usuarioOptional.get().getSenha())
                .authorities(authorities)
                .build();
    }
}
