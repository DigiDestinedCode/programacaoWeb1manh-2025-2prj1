package com.senac.games.repository;

import com.senac.games.entity.Participante;
import com.senac.games.entity.Patrocinador;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Patrocinador p SET p.status = -1 WHERE p.id = :patrocinadorId")
    void apagadoLogicoPatrocinador(@Param("patrocinadorId") Integer patrocinadorId);

    @Query("SELECT p from Patrocinador p WHERE p.status >= 0")
    List<Patrocinador> listarPatrocinadores();

    @Query("SELECT p from Patrocinador p where p.id=:patrocinadorId AND p.status >=0")
    Patrocinador obterPatrocinadorPeloId(Integer patrocinadorId);
}
