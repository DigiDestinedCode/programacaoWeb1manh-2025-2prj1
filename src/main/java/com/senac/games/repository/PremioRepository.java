package com.senac.games.repository;


import com.senac.games.entity.Participante;
import com.senac.games.entity.Premio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Premio p SET p.status = -1 WHERE p.id = :premioId")
    void apagadoLogicoPremio(@Param("premioId") Integer premioId);

    @Query("SELECT p from Premio p WHERE p.status >= 0")
    List<Premio> listarPremios();

    @Query("SELECT p from Premio p where p.id=:premioId AND p.status >=0")
    Premio obterPremioPeloId(Integer premioId);
}