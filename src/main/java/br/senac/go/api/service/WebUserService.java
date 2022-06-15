package br.senac.go.api.service;

import br.senac.go.api.exception.NegocioException;
import br.senac.go.api.model.WebUser;
import br.senac.go.api.repository.WebUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class WebUserService {

    private WebUserRepository webUserRepository;

    @Transactional
    public void salvar(WebUser webUser) {
        boolean emailJaUtilizado = webUserRepository.findByEmail(webUser.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(webUser));

        if (emailJaUtilizado) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }

        webUser.setPassword(new BCryptPasswordEncoder().encode(webUser.getPassword()));

        webUserRepository.save(webUser);
    }
}
