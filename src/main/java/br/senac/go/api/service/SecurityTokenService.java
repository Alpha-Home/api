package br.senac.go.api.service;

import br.senac.go.api.exception.NegocioException;
import br.senac.go.api.model.WebUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SecurityTokenService {


    private String issuer = "api";

    private String expiracao = "3600000";

    private String chaveSecreta = "secretar";


    public String gerarTokenUsuario(Authentication authentication) {
        WebUser clienteLogado = (WebUser) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);

        Date atual = new Date();
        Date expiracaoToken = new Date(atual.getTime() + Long.parseLong(expiracao));

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(clienteLogado.getId().toString())
                .withIssuedAt(atual)
                .withExpiresAt(expiracaoToken)
                .sign(algorithm);
    }

    public Long getIdUsuario(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            String id = decodedJWT.getSubject();

            return Long.parseLong(id);
        } catch (JWTDecodeException exception) {
            throw new NegocioException("Token inválido");
        }
    }
}
