package com.techninjas.tindoar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techninjas.tindoar.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
