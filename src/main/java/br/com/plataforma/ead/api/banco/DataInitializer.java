package br.com.plataforma.ead.api.banco;


import br.com.plataforma.ead.api.colecoes.*;
import br.com.plataforma.ead.api.repositorios.CorRepository;
import br.com.plataforma.ead.api.repositorios.CursoRepository;
import br.com.plataforma.ead.api.repositorios.PerfilRepository;
import br.com.plataforma.ead.api.servicos.UsuarioService;
import br.com.plataforma.ead.api.servicos.AgrupamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    public static final String MASCULINO = "Masculino";


    private CursoRepository cursoRepository;


    private final UsuarioService usuarioService;

    private final PerfilRepository perfilRepository;


    private final AgrupamentoService agrupamentoService;

    private final CorRepository corRepository;



    @Autowired
    public DataInitializer(CursoRepository cursoRepository, UsuarioService usuarioService, PerfilRepository perfilRepository, AgrupamentoService agrupamentoService, CorRepository corRepository) {
        this.cursoRepository = cursoRepository;
        this.usuarioService = usuarioService;
        this.perfilRepository = perfilRepository;
        this.agrupamentoService = agrupamentoService;
        this.corRepository = corRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        carregarCursos();
        carregarListaUsuario();
        carregarPerfis();
        carregarAgrupamentos();
        carregarCores();

    }

    private void carregarCores() {
        List<Cor> coresPredefinidas = Arrays.asList(
                new Cor("Azul", "bg-primary"),
                new Cor("Verde", "bg-success"),
                new Cor("Vermelho", "bg-danger"),
                new Cor("Amarelo", "bg-warning"),
                new Cor("Cinza", "bg-secondary"),
                new Cor("Preto", "bg-secondary")
        );

        corRepository.saveAll(coresPredefinidas);
    }

    private void carregarPerfis() {
        Perfil perfil1 = new Perfil("1", "ROLE_ADMIN");
        Perfil perfil2 = new Perfil("2", "ROLE_ALUNO");
        Perfil perfil3 = new Perfil("3", "ROLE_PROFESSOR");

        perfilRepository.saveAll(Arrays.asList(perfil1, perfil2, perfil3));
    }

    private void carregarAgrupamentos() {
        Agrupamento agrupamento1 = new Agrupamento("1", "Front End");
        Agrupamento agrupamento2 = new Agrupamento("2", "backend");
        Agrupamento agrupamento3 = new Agrupamento("3", "Outros");

        agrupamentoService.salvarTodos(Arrays.asList(agrupamento1, agrupamento2, agrupamento3));
    }

    private void carregarCursos() {
        Curso curso1 = new Curso();
        curso1.setTitulo("Curso de Programação");
        curso1.setProgresso(25);
        curso1.setConquistas(Arrays.asList("Conquista 1", "Conquista 2"));
        curso1.setDescricaoResumida("Descrição resumida do curso 1");
        curso1.setDescricaoCompleta("Descrição completa do curso 1");
        curso1.setCor("Azul");
        curso1.setAgrupamento("Agrupamento 1");

        Curso curso2 = new Curso();
        curso2.setTitulo("Curso de Matemática");
        curso2.setProgresso(50);
        curso2.setConquistas(Arrays.asList("Conquista 3", "Conquista 4"));
        curso2.setDescricaoResumida("Descrição resumida do curso 2");
        curso2.setDescricaoCompleta("Descrição completa do curso 2");
        curso2.setCor("Verde");
        curso2.setAgrupamento("Agrupamento 2");

        Curso curso3 = new Curso();
        curso3.setTitulo("Curso de História");
        curso3.setProgresso(75);
        curso3.setConquistas(Arrays.asList("Conquista 5", "Conquista 6"));
        curso3.setDescricaoResumida("Descrição resumida do curso 3");
        curso3.setDescricaoCompleta("Descrição completa do curso 3");
        curso3.setCor("Vermelho");
        curso3.setAgrupamento("Agrupamento 3");

        // Salvar os cursos no MongoDB
        cursoRepository.saveAll(Arrays.asList(curso1, curso2, curso3));
    }


    public void carregarListaUsuario() {

        Usuario usuario1 = new Usuario(
                "1", // ID
                "Novo usuario plataforma", // Nome
                LocalDate.of(1990, 1, 15), // Data de Nascimento
                MASCULINO, // Gênero
                "12345", // Matrícula
                new ArrayList<>(), // Lista de endereços (vazia)
                new ArrayList<>(), // Lista de telefones (vazia)
                "joao@email.com", // Email
                "123456789", // CPF
                "senha123", // Senha
                new Perfil("1", "ROLE_ADMIN")
        );


        Usuario usuario2 = new Usuario(
                "2", // ID
                "Maria", // Nome
                LocalDate.of(1985, 4, 10), // Data de Nascimento
                "Feminino", // Gênero
                "54321", // Matrícula
                new ArrayList<>(), // Lista de endereços (vazia)
                new ArrayList<>(), // Lista de telefones (vazia)
                "maria@email.com", // Email
                "987654321", // CPF
                "senha456", // Senha
                new Perfil("2", "ROLE_ALUNO")
        );

        Usuario usuario3 = new Usuario(
                "3", // ID
                "Mega lima", // Nome
                LocalDate.of(1988, 7, 25), // Data de Nascimento
                MASCULINO, // Gênero
                "67890", // Matrícula
                new ArrayList<>(), // Lista de endereços (vazia)
                new ArrayList<>(), // Lista de telefones (vazia)
                "carlos@email.com", // Email
                "01156561116", // CPF
                "12345", // Senha
                new Perfil("2", "ROLE_PROFESSOR")

        );

        Usuario usuario4 = new Usuario(
                "4", // ID
                "Mega lima", // Nome
                LocalDate.of(1988, 7, 25), // Data de Nascimento
                MASCULINO, // Gênero
                "67890", // Matrícula
                new ArrayList<>(), // Lista de endereços (vazia)
                new ArrayList<>(), // Lista de telefones (vazia)
                "gilnei.aquino@gmail.com", // Email
                "01156561116", // CPF
                "12345", // Senha
                new Perfil("2", "ROLE_PROFESSOR")

        );


        usuarioService.salvarUsuario(usuario1);
        usuarioService.salvarUsuario(usuario2);
        usuarioService.salvarUsuario(usuario3);
        usuarioService.salvarUsuario(usuario4);




    }


}
