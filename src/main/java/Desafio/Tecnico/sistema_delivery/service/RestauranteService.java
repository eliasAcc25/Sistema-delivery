package Desafio.Tecnico.sistema_delivery.service;


import Desafio.Tecnico.sistema_delivery.entity.Restaurante;
import Desafio.Tecnico.sistema_delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    //INJETANDO ACESSO AO BANCO
    @Autowired
    private RestauranteRepository restauranteRepository;

    //CADASTRANDO RESTAURANTE
    public Restaurante criarRestaurante( Restaurante restaurante) {
        return restauranteRepository.save(restaurante);

    }
    //LISTANDO TODOS
    public List<Restaurante> listarRestaurante() {
        return restauranteRepository.findAll();

    }
    //BUSCANDO POR ID
    public Optional<Restaurante> buscarPorId(Long id) {
        return restauranteRepository.findById(id);
    }
    //BUSCANDO POR NOME
    public Optional<Restaurante> buscarPorNome(String nome) {
        return restauranteRepository.findByNome(nome);
    }
    //ATUALIZANDO RESTAURANTE
    public Optional<Restaurante> atualizarRestaurante(Long id, Restaurante dadosAtualizados) {
        return restauranteRepository.findById(id).map(restaurante -> {
            restaurante.setNome(dadosAtualizados.getNome());
            restaurante.setEndereco(dadosAtualizados.getEndereco());
            restaurante.setTelefone(dadosAtualizados.getTelefone());
            restaurante.setCategoria(dadosAtualizados.getCategoria());
            return restauranteRepository.save(restaurante);
        });
    }
    //EXCLUINDO RESTAURANTE
    public void excluirPeloId(Long id) {
        restauranteRepository.deleteById(id);
    }
}
