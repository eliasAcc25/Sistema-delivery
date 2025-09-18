package Desafio.Tecnico.sistema_delivery.service;

import Desafio.Tecnico.sistema_delivery.entity.Usuario;
import Desafio.Tecnico.sistema_delivery.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    //TEMOS INJEÇÃO DE DEPENDÊNCIA
    // "EI SPRING, ME DÊ UM REPOSITORY PARA TRABALHAR!"
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        //VALIDAÇÃO SE EMAIL EXISTE
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado!");
        }
        if (usuario.getNome().length() <2) {
            throw new RuntimeException("O nome deve ter pelo menos 2 caracteres");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        //BUSCAR SE USUARIO EXISTE!
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        //VALIDAÇÃO SE EMAIL DE USUÁRIO JÁ EXISTE
        if (!usuario.getEmail().equals(usuarioAtualizado.getEmail()) &&
        usuarioRepository.existsByEmail(usuarioAtualizado.getEmail())) {
            throw new RuntimeException("Este email já está em usa");
        }
        //ATUALIZANDO: DADOS
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuario.setSenha(usuarioAtualizado.getSenha());
        }
        // SALVAR MUDANÇAS
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
        usuarioRepository.deleteById(id);
    }

    }

}
