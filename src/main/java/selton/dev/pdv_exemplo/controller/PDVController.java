package selton.dev.pdv_exemplo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import selton.dev.pdv_exemplo.dto.ClienteDTO;
import selton.dev.pdv_exemplo.dto.PDVRequest;
import selton.dev.pdv_exemplo.dto.PDVResponse;
import selton.dev.pdv_exemplo.model.Produto;
import selton.dev.pdv_exemplo.service.ClienteService;
import selton.dev.pdv_exemplo.service.PDVService;
import selton.dev.pdv_exemplo.service.ProdutoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/pdv")
@RequiredArgsConstructor
public class PDVController {
    
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PDVService pdvService;
    
    @PostMapping
    public ResponseEntity<PDVResponse> consultarPreco(@RequestBody PDVRequest request) {
        return ResponseEntity.ok().body(pdvService.consultarPreco(request));
    }

    @PostMapping("/clientes")
    public ResponseEntity<Void> criarCliente(@RequestBody ClienteDTO dto) {
        clienteService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/clientes")
    public ResponseEntity<ClienteDTO> buscarCliente(@RequestParam String cpf) {
        return ResponseEntity.ok().body(clienteService.buscarPorCpf(cpf));
    }

    @PostMapping("/produtos")
    public ResponseEntity<Void> criarProduto(@RequestBody Produto produto) {
        produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.buscarPorId(id));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        produtoService.deletar(produto);
        return ResponseEntity.noContent().build();
    }
}
