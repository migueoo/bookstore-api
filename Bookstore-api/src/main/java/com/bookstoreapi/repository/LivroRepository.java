package com.bookstoreapi.repository;

import com.bookstoreapi.dto.LivroDto;
import com.bookstoreapi.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

}
