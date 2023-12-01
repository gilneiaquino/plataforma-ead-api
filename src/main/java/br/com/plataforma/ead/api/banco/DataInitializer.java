package br.com.plataforma.ead.api.banco;


import br.com.plataforma.ead.api.colecoes.*;
import br.com.plataforma.ead.api.enums.SituacaoCurso;
import br.com.plataforma.ead.api.repositorios.CorRepository;
import br.com.plataforma.ead.api.repositorios.CursoRepository;
import br.com.plataforma.ead.api.repositorios.PerfilRepository;
import br.com.plataforma.ead.api.servicos.UsuarioCursoService;
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


    private final CursoRepository cursoRepository;


    private final UsuarioService usuarioService;

    private final PerfilRepository perfilRepository;


    private final AgrupamentoService agrupamentoService;

    private final CorRepository corRepository;

    private final UsuarioCursoService usuarioCursoService;


    @Autowired
    public DataInitializer(CursoRepository cursoRepository, UsuarioService usuarioService, PerfilRepository perfilRepository, AgrupamentoService agrupamentoService, CorRepository corRepository, UsuarioCursoService usuarioCursoService) {
        this.cursoRepository = cursoRepository;
        this.usuarioService = usuarioService;
        this.perfilRepository = perfilRepository;
        this.agrupamentoService = agrupamentoService;
        this.corRepository = corRepository;
        this.usuarioCursoService = usuarioCursoService;

    }

    @Override
    public void run(String... args) throws Exception {
        carregarCursos();
        carregarListaUsuario();
        carregarPerfis();
        carregarAgrupamentos();
        carregarCores();
        carregarUsuarioCurso();

    }

    private void carregarUsuarioCurso() {
        // Criar instâncias de UsuarioCurso associadas ao Usuario de ID "4" (no seu caso, o usuário 4)
        UsuarioCurso usuarioCurso1 = new UsuarioCurso();
        usuarioCurso1.setUsuarioId("4");
        usuarioCurso1.setCursoId("1");
        usuarioCurso1.setSituacao(SituacaoCurso.CONCLUIDO);

        UsuarioCurso usuarioCurso2 = new UsuarioCurso();
        usuarioCurso2.setUsuarioId("4");
        usuarioCurso2.setCursoId("2");
        usuarioCurso2.setSituacao(SituacaoCurso.EM_ANDAMENTO);

        // Salvar os registros de UsuarioCurso
        usuarioCursoService.salvar(usuarioCurso1);
        usuarioCursoService.salvar(usuarioCurso2);
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
        List<String> areasTI = Arrays.asList(
                "Java", "PHP", "UX", "Banco de Dados MYSQL", "SQL", "DevOps", "JavaScript",
                "Redes de Computadores", "Segurança da Informação", "Cloud Computing",
                "Desenvolvimento Web", "Inteligência Artificial", "Machine Learning",
                "Data Science", "Blockchain"
        );

        List<Curso> cursosTI = new ArrayList<>();

        int i = 1;
        for (String area : areasTI) {
            Curso curso = new Curso();
            curso.setId(String.valueOf(i));
            curso.setTitulo("Curso de " + area);
            curso.setProgresso(0); // Definir progresso inicial como 0
            curso.setDescricaoResumida("Este é um curso de " + area + " destinado a desenvolvedores interessados em aprender mais sobre essa tecnologia.");
            curso.setDescricaoCompleta("O curso de " + area + " abrange tópicos avançados e práticos para desenvolvedores em todos os níveis, desde iniciantes até avançados. Inclui módulos de aprendizado prático, projetos e avaliações.");
            curso.setCor("bg-success");
            curso.setAgrupamento("Área de TI");

            cursosTI.add(curso);
            i++;
        }

        // Salvar os cursos de TI no MongoDB
        cursoRepository.saveAll(cursosTI);
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
