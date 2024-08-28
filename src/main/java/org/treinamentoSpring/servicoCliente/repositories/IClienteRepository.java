package org.treinamentoSpring.servicoCliente.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.treinamentoSpring.servicoCliente.entities.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
    List<Cliente> findAll();

    Optional<Cliente> findById(Integer id);
}
