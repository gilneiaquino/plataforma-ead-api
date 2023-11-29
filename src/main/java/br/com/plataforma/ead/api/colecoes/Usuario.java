package br.com.plataforma.ead.api.colecoes;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    @NotBlank(message = "O nome não pode estar em branco.")
    @NotNull(message = "O nome não pode ser nulo.")
    private String nome;

    @NotNull(message = "A data de nascimento não pode ser nula.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O gênero não pode estar em branco.")
    @NotNull(message = "O gênero não pode ser nulo.")
    private String genero;

    @NotBlank(message = "A Matricula não pode estar em branco.")
    @NotNull(message = "A Matricula não pode ser nulo.")
    private String matricula;

    @DBRef
    private List<Endereco> enderecos;
    @DBRef
    private List<Telefone> telefones;

    private Perfil perfil;
    @Email
    @Indexed(unique = true)
    private String email;

    @CPF
    private String cpf;

    private String senha;

    public Usuario(String id, String nome, LocalDate dataNascimento, String genero, String matricula, List<Endereco> enderecos, List<Telefone> telefones, String email, String cpf, String senha, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.matricula = matricula;
        this.enderecos = enderecos;
        this.telefones = telefones;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
