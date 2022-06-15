package br.senac.go.api.service;

import br.senac.go.api.repository.WebUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final WebUserRepository webUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return webUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inválido"));
    }
}
