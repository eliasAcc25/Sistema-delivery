package Desafio.Tecnico.sistema_delivery.controller;

import Desafio.Tecnico.sistema_delivery.entity.Produto;
import Desafio.Tecnico.sistema_delivery.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // CADASTRANDO PRODUTO
    @PostMapping("/restaurante/{restauranteId}")
    public ResponseEntity<Produto> criarProduto(
            @PathVariable Long restauranteId,
            @RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(restauranteId, produto);
        return ResponseEntity.status(201).body(novoProduto);
    }

    // LISTAGEM DE TODOS OS PRODUTOS
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    // LISTAGEM DE PRODUTO POR RESTAURANTE
    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<Produto>> listarProdutosPorRestaurante(@PathVariable Long restauranteId) {
        List<Produto> produtos = produtoService.listarProdutoPorRestaurante(restauranteId);
        return ResponseEntity.ok(produtos);
    }

    // FAZ BUSCDO PELO ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ATUALIZA PRODUTO
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto dadosAtualizados) {
        Optional<Produto> produtoAtualizado = produtoService.atualizarProduto(id, dadosAtualizados);
        return produtoAtualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // EXCLUIR PRODUTO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
