package Desafio.Tecnico.sistema_delivery.repository;

import Desafio.Tecnico.sistema_delivery.entity.Produto;
import Desafio.Tecnico.sistema_delivery.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByRestaurante(Restaurante restaurante);

    // Corrigindo o método para buscar produtos pelo nome do restaurante
    List<Produto> findByRestauranteNome(String nomeRestaurante);

    // Alternativamente, usando @Query para maior clareza
    @Query("SELECT p FROM Produto p WHERE p.restaurante.nome = :nomeRestaurante")
    List<Produto> findByRestauranteNomeQuery(@Param("nomeRestaurante") String nomeRestaurante);

    // Buscar produtos por categoria
    List<Produto> findByCategoria(String categoria);

    // Buscar produtos por nome (parcial)
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
