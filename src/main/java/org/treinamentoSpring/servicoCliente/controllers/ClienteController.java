package org.treinamentoSpring.servicoCliente.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.treinamentoSpring.servicoCliente.entities.Cliente;
import org.treinamentoSpring.servicoCliente.repositories.IClienteRepository;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    public IClienteRepository clientes;

    @GetMapping
    public List<Cliente> retornaClientes () {
        return clientes.findAll();
    }

    @GetMapping("/{id}")
    public Cliente retornaCliente (@PathVariable Integer id) {
        return clientes.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastraCliente (@RequestBody Cliente cliente) {
        return clientes.save(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizaCliente (@PathVariable Integer id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteEncontrado = clientes.findById(id);
        
        if (clienteEncontrado.isPresent()) {
            cliente.setId(clienteEncontrado.get().getId());
            clientes.save(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }
}
