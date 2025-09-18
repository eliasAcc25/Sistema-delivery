package Desafio.Tecnico.sistema_delivery.controller;

import Desafio.Tecnico.sistema_delivery.entity.Usuario;
import Desafio.Tecnico.sistema_delivery.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    //INJETA O COZINHEIRO, NO CASO A SERVICE
    @Autowired
    private UsuarioService usuarioService;

    //ENDPOINT DE LISTAR OS USUARIOS (GET)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
    //FAZ BUSCA DO USUARIO PELO ID (GET)
    public ResponseEntity<Usuario> buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //CRIAR NOVO USUARIO (POST)
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        try {
            Usuario usuarioCriado = usuarioService.criarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado); // status 201 created
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build(); // status 400 bad request
        }
    }
    //ATUALIZAR USUARIO (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioAtualizado); // status 200
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // status 404
        }


    }
    //DELETAR USUARIO (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity< Void> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.notFound().build(); // status 204
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build(); // status 404
        }
    }
    //BUSCAR USUARIO PELO EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
// @RestController:
// Diz que essa classe vai receber requisições HTTP e automaticamente converte objetos Java em JSON

// @RequestMapping("/usuarios"):
// Todas as URLs desta classe começam com /usuarios

// @GetMapping, @PostMapping
//Define qual verbo HTTP usar (GET, POST, PUT, DELETE)

// @PathVariable:
// Pega valor da URL: /usuarios/123 → id = 123

//@RequestBody:
// Pega o JSON enviado no corpo da requisição e converte para objeto Java

// ResponseEntity:
// Permite controlar o status HTTP da resposta (200, 404, 201, etc.)