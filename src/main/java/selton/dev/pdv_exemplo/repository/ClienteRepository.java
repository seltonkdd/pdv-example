package selton.dev.pdv_exemplo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import selton.dev.pdv_exemplo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    public Optional<Cliente> findByCpf(String cpf);

    public boolean existsByCpf(String cpf);
}
