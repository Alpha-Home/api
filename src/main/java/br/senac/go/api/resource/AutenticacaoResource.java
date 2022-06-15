package br.senac.go.api.resource;


import br.senac.go.api.autenticacao.LoginModel;
import br.senac.go.api.autenticacao.TokenModel;
import br.senac.go.api.service.AutenticacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class AutenticacaoResource {

    private final AutenticacaoService autenticacaoService;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TokenModel autenticar(@Valid @RequestBody LoginModel loginDto) {
        return autenticacaoService.autenticar(loginDto);
    }
}
