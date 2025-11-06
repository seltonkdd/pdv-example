package selton.dev.pdv_exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import selton.dev.pdv_exemplo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
