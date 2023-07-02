package com.bookstoreapi.service;


import com.bookstoreapi.dto.AutorDto;
import com.bookstoreapi.dto.GeneroDto;
import com.bookstoreapi.entity.AutorEntity;
import com.bookstoreapi.entity.GeneroEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GeneroService {

    List<GeneroEntity> listarGeneros();

    ResponseEntity<GeneroEntity> findGeneroById(Long id);

    void adicionarGenero(GeneroDto generoDto);

    ResponseEntity updateByGeneroId(GeneroDto generoDto, Long id);

    void deleteById(Long id);
}
