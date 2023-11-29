package br.com.plataforma.ead.api.dtos;

import jakarta.validation.constraints.NotEmpty;

public class LoginDto {

    @NotEmpty
    private String senha;

    @NotEmpty
    private String email;


    public LoginDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
