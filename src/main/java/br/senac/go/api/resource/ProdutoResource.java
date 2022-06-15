package br.senac.go.api.resource;

import br.senac.go.api.model.Produto;
import br.senac.go.api.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> obterTudoProduto(){
        return produtoService.obterTudoProduto();
    }


    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long produtoId) {
        return ResponseEntity.ok(produtoService.buscarPorId(produtoId));
    }

}
