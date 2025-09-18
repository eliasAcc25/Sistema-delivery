package Desafio.Tecnico.sistema_delivery.controller;

import Desafio.Tecnico.sistema_delivery.entity.Restaurante;
import Desafio.Tecnico.sistema_delivery.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    // Cadastrar restaurante
    @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(@RequestBody Restaurante restaurante) {
        Restaurante novoRestaurante = restauranteService.criarRestaurante(restaurante);
        return ResponseEntity.status(201).body(novoRestaurante);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Restaurante>> listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteService.listarRestaurante();
        return ResponseEntity.ok(restaurantes);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteService.buscarPorId(id);
        return restaurante.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Buscar por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Restaurante> buscarPorNome(@PathVariable String nome) {
        Optional<Restaurante> restaurante = restauranteService.buscarPorNome(nome);
        return restaurante.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Atualizar restaurante
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizarRestaurante(@PathVariable Long id, @RequestBody Restaurante dadosAtualizados) {
        Optional<Restaurante> restauranteAtualizado = restauranteService.atualizarRestaurante(id, dadosAtualizados);
        return restauranteAtualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Excluir restaurante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) {
        restauranteService.excluirPeloId(id);
        return ResponseEntity.noContent().build();
    }
}
