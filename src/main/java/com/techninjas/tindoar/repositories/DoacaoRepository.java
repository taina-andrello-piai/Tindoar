package com.techninjas.tindoar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techninjas.tindoar.models.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer>{

}
