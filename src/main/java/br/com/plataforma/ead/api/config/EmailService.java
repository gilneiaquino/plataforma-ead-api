package br.com.plataforma.ead.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${app.base-url-servidor-spring}")
    private String baseUrlServidorSpring;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmailComToken(String destinatario, String token) {
        String assunto = "Recuperação de Senha - Token de Verificação";
        String corpo = construirCorpoEmail(token);
        enviarEmail(destinatario, assunto, corpo);
    }

    private String construirCorpoEmail(String token) {
        String linkRedefinicaoSenha = gerarLinkRedefinicaoSenha(token);
        String linkDefinicao = "\n\nClique no link a seguir para redefinir sua senha:\n" + linkRedefinicaoSenha;

        StringBuilder corpo = new StringBuilder();
        corpo.append("Olá,\n\n")
                .append("Você solicitou a recuperação de senha. Utilize o seguinte link para redefinir sua senha: ")
                .append("\n\n").append(linkDefinicao).append("\n\n")
                .append("Atenciosamente,\n")
                .append("Equipe Escolar");

        return corpo.toString();
    }

    private String gerarLinkRedefinicaoSenha(String token) {
        return baseUrlServidorSpring + "/api/logins/redefinir-senha?token=" + token;
    }

    private void enviarEmail(String destinatario, String assunto, String corpo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(destinatario);
        mensagem.setSubject(assunto);
        mensagem.setText(corpo);

        javaMailSender.send(mensagem);
    }
}
