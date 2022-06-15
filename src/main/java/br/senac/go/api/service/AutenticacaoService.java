package br.senac.go.api.service;


import br.senac.go.api.autenticacao.LoginModel;
import br.senac.go.api.autenticacao.TokenModel;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final SecurityTokenService tokenService;

    public TokenModel autenticar(LoginModel loginModel) {
        UsernamePasswordAuthenticationToken login = loginModel.toUsernamePasswordAuthenticationToken();
        Authentication authentication = authenticationManager.authenticate(login);
        String token = tokenService.gerarTokenUsuario(authentication);
        return new TokenModel(token, "Bearer");
    }
}
