package br.senac.go.api.service;

import br.senac.go.api.exception.NegocioException;
import br.senac.go.api.model.Produto;
import br.senac.go.api.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public Produto buscarPorId(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new NegocioException("Produto não encontrado"));
    }

    public List<Produto> obterTudoProduto() {

        List<Produto> produto = produtoRepository.findAll();

        if (produto.isEmpty()) {
            throw new NegocioException("Produto não encontrado");
        }

        return produto;
    }


}
