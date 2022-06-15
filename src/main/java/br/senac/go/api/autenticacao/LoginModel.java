package br.senac.go.api.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginModel {


    @Email(message = "O e-mail é inválido")
    @NotBlank(message = "O e-mail não pode ser vazio")
    private String email;


    @NotBlank(message = "A senha não pode ser vazia")
    private String password;

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}
