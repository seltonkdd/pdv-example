package selton.dev.pdv_exemplo.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import selton.dev.pdv_exemplo.dto.ClienteDTO;
import selton.dev.pdv_exemplo.model.Cliente;
import selton.dev.pdv_exemplo.repository.ClienteRepository;
import selton.dev.pdv_exemplo.utils.CpfUtils;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public void salvar(ClienteDTO dto) {
        String cpf = dto.cpf();

        if (!CpfUtils.isValid(cpf)) throw new IllegalArgumentException("Cpf inválido");

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(cpf);
        clienteRepository.save(cliente);
    }

    public ClienteDTO buscarPorCpf(String cpf) {
        if (!CpfUtils.isValid(cpf)) throw new IllegalArgumentException("Cpf inválido");

        Cliente cliente = clienteRepository.findByCpf(cpf)
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        return new ClienteDTO(cliente.getNome(), cliente.getCpf());
    }

    public boolean existe(String cpf) {
        if (!CpfUtils.isValid(cpf)) throw new IllegalArgumentException("Cpf inválido");
        return clienteRepository.existsByCpf(cpf);
    }
}
