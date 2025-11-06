package selton.dev.pdv_exemplo.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import selton.dev.pdv_exemplo.model.Produto;
import selton.dev.pdv_exemplo.repository.ProdutoRepository;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public void salvar(Produto produto) {
        produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    public void deletar(Produto produto) {
        produtoRepository.delete(produto);
    }
}
