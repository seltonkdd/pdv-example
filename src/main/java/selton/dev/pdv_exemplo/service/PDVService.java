package selton.dev.pdv_exemplo.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import selton.dev.pdv_exemplo.dto.PDVRequest;
import selton.dev.pdv_exemplo.dto.PDVResponse;
import selton.dev.pdv_exemplo.exception.custom.EntityNotFoundException;
import selton.dev.pdv_exemplo.model.Produto;

@Service
@RequiredArgsConstructor
public class PDVService {
    
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PDVResponse consultarPreco(PDVRequest request) {
        Produto produto = produtoService.buscarPorId(request.id());
        Double preco = produto.getPreco();

        if (request.cpfCliente() != null) {
            if (!clienteService.existe(request.cpfCliente())) throw new EntityNotFoundException("Cliente");
            if (produto.getPrecoFidelidade() != null) preco = produto.getPrecoFidelidade(); 
        }

        return new PDVResponse(produto.getId(), produto.getNomeProduto(), preco);
    }
}
