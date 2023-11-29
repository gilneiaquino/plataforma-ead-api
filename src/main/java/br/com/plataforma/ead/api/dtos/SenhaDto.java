package br.com.plataforma.ead.api.dtos;

public class SenhaDto {

    private String email;
    private String senhaAtual;
    private String novaSenha;
    private String confirmarSenha;

    public SenhaDto(String senhaAtual, String novaSenha, String confirmarSenha, String email) {
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
        this.confirmarSenha = confirmarSenha;
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    // getters e setters
}
