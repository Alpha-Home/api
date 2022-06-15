package br.senac.go.api.service;

import br.senac.go.api.model.Endereco;
import br.senac.go.api.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    @Transactional
    public void salvar(Endereco endereco) {
        enderecoRepository.save(endereco);
    }
}
