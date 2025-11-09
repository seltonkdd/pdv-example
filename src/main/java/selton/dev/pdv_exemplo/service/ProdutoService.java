package selton.dev.pdv_exemplo.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import selton.dev.pdv_exemplo.dto.ProdutoDTO;
import selton.dev.pdv_exemplo.exception.custom.EntityNotFoundException;
import selton.dev.pdv_exemplo.model.Produto;
import selton.dev.pdv_exemplo.repository.ProdutoRepository;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public void salvar(ProdutoDTO dto) {
        Produto novoProduto = new Produto();
        novoProduto.setNomeProduto(dto.nome());
        novoProduto.setPreco(dto.preco());
        novoProduto.setPrecoFidelidade(dto.precoFidelidade());
        produtoRepository.save(novoProduto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto"));
    }

    public Produto editarPorId(Long id, ProdutoDTO dto) {
        Produto produtoExistente = buscarPorId(id);
        if (dto.nome() != null) produtoExistente.setNomeProduto(dto.nome());
        if (dto.preco() != null) produtoExistente.setPreco(dto.preco());
        if (dto.precoFidelidade() != null) produtoExistente.setPrecoFidelidade(dto.precoFidelidade());
        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Produto produto) {
        produtoRepository.delete(produto);
    }
}
