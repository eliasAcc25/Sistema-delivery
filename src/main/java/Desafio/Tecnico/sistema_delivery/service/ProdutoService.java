package Desafio.Tecnico.sistema_delivery.service;

import Desafio.Tecnico.sistema_delivery.entity.Produto;
import Desafio.Tecnico.sistema_delivery.entity.Restaurante;
import Desafio.Tecnico.sistema_delivery.repository.ProdutoRepository;
import Desafio.Tecnico.sistema_delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    // CADASTRANDO ASSOCIANDO A UM RESTAURANTE PELO ID
    public Produto criarProduto(Long restauranteId, Produto produto) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado!"));
        produto.setRestaurante(restaurante);
        return produtoRepository.save(produto);
    }
    // LISTANDO TODOS PRODUTOS
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // LISTANDO PRODUTOS DE UM RESTAURANTE
    public List<Produto> listarProdutoPorRestaurante(Long restauranteId) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado!"));
        return  produtoRepository.findByRestaurante(restaurante);
    }

    // BUSCAR PRODUTO POR ID
    public Optional<Produto> buscarPorId( Long id) {
        return produtoRepository.findById(id);
    }

    // ARTUALIZANDO PRODUTO
    public Optional<Produto> atualizarProduto(Long id, Produto dadosAtualizados) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(dadosAtualizados.getNome());
            produto.setDescricao(dadosAtualizados.getDescricao());
            produto.setPreco(dadosAtualizados.getPreco());
            produto.setCategoria(dadosAtualizados.getCategoria());
            return produtoRepository.save(produto);
        });
    }

    // EXCLUINDO PRODUTO
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

}
