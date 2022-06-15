
package br.senac.go.api.filter;

import br.senac.go.api.model.WebUser;
import br.senac.go.api.service.SecurityTokenService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {


    private final SecurityTokenService securityTokenService;

    private String getToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        if (token == null || token.trim().isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.replace("Bearer ", "");
    }

    private void autenticarUsuario(String token) {
        try {
            Long idUsuario = securityTokenService.getIdUsuario(token);

            WebUser webUser = new WebUser();

            webUser.setId(idUsuario);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(webUser, token, webUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(httpServletRequest);


        if (token != null) {
            autenticarUsuario(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
