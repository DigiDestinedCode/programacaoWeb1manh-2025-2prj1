package com.senac.games.repository;

import com.senac.games.entity.Jogo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Jogo p SET p.status = -1 WHERE p.id = :jogoId")
    void apagadoLogicoJogo(@Param("jogoId") Integer jogoId);

    @Query("SELECT p from Jogo p WHERE p.status >= 0")
    List<Jogo> listarJogos();

    @Query("SELECT p from Jogo p where p.id=:jogoId AND p.status >=0")
    Jogo obterJogoPeloId(@Param("jogoId") Integer jogoId);
}
