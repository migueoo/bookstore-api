package com.bookstoreapi.service;


import com.bookstoreapi.dto.LivroDto;
import com.bookstoreapi.entity.LivroEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface LivroService {

    List<LivroEntity> listarLivros();

    ResponseEntity<LivroEntity> findLivroById(Long id);

    void adicionarLivro(LivroDto livroDto);

    ResponseEntity<String> updateByLivroId(LivroDto livroDto, Long id);

    public void deleteById(Long id);
}
