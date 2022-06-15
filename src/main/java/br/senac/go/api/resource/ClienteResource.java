package br.senac.go.api.resource;

import br.senac.go.api.model.Cliente;
import br.senac.go.api.service.ClienteService;
import br.senac.go.api.service.EnderecoService;
import br.senac.go.api.service.WebUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    private ClienteService clienteService;

    private WebUserService webUserService;

    private EnderecoService enderecoService;


    @GetMapping("/{clienteId}")
    public Optional<Cliente> buscarPorId(@PathVariable Long clienteId) {
        return Optional.ofNullable(clienteService.buscar(clienteId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){

            webUserService.salvar(cliente.getWebUser());
            enderecoService.salvar(cliente.getEndereco());

        return  clienteService.salvar(cliente);
    }
}
